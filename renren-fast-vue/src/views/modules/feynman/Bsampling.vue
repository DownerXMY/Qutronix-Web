<template>
<el-container style="height: 660px; border: 1px solid #eee">

    <el-main>
    <el-container>
       <el-header style="text-align: center;height: 40px; font-size: 28px; background-color: rgb(222, 231, 208)">
         <span>Boson Sampling</span>
       </el-header>
       <el-main style="text-align: center; height: 260px; background-color: rgb(232, 241, 241)">
         <span class="el-dropdown-link">
            <img :src="bsPath" :style="selfStyle">
         </span>
       </el-main>
       <el-footer style="text-align: center; height: 320px; background-color: rgb(202, 201, 217)">
         <span class="el-dropdown-link">
            <img :src="bsPath2" :style="selfStyle2">
         </span>
       </el-footer>

    </el-container>
    </el-main>
    <el-aside width="400px" style="background-color:rgb(238, 241, 246)">
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()">
        <el-row>
           <el-col :span="24">
           <div class="grid-content" style="font-size: 22px">
           <el-button type="info" disabled>Input Your Own One</el-button>
           </div>
           </el-col>
        </el-row>

        <el-row>
          <el-col :span="10"><div class="grid-content">
          <el-button type="success" @click="plot2()">An Example</el-button>
          </div></el-col>
          <el-col :span="12"><div class="grid-content">
          <el-button type="success">Select a Unitary Matrix</el-button>
          </div></el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="14"><div class="grid-content">
          <el-button type="text" disabled>Choose Decomposition Style:</el-button>
          </div></el-col>
          <el-col :span="10"><div class="grid-content bg-purple">
          <template>
            <el-select v-model="value" placeholder="" @change="showMessage($event)">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </template>
          </div></el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="12"><div class="grid-content">
          <el-button type="text" disabled>Num of Nodes:</el-button>
          </div></el-col>
          <el-col :span="10"><div class="grid-content">
          <el-input v-model="dataForm.inputNum" placeholder=""></el-input>
          </div></el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="12"><div class="grid-content">
          <el-button type="text" disabled>Initial State:</el-button>
          </div></el-col>
          <el-col :span="10"><div class="grid-content">
          <el-input v-model="dataForm.iniState" placeholder=""></el-input>
          </div></el-col>
        </el-row>

        <el-row>
          <el-col :span="10"><div class="grid-content">
          <el-button type="success">Manually Set</el-button>
          </div></el-col>
          <el-col :span="10"><div class="grid-content">
          <el-button type="success">Num On/Off</el-button>
          </div></el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="10"><div class="grid-content">
          <el-button type="success">Random Set</el-button>
          </div></el-col>
          <el-col :span="12"><div class="grid-content">
          <el-button type="success">Select a Parameters File</el-button>
          </div></el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col><div class="grid-content">
          <el-button type="success" style="width: 80%" @click="plot2()">Plot</el-button>
          </div></el-col>
        </el-row>

        <el-container>
          <el-main style="text-align: center; height: 180px;">
             <img :src="bsPath4" style="height:150px; width:360px; border:1px solid #eee">
          </el-main>
        </el-container>

        <el-row>
          <el-col :span="4"><div class="grid-content">
          <el-button type="text" disabled>Alpha:</el-button>
          </div></el-col>
          <el-col :span="4"><div class="grid-content">
          <el-input v-model="dataForm.alpha" placeholder=""></el-input>
          </div></el-col>
          <el-col :span="4"><div class="grid-content">
          <el-button type="text" disabled>Varphi:</el-button>
          </div></el-col>
          <el-col :span="4"><div class="grid-content">
          <el-input v-model="dataForm.varphi" placeholder=""></el-input>
          </div></el-col>
          <el-col :span="8"><div class="grid-content">
          <el-button type="success">Confirm</el-button>
          </div></el-col>
        </el-row>

        <el-row type="flex" justify="space-around">
           <div class="box">
             <div class="bottom">
             <el-col :span="8">
             <div class="grid-content">
             <el-tooltip class="item" effect="dark" content="Quantum Walks" placement="bottom-start">
               <el-button type="text" round>QW</el-button>
             </el-tooltip>
             </div>
             </el-col>
             <el-col :span="8">
             <div class="grid-content">
             <el-tooltip class="item" effect="dark" content="Quantum Stochastic Walks" placement="bottom">
             <el-button type="text" round>QSW</el-button>
             </el-tooltip>
             </div>
             </el-col>
             <el-col :span="8">
             <div class="grid-content">
             <el-tooltip class="item" effect="dark" content="Multi-Particle Quantum Walks" placement="bottom-end">
             <el-button type="text" round>MPQW</el-button>
             </el-tooltip>
             </div>
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
    data() {
      return {
         selfStyle: {
           height: "220px",
           width: "auto",
           border: "1px solid #eee"
        },
         selfStyle2: {
           height: "290px",
           width: "auto",
           border: "1px solid #eee"
        },
        visible: false,
         dataForm: {
           'inputNum': 12,
           'iniState': '|000000000111>',
           'alpha': '',
           'varphi': '',
           'feature': 'Reck',
           uuid: ''
        },
        bsPath: window.SITE_CONFIG.cdnUrl + '/static/img/'+'loading.gif',
        bsPath2: window.SITE_CONFIG.cdnUrl + '/static/img/'+'loading1.gif',
        bsPath4: window.SITE_CONFIG.cdnUrl + '/static/img/'+'Reck_example_2.png',
        options: [{
          value: 'Reck',
          label: 'Reck',
          feature: 'Reck'
        }, {
          value: 'Clements',
          label: 'Clements',
          feature: 'Clements'
        }],
        value: 'Reck'
      }
    },
    methods: {
    // Show Style
    showMessage(e) {
        console.log(e);
        console.log(e.feature);
        this.$message('Choose Style "'+e+'"');
        this.dataForm.feature = e;
        this.bsPath4 = window.SITE_CONFIG.cdnUrl + '/static/img/'+e+'_example_2.png'
    },
    // get the picture1
          plot1() {
            this.$refs['dataForm'].validate((valid) => {
              if (valid) {
                this.$http({
                  url: this.$http.adornUrl(`/feynman/server3/Boson_plot1`),
                  method: 'post',
                  data: this.$http.adornData({
                    'uuid': getUUID(),
                    'inputNum': this.dataForm.inputNum,
                    'iniState': this.dataForm.iniState
                  })
                }).then(({ data }) => {
                  if (data && data.status === 200) {
                    this.bsPath = this.$http.adornUrl(`/feynman/server3/result?fileName=` + data.data.fileName)
                    this.$message({
                      message: 'Success',
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
          plot2() {
                this.$refs['dataForm'].validate((valid) => {
                  if (valid) {
                    this.$http({
                      url: this.$http.adornUrl(`/feynman/server3/example`),
                      method: 'post',
                      data: this.$http.adornData({
                        'uuid': getUUID(),
                        'inputNum': this.dataForm.inputNum,
                        'iniState': this.dataForm.iniState,
                        'feature': this.dataForm.feature
                      })
                    }).then(({ data }) => {
                      if (data && data.status === 200) {
                        this.selfStyle = {height: "220px", width: "820px", border: "1px solid #eee"}
                        this.selfStyle2 = {height: "290px", width: "820px", border: "1px solid #eee"}
                        this.bsPath = this.$http.adornUrl(`/feynman/server3/result?fileName=` + data.data.distribution)
                        this.bsPath2 = this.$http.adornUrl(`/feynman/server3/result?fileName=` + data.data.waveguides)
                        this.$message({
                          message: 'Success',
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
    // get the picture3
              plot3() {
                this.$refs['dataForm'].validate((valid) => {
                  if (valid) {
                    this.$http({
                      url: this.$http.adornUrl(`/feynman/server3/Boson_plot3`),
                      method: 'post',
                      data: this.$http.adornData({
                        'uuid': getUUID(),
                        'inputNum': this.dataForm.inputNum,
                        'iniState': this.dataForm.iniState
                      })
                    }).then(({ data }) => {
                      if (data && data.status === 200) {
                        this.bsPath2 = this.$http.adornUrl(`/feynman/server3/result?fileName=` + data.data.fileName)
                        this.$message({
                          message: 'Success',
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
    }
 }
</script>

<style>
  .el-container {
     margin-bottom: 10px;
     &:last-child {
       margin-bottom: 0;
     }
  }
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
    background-color: #f9fafc
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
  .el-dropdown-link {
      cursor: pointer;
      color: #409EFF;
    }
    .el-icon-arrow-down {
      font-size: 12px;
    }
</style>
