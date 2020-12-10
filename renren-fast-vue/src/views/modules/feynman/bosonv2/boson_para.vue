<template>
  <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
    label-width="220px">

    <el-form-item label="Num of Nodes" label-width="260px">
      <el-input v-model="dataForm.inputNum"></el-input>
    </el-form-item>
    <el-form-item label="Initial State" label-width="260px">
      <el-input v-model="dataForm.iniState"></el-input>
    </el-form-item>
    <el-form-item label="Choose decomposition style" label-width="260px">
      <el-select v-model="value" placeholder="" @change="showMessage($event)">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <div align="center" style="width:800px;height:200px">
      <img :src="decompositionImg" style="width:300px;height:200px">
    </div>

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
        'inputNum': 12,
        'iniState': '|000000000111>',
        'alpha': '',
        'varphi': '',
        'feature': 'Reck',
        uuid: '',
        tabledata: ''
      },
      options: [{
        value: 'Reck',
        label: 'Reck',
        feature: 'Reck'
      }, {
        value: 'Clements',
        label: 'Clements',
        feature: 'Clements'
      }],
      value: 'Reck',
      decompositionImg: window.SITE_CONFIG.cdnUrl + '/static/img/' + 'Reck_example_2.png',
    }
  },
  methods: {
    // 表单提交
    plot() {
      this.$refs['dataForm'].validate((valid) => {
      
        if (valid) {
            this.$emit("closeDrawer")

          this.$store.state.feynmandata.qwsimgcontainerBoson.style.display = "none";
          this.$store.state.feynmandata.qwsimgcontainerBosonView.style.display = "none";
          this.$store.state.feynmandata.imgloadingBoson.style.display = "block";
          this.$store.state.feynmandata.imgloadingBosonView.style.display = "block";

          this.$http({
            url: this.$http.adornUrl(`/feynman/server3/example`),
            method: 'post',
            data: this.$http.adornData({
              'uuid': getUUID(),
              'inputNum': this.dataForm.inputNum,
              'iniState': this.dataForm.iniState,
              'feature': this.dataForm.feature,
              'colorbar': this.colorbar,
              'executor': this.$store.state.user.name

            })
          }).then(({ data }) => {
            console.log('data=' + data + 'data.status=' + data.status)
            if (data && data.status === 200) {

              this.$store.state.feynmandata.bosonImg = this.$http.adornUrl(`/feynman/server/result?fileName=` + data.data.distribution);

              this.$store.state.feynmandata.bosonView = this.$http.adornUrl(`/feynman/server/result?fileName=` + data.data.waveguides);

              this.$store.state.feynmandata.imgloadingBoson.style.display = "none";
              this.$store.state.feynmandata.imgloadingBosonView.style.display = "none";
              this.$store.state.feynmandata.qwsimgcontainerBoson.style.display = "block";
              this.$store.state.feynmandata.qwsimgcontainerBosonView.style.display = "block";
              //  this.tData.push({ Id: this.num, z: this.dataForm.z, x: this.dataForm.x, y: this.dataForm.y, dx: this.dataForm.dx, dy: this.dataForm.dy, px: this.dataForm.px, py: this.dataForm.py, Fz: this.dataForm.fz, Inn: this.dataForm.inn, Uuid: this.dataForm.uuid });

              this.loadtasks();
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
              this.$store.state.feynmandata.imgloadingBoson.style.display = "none";
              this.$store.state.feynmandata.imgloadingBosonView.style.display = "none";
              this.$store.state.feynmandata.qwsimgcontainerBoson.style.display = "none";
              this.$store.state.feynmandata.qwsimgcontainerBosonView.style.display = "none";
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
    }, showMessage(e) {
      this.dataForm.feature = e;
      this.decompositionImg = window.SITE_CONFIG.cdnUrl + '/static/img/' + e + '_example_2.png'
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