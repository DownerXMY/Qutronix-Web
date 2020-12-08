<template>
  <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
    label-width="220px">

    <el-form-item label="Inject num" label-width="260px">
      <el-input v-model="dataForm.inputNum"></el-input>
    </el-form-item>
    <el-form-item label="δβ Amplitude" label-width="260px">
      <el-input v-model="dataForm.amplitude"></el-input>
    </el-form-item>
    <el-form-item label="Z Interval(nm)" label-width="260px">
      <el-input v-model="dataForm.gap"></el-input>
    </el-form-item>
    <el-form-item label="From" label-width="260px">
      <el-input v-model="dataForm.fData"></el-input>
    </el-form-item>
    <el-form-item label="To" label-width="260px">
      <el-input v-model="dataForm.tData"></el-input>
    </el-form-item>

    <el-form-item label="View Probability For Node No" label-width="260px">
      <el-input v-model="dataForm.nodeId"></el-input>
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
        'inputNum': 13,
        'amplitude': 1,
        'gap': 0.1,
        'fData': 2,
        'tData': 5,
        'nodeId': '13,1',
        'times': 10,
        uuid: '',
        tabledata: ''
      }
    }
  },
  methods: {
    // 表单提交
    plot() {
      this.$refs['dataForm'].validate((valid) => {

        if (valid) {
          this.$store.state.feynmandata.qwsimgcontainerQsws.style.display = "none";
          this.$store.state.feynmandata.qwsimgcontainerQswsView.style.display = "none";
          this.$store.state.feynmandata.imgloadingQsws.style.display = "block";
          this.$store.state.feynmandata.imgloadingQswsView.style.display = "block";
          this.qwsPath = this.$http({
            url: this.$http.adornUrl(`/feynman/server2/plot3`),
            method: 'post',
            data: this.$http.adornData({
              'uuid': getUUID(),
              'inputNum': this.dataForm.inputNum,
              'amplitude': this.dataForm.amplitude,
              'gap': this.dataForm.gap,
              'fdata': this.dataForm.fData,
              'tdata': this.dataForm.tData,
              'nodeId': this.dataForm.nodeId,
              'times': this.dataForm.times,
              'colorbar': "colorbar_" + this.colorbar,
              'executor': this.$store.state.user.name

            })
          }).then(({ data }) => {
            console.log('data=' + data + 'data.status=' + data.status)
            if (data && data.status === 200) {

              this.num = this.num + 1;
              this.qswsImg = this.$http.adornUrl(`/feynman/server/result?fileName=` + data.data.fileName);
              this.$store.state.feynmandata.qswsView = this.$http.adornUrl(`/feynman/server/result?fileName=` + data.data.figure1);
              this.$store.state.feynmandata.imgloadingQsws.style.display = "none";
              this.$store.state.feynmandata.imgloadingQswsView.style.display = "none";
              this.$store.state.feynmandata.qwsimgcontainerQsws.style.display = "block";
              this.$store.state.feynmandata.qwsimgcontainerQswsView.style.display = "block";

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
              this.$store.state.feynmandata.imgloadingQsws.style.display = "none";
              this.$store.state.feynmandata.imgloadingQswsView.style.display = "none";
              this.$store.state.feynmandata.qwsimgcontainerQsws.style.display = "none";
              this.$store.state.feynmandata.qwsimgcontainerQswsView.style.display = "none";
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
    }

  },
  mounted() {

  },

  computed: {
    colorbar: {
      get() { return this.$store.state.feynmandata.qws_colorbar },

    },
    qswsImg: {
      set(val) {
        this.$store.commit('feynmandata/updateQswsImg', val)
      }
    },
    tData: {
      get() { return this.$store.state.feynmandata.qwsHisData },
      set(val) {
        this.$store.commit('feynmandata/updateQwsHisData', val)
      }
    }, qwsimgcontainer: {
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
  width: 200px;
}
.el-button {
  width: 140px;
}
</style>