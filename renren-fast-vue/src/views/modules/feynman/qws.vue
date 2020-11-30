<template>
  <div class="mod-config">
    <el-container style="height: 1000px; border: 1px solid #eee">

      <el-container>

        <el-header style="text-align: center; font-size: 32px">
          <span>Two Dimensional Quantum Walks</span>
        </el-header>

        <el-main>
          <div id="d_qws" style="width: 600px; height: 600px">
            <img :src="qwsPath" id="qws" style="height: auto; width:auto;border: 1px solid #eee">
          </div>
        </el-main>

      </el-container>
      <el-aside width="600px" style="background-color: rgb(238, 241, 246)">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
          label-width="220px">

          <el-row type="flex" justify="center">
            <el-col :span="24" :offset="0" style="text-align: center;">
              <div style="text-align: center; font-size: 22px">A first try of the
                regular 2d quantum</div>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top  : 10px;">
            <el-col :span="12" :offset="6">
              <el-button type="success" round>An example of 2d quantum walks</el-button>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="Prpagation Distance " label-width="200px">

              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="z(mm)" prop="z" label-width="60px">
                <el-input v-model="dataForm.z" style="width: 120px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">

            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="Num of nodes in x or y:" label-width="200px">

              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="(x)" prop="x" label-width="60px">
                <el-input v-model="dataForm.x" style="width: 120px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="(y)" prop="y" label-width="60px">
                <el-input v-model="dataForm.y" style="width: 120px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="Inject point of x or 1y:" label-width="200px">

              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="(x)" prop="px" label-width="60px">
                <el-input v-model="dataForm.px" style="width: 120px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="(y)" prop="py" label-width="60px">
                <el-input v-model="dataForm.py" style="width: 120px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="Waveguide spacing in x direction (um):" label-width="200px">

              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="(x)" prop="dx" label-width="60px">
                <el-input v-model="dataForm.dx" style="width: 120px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="(y)" prop="dy" label-width="60px">
                <el-input v-model="dataForm.dy" style="width: 120px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top:10px;" justify="center">
            <el-col :span="7" justify="center">
              <el-button type="success" round>Plot2</el-button>
            </el-col>
            <el-col :span="7">
              <el-button type="success" round @click="plot()">Plot Quickly</el-button>
            </el-col>
          </el-row>

          <el-row type="flex" justify="center">
            <el-col :span="24" :offset="0" style="text-align: center;">
              <div style="text-align: center; font-size: 22px">Input your own one</div>
            </el-col>
          </el-row>

          <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top:10px;" justify="center">
            <el-upload ref="upload" action="/" :show-file-list="false" :on-change="importExcel" :auto-upload="false">
              <el-button slot="trigger" icon="el-icon-upload" size="small" type="primary">
                Select a file
              </el-button>
            </el-upload>
            <el-col :span="7">
              <el-button type="primary" round @click="drawTablePoint()">Manually set</el-button>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="Prpagation Distance z(mm):" label-width="200px">

              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="" prop="fz" label-width="60px">
                <el-input v-model="dataForm.fz" style="width: 120px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">

            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="Inject num: " label-width="200px">

              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="" prop="inn" label-width="60px">
                <el-input v-model="dataForm.inn" style="width: 120px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">

            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top:10px;" justify="center">
            <el-col :span="7" justify="center">
              <el-button type="success" round>Plot your file</el-button>
            </el-col>
            <el-col :span="7">
              <el-button type="success" round @click="plotFile()">Plot your file Quickly</el-button>
            </el-col>
          </el-row>
        </el-form>
      </el-aside>

    </el-container>
    <drawTablePoint v-if="drawTablePointVisible" ref="drawTablePoint" @refreshDrawData="getDrawData"></drawTablePoint>
  </div>
</template>

<script>
import { getUUID } from '@/utils'
import XLSX from 'xlsx'
import DrawTablePoint from './draw'
export default {
  data() {
    return {
      visible: false,
      dataForm: {
        z: 5,
        'x': 21,
        'y': 21,
        'px': 11,
        'py': 11,
        'dx': 15,
        'dy': 15,
        'fz': '3',
        'inn': '2',
        uuid: '',
        tabledata: ''

      },
      drawTablePointVisible: false,
      qwsPath: '',
      dataRule: {
        z: [
          { required: true, message: 'Prpagation Distance z(mm)', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    // this.getQwsResult()
  },
  components: {
    DrawTablePoint
  },
  methods: {
    // 获取结果
    getQwsResult() {
      this.dataForm.uuid = getUUID()
      this.qwsPath = this.$http.adornUrl(`/feynman/server/result?fileName=test&uuid=${this.dataForm.uuid}`)
    },
    // 表单提交
    plot() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.qwsPath = this.$http({
            url: this.$http.adornUrl(`/feynman/server/plot`),
            method: 'post',
            data: this.$http.adornData({
              'uuid': getUUID(),
              'z': this.dataForm.z,
              'x': this.dataForm.x,
              'y': this.dataForm.y,
              'px': this.dataForm.px,
              'py': this.dataForm.py,
              'dx': this.dataForm.dx,
              'dy': this.dataForm.dy

            })
          }).then(({ data }) => {
            console.log('data=' + data + 'data.status=' + data.status)
            if (data && data.status === 200) {
              this.qwsPath = this.$http.adornUrl(`/feynman/server/result?fileName=` + data.data.fileName)
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
              'tabledata': this.dataForm.tabledata
            })
          }).then(({ data }) => {
            console.log('data=' + data + 'data.status=' + data.status)
            if (data && data.status === 200) {
              this.qwsPath = this.$http.adornUrl(`/feynman/server/result?fileName=` + data.data.fileName)
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
          this.plotFile()
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
    },
    drawTablePoint() {
      this.drawTablePointVisible = true
      this.$nextTick(() => {
        this.$refs.drawTablePoint.inti();
      })
    },
      // 获取数据列表
      getDrawData (coordinates) {
        console.log("Finish");
        this.dataForm.tabledata = coordinates
        this.dataForm.tabledata = this.dataForm.tabledata.map(item => ({x:(item[0]-300)*0.5,y:(item[1]-300)*0.5}))
        console.log(this.dataForm.tabledata)
      },
    // 表单提交
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {

        }
      })
    }
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 0px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
  text-align: center;
}
.row-bg {
  padding: 0px 0;
  background-color: #f9fafc;
}
</style>
