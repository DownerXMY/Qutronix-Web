<template>
  <div>
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
      label-width="300px">
      <el-form-item label="">
        <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top:10px;" justify="center">
          <el-col :span="10" justify="center">
            <el-upload ref="upload" action="/" :show-file-list="false" :on-change="importExcel" :auto-upload="false">
              <el-button slot="trigger" icon="el-icon-upload" size="large" type="warning">
                Select a file
              </el-button>
            </el-upload>
          </el-col>
          <el-col :span="10" justify="center">
            <el-button type="warning" size="large" @click="showDrawTablePoint()">Manually set</el-button>
          </el-col>
          <el-col :span="4" justify="center">

          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="Prpagation Distance z(mm)" class="title">
        <el-input v-model="dataForm.fz"></el-input>
      </el-form-item>
      <el-form-item label="Inject num">
        <el-input v-model="dataForm.inn"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top:10px;" justify="center">
          <el-col :span="10" justify="center">
            <el-button type="success" size="large" @click="plotFile()">Plot file</el-button>
          </el-col>
          <el-col :span="10">
            <el-button type="success" size="large" @click="plotFile()">Plot file Quickly</el-button>
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
    showDrawTablePoint() {
      console.log("qws manual showDrawTablePoint")
      this.$emit("showDrawTablePoint");
    },
    drawTablePoint() {
      this.drawTablePointVisible = true
      this.$nextTick(() => {
        this.$refs.drawTablePoint.inti();
      })
    },
    // 获取数据列表
    getDrawData(coordinates) {
      console.log("qws manual getDrawData ");
      //this.$store.state.feynmandata.qwsTabledata = coordinates
      //this.$store.state.feynmandata.qwsTabledata = this.$store.state.feynmandata.qwsTabledata.map(item => ({ x: (item[0] - 300) * 0.5, y: (item[1] - 300) * 0.5 }))
      //console.log(this.$store.state.feynmandata.qwsTabledata);
    },
    // 表单提交
    plotFile() {
      console.log("qws plot file")
      console.log(this.colorbar)
       console.log(this.qwsTabledata)

      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
                this.$emit("closeDrawer")
            this.qwsimgcontainer.style.display = "none";
          this.imgloading.style.display = "block";
          this.qwsPath = this.$http({
            url: this.$http.adornUrl(`/feynman/server/plotfile`),
            method: 'post',
            data: this.$http.adornData({
              'uuid': getUUID(),
              'inn': this.dataForm.inn,
              'fz': this.dataForm.fz,
              'tabledata':this.qwsTabledata,
              'colorbar': this.colorbar,
              'executor': this.$store.state.user.name
            })
          }).then(({ data }) => {
            console.log('data=' + data + 'data.status=' + data.status)
            if (data && data.status === 200) {
              this.qwsimg = this.$http.adornUrl(`/feynman/server/result?fileName=` + data.data.fileName);
                    this.qwsimgcontainer.style.display = "block";
              this.imgloading.style.display = "none";
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
                    this.qwsimgcontainer.style.display = "none";
              this.imgloading.style.display = "none";
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
          this.qwsTabledata = tabJson[0].sheet
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
      get() { return this.$store.state.feynmandata.qws_colorbar },

    },
    qwsimg: {
      set(val) {
        this.$store.commit('feynmandata/updateQwsImg', val)
      }
    },
    qwsTabledata: {
      get() { return this.$store.state.feynmandata.qwsTabledata },
      set(val) {
        this.$store.commit('feynmandata/updateQwsTabledata', val)
      }
    },qwsimgcontainer: {
      get() { return this.$store.state.feynmandata.qwsimgcontainer },
      set(val) {
        this.$store.commit('feynmandata/updateQwsimgcontainer', val)
      }
    }, imgloading: {
      get() { return this.$store.state.feynmandata.imgloading },
      set(val) {
        this.$store.commit('feynmandata/updateImgloading', val)
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
.el-form-item__label {
  font: bold italic 30px/40px arial, sans-serif, "宋体";
  text-align: right;
  float: left;
  font-size: 16px;
  color: #606266;
  line-height: 40px;
  padding: 0 12px 0 0;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  width: 260px;
}
.el-input__inner {
  font: bold 30px/40px arial, sans-serif, "宋体";
  font-size: 16px;
  -webkit-appearance: none;
  background-color: #fff;
  background-image: none;
  border-radius: 4px;
  border: 1px solid #dcdfe6;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  color: #606266;
  display: inline-block;
  height: 40px;
  line-height: 40px;
  outline: 0;
  padding: 0 15px;
  -webkit-transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  width: 100%;
}
</style>