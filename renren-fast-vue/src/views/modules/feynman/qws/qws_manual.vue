<template>
  <div>
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
      label-width="260px">
      <el-form-item label="">
        <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top:10px;" justify="center">
          <el-col :span="10" justify="center">
            <el-upload ref="upload" action="/" :show-file-list="false" :on-change="importExcel" :auto-upload="false">
              <el-button slot="trigger" icon="el-icon-upload" size="middle" type="primary">
                Select a file
              </el-button>
            </el-upload>
          </el-col>
          <el-col :span="10" justify="center">
            <el-button type="primary" @click="drawTablePoint()">Manually set</el-button>
          </el-col>
          <el-col :span="4" justify="center">

          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="Prpagation Distance z(mm)">
        <el-input v-model="dataForm.fz"></el-input>
      </el-form-item>
      <el-form-item label="Inject num">
        <el-input v-model="dataForm.inn"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top:10px;" justify="center">
          <el-col :span="10" justify="center">
            <el-button type="success" @click="plotFile()">Plot file</el-button>
          </el-col>
          <el-col :span="10">
            <el-button type="success" @click="plotFile()">Plot file Quickly</el-button>
          </el-col>
          <el-col :span="4" justify="center">

          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
    <drawTablePoint v-if="drawTablePointVisible" ref="drawTablePoint" @refreshDrawData="getDrawData"></drawTablePoint>
  </div>
</template>

<script>
import XLSX from 'xlsx'
import DrawTablePoint from '../draw'
import { getUUID } from '@/utils'

export default {
  data() {
    return {
      dataForm: {
        'fz': '3',
        'inn': '2',
        uuid: '',
        tabledata: ''
      },
      drawTablePointVisible: false,
    }
  },
  methods: {
    drawTablePoint() {
      this.drawTablePointVisible = true
      this.$nextTick(() => {
        this.$refs.drawTablePoint.inti();
      })
    },
    // 获取数据列表
    getDrawData(coordinates) {
      console.log("Finish");
      this.dataForm.tabledata = coordinates
      this.dataForm.tabledata = this.dataForm.tabledata.map(item => ({ x: (item[0] - 300) * 0.5, y: (item[1] - 300) * 0.5 }))
      console.log(this.dataForm.tabledata);
    },
    // 表单提交
    plotFile() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.qwsPath = this.$http({
            url: this.$http.adornUrl(`/feynman/server/plotfile`),
            method: 'post',
            data: this.$http.adornData({
              'uuid': getUUID(),
              'inn': this.dataForm.inn,
              'fz': this.dataForm.fz,
              'tabledata': this.dataForm.tabledata,
              'colorbar': this.colorbar
            })
          }).then(({ data }) => {
            console.log('data=' + data + 'data.status=' + data.status)
            if (data && data.status === 200) {
              this.qwsimg = this.$http.adornUrl(`/feynman/server/result?fileName=` + data.data.fileName);
              this.num2 = this.num2 + 1;
              var tableFileData = "";
              for (var index = 0; index < this.dataForm.tabledata.length; index++) {
                this.tableFileData = this.tableFileData + "(" + this.dataForm.tabledata[index].x + "," + this.dataForm.tabledata[index].y + "), ";
              }
              this.tableFileData = this.tableFileData.substring(9, this.tableFileData.length - 2);
              console.log(this.tableFileData);
              this.tData2.push({ Id: this.num2, fz: this.dataForm.fz, inn: this.dataForm.inn, uuid: this.dataForm.uuid, points: this.tableFileData })
              //  this.qws.width = Math.ceil(this.qws.height / this.qws.width * 600) + 'px'
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    },
    importExcel(file) {
      console.log('importExcel...')
      // let file = file.files[0] // 使用传统的input方法需要加上这一步
      const types = file.name.split('.')[1]
      const fileType = ['xlsx', 'xls', 'xlm', 'mat'].some(item => item === types)
      if (!fileType) {
        alert('格式错误！请重新选择')
        return
      }
      this.file2Xce(file).then(tabJson => {
        if (tabJson && tabJson.length > 0) {
          this.xlsxJson = tabJson
          this.dataForm.tabledata = tabJson[0].sheet
          // this.plotFile()
          // xlsxJson就是解析出来的json数据,数据格式如下
          // [
          //   {
          //     sheetName: sheet1
          //     sheet: sheetData
          //   }
          // ]
        }
      })
    },
    file2Xce(file) {
      return new Promise(function (resolve, reject) {
        const reader = new FileReader()
        reader.onload = function (e) {
          const data = e.target.result
          this.wb = XLSX.read(data, {
            type: 'binary'
          })
          const result = []
          this.wb.SheetNames.forEach((sheetName) => {
            result.push({
              sheetName: sheetName,
              sheet: XLSX.utils.sheet_to_json(this.wb.Sheets[sheetName])
            })
          })
          resolve(result)
        }
        reader.readAsBinaryString(file.raw)
        // reader.readAsBinaryString(file) // 传统input方法
      })
    }
  },
  components: {
    DrawTablePoint
  },
  mounted() {

  },
  computed: {
    colorbar: {
      get() { return this.$store.state.feynmandata.qwsHisData },

    },
    qwsimg: {
      set(val) {
        this.$store.commit('feynmandata/updateQwsImg', val)
      }
    }
  }
};
</script>

<style>
.el-input {
  width: 280px;
}
.el-button {
  width: 140px;
}
</style>