<template>
<el-container style="height: 660px; border: 1px solid #eee">
  <el-main>
   <el-container>

     <el-header style="text-align: center; height: 40px; font-size: 28px; background-color:rgb(222, 231, 208)">
       <span>Multi-Particle Quantum Walks</span>
     </el-header>

     <el-main style="text-align: center; height: 300px; background-color: rgb(232, 241, 241)">
         <span class="el-dropdown-link">
            <img :src="MPQW_Path1" style="height:260px;width:820px;float:right;border:1px solid #eee">
         </span>
     </el-main>

     <el-container>
     <el-main style="text-align: center; height: 280px; background-color:rgb(239, 101, 198)">
         <span class="el-dropdown-link">
            <img :src="MPQW_Path2" style="height:240px;width:320px;border:1px solid #eee">
         </span>
     </el-main>

     <el-aside width="330px" style="text-align: center; height: 280px; background-color:rgb(227, 241, 112)">
         <span class="el-dropdown-link">
            <img :src="MPQW_Path3" style="height:240px;width:320px;border:1px solid #eee">
         </span>
     </el-aside>

     <el-aside width="40px" style="text-align: center; height: 280px;">
         <span class="el-dropdown-link">
            <img :src="MPQW_Path5" style="height:280px;width:30px;border:1px solid #eee">
         </span>
     </el-aside>

     <el-aside width="140px" style="text-align: center; height: 280px; background-color:rgb(108, 188, 112)">
     <el-row>
       <el-col :span="24">
       <div class="grid-content" style="font-size: 22px">
         <el-button type="text" disabled>Style:</el-button>
       </div>
       </el-col>
     </el-row>
      <template>
        <el-select v-model="mode_value" placeholder="" @change="showMode($event)">
          <el-option
            v-for="item in mode_options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            :mode_value="item.value">
          </el-option>
        </el-select>
      </template>

     <el-row>
       <el-col :span="24">
       <div class="grid-content" style="font-size: 22px">
         <el-button type="text" disabled>Colorbar:</el-button>
       </div>
       </el-col>
     </el-row>
      <template>
        <el-select v-model="Colorbar_value" placeholder="" @change="showColorbar($event)">
          <el-option
            v-for="item in Colorbar_options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            :Colorbar_value="item.value">
          </el-option>
        </el-select>
      </template>

     <el-row>
       <el-col :span="24">
       <div class="grid-content" style="font-size: 22px">
         <el-button type="text" disabled>photon No.</el-button>
       </div>
       </el-col>
     </el-row>
      <template>
        <el-select v-model="photon_value" placeholder="" @change="showPhotonNum($event)">
          <el-option
            v-for="item in photon_options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            :photon_value="item.value">
          </el-option>
        </el-select>
      </template>
     </el-aside>

     </el-container>
     <el-footer style="text-align: center; height: 290px; font-size: 28px; background-color:rgb(222, 231, 208)">
    <template>
  <el-table
    :data="MPQwsData"
    height="250"
    border
    empty-text="No record yet"
    style="width: 100%">
    <el-table-column
      prop="Id"
      label="Id"
      width="40">
    </el-table-column>
    <el-table-column
      prop="stat_method"
      label="stat_method"
      width="120">
    </el-table-column>
    <el-table-column
      prop="iniState"
      label="iniState"
      width="120">
    </el-table-column>
    <el-table-column
      prop="distance"
      label="distance"
      width="100">
    </el-table-column>
    <el-table-column
      prop="probState"
      label="probState"
      width="200">
    </el-table-column>
    <el-table-column
      prop="perspectives"
      label="perspectives"
      width="120">
    </el-table-column>
    <el-table-column
      prop="colorbar"
      label="colorbar"
      width="100">
    </el-table-column>
    <el-table-column
      prop="photon_number"
      label="photon_number"
      width="80">
    </el-table-column>
    <el-table-column
      prop="mode"
      label="mode"
      width="70">
    </el-table-column>
    <el-table-column
      prop="uuid"
      label="uuid"
      width="100"
      v-if="show">
    </el-table-column>
    <el-table-column
      prop="imgUrl"
      label="imgUrl"
      width="80">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="success"
          @click="getDraw(scope.row)">plot</el-button>
      </template>
    </el-table-column>
  </el-table>
          <el-row>
            <el-col :span="12">
              <el-button type="success" round @click="loadRecords()">Load Records</el-button>
            </el-col>
            <el-col :span="12">
              <el-button type="success" round @click="clearRecords()">Clear Records</el-button>
            </el-col>
          </el-row>
    </template>
     </el-footer>
   </el-container>
  </el-main>

  <el-aside width="420px" style="background-color:rgb(238, 241, 246)">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()">
    <el-row>
      <el-col :span="24">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="info" disabled>Input Your Own One</el-button>
      </div>
      </el-col>
      <el-col :span="16">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="text" disabled>Statistical Method:</el-button>
      </div>
      </el-col>
      <el-col :span="6">
      <div class="grid-content" style="font-size: 22px">
        <template>
          <el-select v-model="SM_value" placeholder="" @change="showMessage($event)">
            <el-option
            v-for="item in MS_options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            :SM_value="item.value">
            </el-option>
          </el-select>
        </template>
      </div>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :span="8">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="success" @click="plot1()">An Example</el-button>
      </div>
      </el-col>
      <el-col :span="8">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="success">Select a File</el-button>
      </div>
      </el-col>
      <el-col :span="8">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="success">Manually Set</el-button>
      </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="10">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="text" disabled>Initial State:</el-button>
      </div>
      </el-col>
      <el-col :span="12">
      <div class="grid-content" style="font-size: 22px">
        <el-input v-model="dataForm.iniState" placeholder=""></el-input>
      </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="16">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="text" disabled>Propagation distance z (mm):</el-button>
      </div>
      </el-col>
      <el-col :span="6">
      <div class="grid-content" style="font-size: 22px">
        <el-input v-model="dataForm.distance" placeholder=""></el-input>
      </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="6">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="success">Positions</el-button>
      </div>
      </el-col>
      <el-col :span="5">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="success">Modify</el-button>
      </div>
      </el-col>
      <el-col :span="8">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="success">Mum On\Off</el-button>
      </div>
      </el-col>
      <el-col :span="4">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="success">Plot</el-button>
      </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
      <div class="grid-content" style="font-size: 22px">
        <el-button type="text" disabled>Probabilities of States:</el-button>
      </div>
      </el-col>
    </el-row>

    <el-row>
      </el-col>
      <el-col :span="24">
      <div class="grid-content" style="font-size: 22px">
        <el-input v-model="dataForm.probStates" placeholder=""></el-input>
      </div>
      </el-col>
    </el-row>

    <el-container>
      <el-main style="text-align: center; height: 280px; background-color: rgb(129, 141, 87)">
         <span class="el-dropdown-link">
            <img :src="MPQW_Path4" style="height:240px;width:380px;border:1px solid #eee">
         </span>
      </el-main>
    </el-container>

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
             <el-tooltip class="item" effect="dark" content="Boson Sampling" placement="bottom-end">
             <el-button type="text" round>BS</el-button>
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
import menuAddOrUpdateVue from '../sys/menu-add-or-update.vue';
  export default {
    data() {
      return {
        show: false,
        probUpdateStates: "",
        index: 0,
        MPQwsData: [],
        visible: false,
         dataForm: {
           'distance': 10,
           'iniState': '|100010001>',
           'probStates': '|000020001>;|3,1;5,1;8,1>;|1,1;8,1;9,1>',
           'perspectives': '000000000',
           'colorbar': 'colorbar3',
           'photonNumber': 1,
           'mode': 'view all states',
           'statMethod': 'Bosonic',
           uuid: ''
        },
        MPQW_Path1: '',
        MPQW_Path2: '',
        MPQW_Path3: '',
        MPQW_Path4: '',
        MPQW_Path5: '',
        mode_options: [{
          value: 'view all states',
          label: 'view all states'
        }, {
          value: 'scroll bar',
          label: 'scroll bar'
        }],
        mode_value: 'view all states',
        MS_options: [{
          value: 'Bosonic',
          label: 'Bosonic'
        }, {
          value: 'Fermionic',
          label: 'Fermionic'
        }, {
          value: 'Distinguishable',
          label: 'Distinguishable'
        }],
        SM_value: 'Bosonic',
        Colorbar_options: [{
          value: 'colorbar3',
          label: 'colorbar3'
        }, {
          value: 'hot',
          label: 'hot'
        }, {
          value: 'colorbar',
          label: 'colorbar'
        }, {
          value: 'rainbow',
          label: 'rainbow'
        }, {
          value: 'CMRmap',
          label: 'CMRmap'
        }, {
          value: 'hot_r',
          label: 'hot_r'
        }, {
          value: 'RdYlBu_r',
          label: 'RdYlBu_r'
        }, {
          value: 'coolwarm',
          label: 'coolwarm'
        }],
        Colorbar_value: 'colorbar3',
        photon_options: [{
          value: 1,
          label: 1
        }, {
          value: 5,
          label: 5
        }, {
          value: 9,
          label: 9
        }],
        photon_value: '1',
      }
    },
    methods: {
    // get Drawing
    getDraw(row) {
      this.MPQW_Path1 = this.$http.adornUrl(`/feynman/server4/result?fileName=` + row.uuid+"_fenumerate");
      this.MPQW_Path2 = this.$http.adornUrl(`/feynman/server4/result?fileName=` + row.uuid+"_ftwoD");
      this.MPQW_Path3 = this.$http.adornUrl(`/feynman/server4/result?fileName=` + row.uuid+"_foneOptic");
      this.MPQW_Path4 = this.$http.adornUrl(`/feynman/server4/result?fileName=` + row.uuid+"_frhoZ");
    },
    // Load Records
    loadRecords() {
      console.log("loading data from mpqws...");
      this.$http({
        url: this.$http.adornUrl('/feynman/server4/MPQws/result'),
        method: "post"
        }).then(({data}) => {
          for (var amount=0;amount<data.data.length;amount++) {
            console.log(data.data[amount]);
            this.index = this.index + 1;
            this.probUpdateStates = data.data[amount].probRawStates.replace("]",">");
            this.MPQwsData.push({
              Id:this.index,
              stat_method:data.data[amount].statMethod,
              iniState:data.data[amount].iniRawState,
              probState:this.probUpdateStates,
              perspectives:data.data[amount].perspectives,
              distance:data.data[amount].distance,
              colorbar:data.data[amount].colorbar,
              mode:data.data[amount].mode,
              photon_number:data.data[amount].photonNumber,       
              uuid:data.data[amount].uuid
            });
          }
        });
    },
    // Clear Records
    clearRecords() {
      this.index = 0;
      this.MPQwsData = [];
    },
    // Statistical Methods
    showMessage(e1) {
        this.$message('Choose Statistical Method "'+e1+'"');
        this.dataForm.statMethod = e1
    },
    // Choose Styles
    showMode(e2) {
        this.$message('Choose Style "'+e2+'"');
        this.dataForm.mode = e2
    },
    // Choose Colorbar
    showColorbar(e3) {
        this.$message('Choose Colorbar "'+e3+'"');
        this.dataForm.colorbar = e3;
        this.MPQW_Path5 = window.SITE_CONFIG.cdnUrl + '/static/img/colorbar_'+e3+'.png'
    },
    // Choose photon number
    showPhotonNum(e4) {
        this.$message('Choose photon number "'+e4+'"');
        this.dataForm.photonNumber = e4
    },
    // get the picture1
          plot1() {
            this.$refs['dataForm'].validate((valid) => {
              if (valid) {
                this.$http({
                  url: this.$http.adornUrl(`/feynman/server4/MPQW_plot1`),
                  method: 'post',
                  data: this.$http.adornData({
                    'uuid': getUUID(),
                    'distance': this.dataForm.distance,
                    'iniState': this.dataForm.iniState,
                    'probStates': this.dataForm.probStates,
                    'perspectives': this.dataForm.perspectives,
                    'colorbar': this.dataForm.colorbar,
                    'photonNumber': this.dataForm.photonNumber,
                    'statMethod': this.dataForm.statMethod,
                    'mode': this.dataForm.mode
                  })
                }).then(({ data }) => {
                  if (data && data.status === 200) {
                    // console.log("hello")
                    this.MPQW_Path1 = this.$http.adornUrl(`/feynman/server4/result?fileName=` + data.data.fenumerate)
                    this.MPQW_Path2 = this.$http.adornUrl(`/feynman/server4/result?fileName=` + data.data.ftwoD)
                    this.MPQW_Path3 = this.$http.adornUrl(`/feynman/server4/result?fileName=` + data.data.foneOptic)
                    this.MPQW_Path4 = this.$http.adornUrl(`/feynman/server4/result?fileName=` + data.data.frhoZ)
                    this.index = this.index + 1;
                    this.MPQwsData.push({
                      Id:this.index,
                      stat_method:this.dataForm.statMethod,
                      iniState:this.dataForm.iniState,
                      probState:this.dataForm.probStates,
                      perspectives:this.dataForm.perspectives,
                      distance:this.dataForm.distance,
                      colorbar:this.dataForm.colorbar,
                      mode:this.dataForm.mode,
                      photon_number:this.dataForm.photonNumber,
                      uuid:this.dataForm.uuid
                    })
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
    margin-bottom: 5px;
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
