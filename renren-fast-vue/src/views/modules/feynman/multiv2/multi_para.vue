<template>
  <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
    label-width="220px">

    <el-form-item label="Statistical Method" label-width="260px">
      <el-select v-model="statisticalMethod" placeholder="" @change="changeStatisticalMethod($event)">
        <el-option v-for="item in methodOptions" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="Initial State" label-width="260px">
      <el-input v-model="dataForm.iniState"></el-input>
    </el-form-item>

    <el-form-item label="Propagation distance z (mm)" label-width="260px">
      <el-input v-model="dataForm.distance"></el-input>
    </el-form-item>

    <el-form-item label="Probabilities of States" label-width="260px">
      <el-input v-model="dataForm.probStates"></el-input>
    </el-form-item>
    <el-form-item label="Photon No" label-width="260px">
      <el-select v-model="photon_value" placeholder="" @change="showPhotonNum($event)">
        <el-option v-for="item in photon_options" :key="item.value" :label="item.label" :value="item.value"
          :photon_value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="Colorbar" label-width="260px">
      <el-select v-model="colorbar" placeholder="" @change="showColorbar($event)">
        <el-option v-for="item in Colorbar_options" :key="item.value" :label="item.label" :value="item.value"
          :colorbar="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="">
      <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top:10px;" justify="center">
        <el-col :span="10" justify="center">
          <el-button type="success" @click="plot()">Plot</el-button>
        </el-col>
        <el-col :span="10">
          <el-button type="success" @click="plot()">Plot Quickly</el-button>
        </el-col>
        <el-col :span="4" justify="center">

        </el-col>
      </el-row>
    </el-form-item>
  </el-form>

</template>

<script>
import { getUUID } from '@/utils'
export default {
  data() {
    return {
      dataForm: {
        'distance': 10,
        'iniState': '|100010001>',
        'probStates': '|000020001>;|3,1;5,1;8,1>;|1,1;8,1;9,1>',
        'perspectives': '000000000',
        'photonNumber': 1,
        'mode': 'view all states',
        'statMethod': 'Bosonic',
        uuid: ''
      },
      methodOptions: [{
        value: 'Bosonic',
        label: 'Bosonic'
      }, {
        value: 'Fermionic',
        label: 'Fermionic'
      }, {
        value: 'Distinguishable',
        label: 'Distinguishable'
      }],
      statisticalMethod: 'Bosonic',
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
      colorbar: 'colorbar3',
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
    // 表单提交
    plot() {
      this.$refs['dataForm'].validate((valid) => {

this.$emit("closeDrawer")
        if (valid) {

          this.$emit("loading");


          this.$http({
            url: this.$http.adornUrl(`/feynman/server4/MPQW_plot1`),
            method: 'post',
            data: this.$http.adornData({
              'uuid': getUUID(),
              'distance': this.dataForm.distance,
              'iniState': this.dataForm.iniState,
              'probStates': this.dataForm.probStates,
              'perspectives': this.dataForm.perspectives,
              'colorbar': this.colorbar,
              'photonNumber': this.dataForm.photonNumber,
              'statMethod': this.dataForm.statMethod,
              'executor': this.$store.state.user.name

            })
          }).then(({ data }) => {
            console.log('data=' + data + 'data.status=' + data.status)
            if (data && data.status === 200) {

              this.$emit("done",data.data);
              //  this.tData.push({ Id: this.num, z: this.dataForm.z, x: this.dataForm.x, y: this.dataForm.y, dx: this.dataForm.dx, dy: this.dataForm.dy, px: this.dataForm.px, py: this.dataForm.py, Fz: this.dataForm.fz, Inn: this.dataForm.inn, Uuid: this.dataForm.uuid });
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false

                }
              })
            } else {
               this.$emit("fail");
              this.$message.error(data.msg)

            }
          })
        }
      })
    },
    loadtasks() {
      console.log("loading data from qws...");
      this.$http({
        url: this.$http.adornUrl('/feynman/server/qws/result'),
        method: "post"
      }).then(({ data }) => {
        console.log(data.data);
        this.tData = data.data
      });
    },
    changeStatisticalMethod(e1) {
      this.$message('Choose Statistical Method "' + e1 + '"');
      this.dataForm.statisticalMethod = e1
    },
    showColorbar(e3) {
      this.$message('Choose Colorbar "' + e3 + '"');
      this.colorbar = e3;
      this.MPQW_Path5 = window.SITE_CONFIG.cdnUrl + '/static/img/colorbar_' + e3 + '.png'
    },
    // Choose photon number
    showPhotonNum(e4) {
      this.$message('Choose photon number "' + e4 + '"');
      this.dataForm.photonNumber = e4
    },

  },
  mounted() {

  },

  computed: {



  }
};
</script>

<style>
.el-input {
  width: 200px;
}
.el-button {
  width: 140px;
}
</style>