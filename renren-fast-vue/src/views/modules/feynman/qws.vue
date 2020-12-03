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
            <el-col :span="8">
              <el-button type="success" round>Plot your file</el-button>
            </el-col>
            <el-col :span="11">
              <el-button type="success" round @click="plotFile()">Plot your file Quickly</el-button>
            </el-col>
            <el-col :span="8">
              <el-button type="success" round @click="loadtasks()">Load Tasks</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="success" round @click="cleartasks()">Clear All</el-button>
            </el-col>
          </el-row>
          <el-row>
          <el-table
            :data="tData"
            height="340"
            border
            empty-text="No record yet"
            style="width: 100%">
            <el-table-column
              prop="Id"
              label="Id"
              width="40">
            </el-table-column>
            <el-table-column
              prop="z"
              label="z"
              width="40">
            </el-table-column>
            <el-table-column
              prop="x"
              label="x"
              width="40">
            </el-table-column>
            <el-table-column
              prop="y"
              label="y"
              width="40">
            </el-table-column>
            <el-table-column
              prop="dx"
              label="dx"
              width="60">
            </el-table-column>
            <el-table-column
              prop="dy"
              label="dy"
              width="60">
            </el-table-column>
            <el-table-column
              prop="px"
              label="px"
              width="60">
            </el-table-column>
            <el-table-column
              prop="py"
              label="py"
              width="60">
            </el-table-column>
            <el-table-column
              prop="Fz"
              label="Fz"
              width="60">
            </el-table-column>
            <el-table-column
              prop="Inn"
              label="Inn"
              width="60">
            </el-table-column>
            <el-table-column
              prop="imgUrl"
              label="imgUrl"
              width="80">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="success"
                  @click="takeDraw(scope.row)">plot</el-button>
              </template>
            </el-table-column>
            <el-table-column
              prop="Uuid"
              label="Uuid"
              width="60"
              v-if="show">
            </el-table-column>
          </el-table>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-button type="success" round @click="loadHistories()">Load Histories</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="success" round @click="clearHisroties()">Clear Histories</el-button>
            </el-col>
          </el-row>
          <el-row>
            <el-table
              :data="tData2"
              height="240"
              border
              empty-text="No record yet"
              style="width: 100%">
              <el-table-column
                prop="Id"
                label="Id"
                width="40">
              </el-table-column>
              <el-table-column
                prop="fz"
                label="fz"
                width="40">
              </el-table-column>
              <el-table-column
                prop="inn"
                label="inn"
                width="60">
              </el-table-column>
              <el-table-column
                prop="points"
                label="points"
                width="380">
              </el-table-column>
            <el-table-column
              prop="imgUrl"
              label="imgUrl"
              width="80">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="success"
                  @click="takeManDraw(scope.row)">plot</el-button>
              </template>
            </el-table-column>
              <el-table-column
                prop="uuid"
                label="uuid"
                width="40"
                v-if="show">
              </el-table-column>
            </el-table>
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
      show: false,
      num: 0,
      num2: 0,
      tData: [],
      tData2: [],
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
    takeDraw(row) {
        console.log(row.Uuid);
        this.qwsPath = this.$http.adornUrl(`/feynman/server/result?fileName=` + row.Uuid);
    },
    takeManDraw(row) {
      console.log(row.uuid);
      this.qwsPath = this.$http.adornUrl(`/feynman/server/result?fileName=` + row.uuid)
    },
    // 获取结果
    getQwsResult() {
      this.dataForm.uuid = getUUID()
      this.qwsPath = this.$http.adornUrl(`/feynman/server/result?fileName=test&uuid=${this.dataForm.uuid}`)
    },
    // Load Tasks
    loadtasks() {
      console.log("loading data from qws...");
      this.$http({
        url: this.$http.adornUrl('/feynman/server/qws/result'),
        method: "post"
        }).then(({data}) => {
          for (var amount=0;amount<data.data.length;amount++) {
            if (data.data[amount].z != null) {
              this.num = this.num + 1;
              var default_Fz = data.data[amount].Fz;
              var default_Inn = data.data[amount].Inn;
              if (data.data[amount].Fz == null) {
                this.defalut_Fz = '0';
              }
              if (data.data[amount].Inn == null) {
                this.default_Inn = '0';
              }
              this.tData.push({Id:this.num,z:data.data[amount].z,x:data.data[amount].x,y:data.data[amount].y,dx:data.data[amount].dx,dy:data.data[amount].dy,px:data.data[amount].px,py:data.data[amount].py,Fz:this.defalut_Fz,Inn:this.default_Inn,Uuid:data.data[amount].uuid});
            }
          }
        });
    },
    // Load Histories
    loadHistories() {
      console.log("loading data from qwsFile...");
      this.$http({
        url: this.$http.adornUrl('/feynman/server/qwsFile/result'),
        method: "post"
        }).then(({data}) => {
          console.log(data);
          for (var amount=0;amount<data.data.length;amount++) {
            this.num2 = this.num2 + 1;
              this.tData2.push({Id:this.num2,fz:data.data[amount].fz,inn:data.data[amount].inn,uuid:data.data[amount].uuid,points:data.data[amount].points});
            }
        });  
    },
    // Clear Tasks
    cleartasks() {
      this.tData = [];
      this.num = 0;
    },
    // Clear Histories
    clearHistories() {
      this.tData2 = [];
      this.num2 = 0;
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
              this.num = this.num + 1;
              this.qwsPath = this.$http.adornUrl(`/feynman/server/result?fileName=` + data.data.fileName);
              this.tData.push({Id:this.num,z:this.dataForm.z,x:this.dataForm.x,y:this.dataForm.y,dx:this.dataForm.dx,dy:this.dataForm.dy,px:this.dataForm.px,py:this.dataForm.py,Fz:this.dataForm.fz,Inn:this.dataForm.inn,Uuid:this.dataForm.uuid});
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
              this.num2 = this.num2 + 1;
              var tableFileData = "";
              for (var index=0;index<this.dataForm.tabledata.length;index++) {
                this.tableFileData = this.tableFileData + "("+this.dataForm.tabledata[index].x+","+this.dataForm.tabledata[index].y+"), ";
              }
              this.tableFileData = this.tableFileData.substring(9,this.tableFileData.length-2);
              console.log(this.tableFileData);
              this.tData2.push({Id:this.num2,fz:this.dataForm.fz,inn:this.dataForm.inn,uuid:this.dataForm.uuid,points:this.tableFileData})
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
        console.log(this.dataForm.tabledata);
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
