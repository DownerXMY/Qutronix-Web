<template>
  <el-container style="height: 660px; border: 1px solid #eee">

    <el-container>

      <el-header style="text-align: center; font-size: 28px; background-color: rgb(125, 231, 89)">
      <span>2D Quantum Stochastic Walks</span>
      </el-header>

      <el-main>
        <span class="el-dropdown-link">
        <img src="qwsPath2" style="height:440px;width:500px;float:right;border:1px solid #eee">
        </span>
      </el-main>

    </el-container>
    <el-aside width="20px" style="background-color: rgb(0, 0, 0)"></el-aside>
    <el-aside width="480px" style="background-color: rgb(238, 241, 246)">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()">

    <el-row>
      <el-col :span="24"><div class="grid-content" style="font-size: 22px">
      <el-button type="info" disabled>Input Your Own One</el-button></div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="8"><div class="grid-content">
      <el-button type="success" @click="plot3()">An Example</el-button></div>
      </el-col>
      <el-col :span="8"><div class="grid-content">
      <el-button type="success">Select a File</el-button></div>
      </el-col>
      <el-col :span="8"><div class="grid-content">
      <el-button type="success">Manually Set</el-button></div>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :span="6"><div class="grid-content">
      <el-button type="text" disabled>Inject num:</el-button></div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light">
      <el-input v-model="dataForm.inputNum" placeholder=""></el-input></div>
      </el-col>
      <el-col :span="6"><div class="grid-content">
      <el-button type="text" disabled>δβ Amplitude:</el-button></div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light">
      <el-input v-model="dataForm.amplitude" placeholder=""></el-input></div>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :span="6"><div class="grid-content">
      <el-button type="text" disabled>Z Interval(nm):</el-button></div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light">
      <el-input v-model="dataForm.interval" placeholder=""></el-input></div>
      </el-col>
      <el-col :span="3"><div class="grid-content">
      <el-button type="text" disabled>From</el-button></div></el-col>
      <el-col :span="3"><div class="grid-content bg-purple-light">
      <el-input v-model="dataForm.from" placeholder=""></el-input></div>
      </el-col>
      <el-col :span="3"><div class="grid-content">
      <el-button type="text" disabled>To</el-button></div></el-col>
      <el-col :span="3"><div class="grid-content bg-purple-light">
      <el-input v-model="dataForm.to" placeholder=""></el-input></div>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :span="12"><div class="grid-content">
      <el-button type="text" disabled>View Probability For Node No.</el-button></div></el-col>
      <el-col :span="5"><div class="grid-content bg-purple">
      <el-input v-model="dataForm.nodeId" placeholder=""></el-input></div>
      </el-col>
      <el-col :span="2"><div class="grid-content">
      <el-button size="small" type="success">Change Button</el-button></div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="8"><div class="grid-content">
      <el-button type="success" size="medium">View Manual Set</el-button></div>
      </el-col>
      <el-col :span="8"><div class="grid-content">
      <el-button type="success" size="medium">Modify Manual Set</el-button></div>
      </el-col>
      <el-col :span="8"><div class="grid-content">
      <el-button type="success" size="medium">Num On\Off</el-button></div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="5"><div class="grid-content">
      <el-button type="success" size="mini">Plot Your File</el-button></div>
      </el-col>
      <el-col :span="9"><div class="grid-content">
      <el-button type="success" size="mini" @click="plot2()">Plot Your File Quickly</el-button></div>
      </el-col>
      <el-col :span="5"><div class="grid-content">
      <el-button type="success" size="mini">Average Of:</el-button></div>
      </el-col>
      <el-col :span="3"><div class="grid-content">
      <el-input type="success" size="mini" v-model="dataForm.times" placeholder=""></el-input></div>
      </el-col>
      <el-col :span="2"><div class="minitype">
      <el-button type="text" size="mini" disabled>times</el-button></div></el-col>
    </el-row>

    <el-row>
      <span class="el-dropdown-link">
        <img src="qwsPath" style="height:190px;width:480px;float:right;border:1px solid #eee">
      </span>
    </el-row>

    <el-row>
      <el-col :span="24"><div class="grid-content">
      <el-button type="text" disabled>Case Studies Of QSW</el-button></div></el-col>
    </el-row>

    <el-row>
      <el-col :span="24"><div class="grid-content">
      <el-button type="info" disabled>Links To More Modules</el-button></div></el-col>
    </el-row>

    <el-row type="flex" justify="space-around">
      <div class="box">
      <div class="bottom">
      <el-col :span="8"><div class="grid-content">
      <el-tooltip class="item" effect="dark" content="Quantum Walks" placement="bottom-start">
      <el-button type="text" round>QW</el-button>
      </el-tooltip></div>
      </el-col>
      <el-col :span="8"><div class="grid-content">
      <el-tooltip class="item" effect="dark" content="Multi-Particle Quantum Walks" placement="bottom">
      <el-button type="text" round>MPQW</el-button>
      </el-tooltip></div>
      </el-col>
      <el-col :span="8"><div class="grid-content">
      <el-tooltip class="item" effect="dark" content="Boson Sampling" placement="bottom-end">
      <el-button type="text" round>BS</el-button>
      </el-tooltip></div>
      </el-col>
      </div>
      </div>
    </el-row>

    </el-form>
    </el-aside>
  </el-container>
</template>

<script>
  import { getUUID } from '@/utils'
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          'inputNum': 13,
          'amplitude': 1,
          'interval': 0.1,
          'from': 2,
          'to': 5,
          'nodeId': '13,1',
          'times': 10,
          uuid: ''
        },
        qwsPath: '',
        qwsPath2: '',
        dataRule: {}
      }
    },
    created() {
        this.getQwsResult()
    },
    methods: {
       // return result...
      getQwsResult () {
        this.dataForm.uuid = getUUID()
        this.captchaPath = this.$http.adornUrl(`/feynman/server2/result?fileName=tt&uuid=${this.dataForm.uuid}`)
      },
      // get the picture1
      plot2() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/feynman/server2/plot2'}`),
              method: 'post',
              data: this.$http.adornData({
                'uuid': getUUID(),
                'inputNum': this.dataForm.inputNum,
                'amplitude': this.dataForm.amplitude,
                'interval': this.dataForm.interval,
                'from': this.dataForm.from,
                'to': this.dataForm.to,
                'nodeId': this.dataForm.nodeId,
                'times': this.dataForm.times
              })
            }).then(({data}) => {
              if (data && data.code === 200) {
                this.qwsPath = this.$http.adornUrl(`/feynman/server2/result?fileName=` + data.data.fileName)
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
      // get the picture2
            plot3() {
              this.$refs['dataForm'].validate((valid) => {
                if (valid) {
                  this.$http({
                    url: this.$http.adornUrl(`/feynman/server2/plot3'}`),
                    method: 'post',
                    data: this.$http.adornData({
                      'uuid': getUUID(),
                      'inputNum': this.dataForm.inputNum,
                      'amplitude': this.dataForm.amplitude,
                      'interval': this.dataForm.interval,
                      'from': this.dataForm.from,
                      'to': this.dataForm.to,
                      'nodeId': this.dataForm.nodeId,
                      'times': this.dataForm.times
                    })
                  }).then(({data}) => {
                    if (data && data.code === 200) {
                      this.qwsPath2 = this.$http.adornUrl(`/feynman/server2/result?fileName=` + data.data.fileName)
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
          dataFormSubmit() {
            this.$refs['dataForm'].validate((valid) => {
              if (valid) {}
          })
      }
    }
  }
</script>

<style>
  .el-row {
    margin-bottom: 10px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
    text-align: center;
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
  }
  .go-right {
    float: right;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .minitype {
  border-radius: 4px;
    min-height: 20px;
  }
  .box {
   width: 400px;
   .item {
     margin: 30px;
   }
   .bottom {
     clear: both;
     text-align: center;
   }
  }
</style>
