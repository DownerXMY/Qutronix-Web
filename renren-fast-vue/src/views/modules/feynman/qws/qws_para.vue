<template>
  <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
    label-width="220px">

    <el-form-item label="Prpagation Distance z(mm)" label-width="260px">
      <el-input v-model="dataForm.dx"></el-input>
    </el-form-item>
    <el-form-item label="Num of nodes in x" label-width="260px">
      <el-input v-model="dataForm.x"></el-input>
    </el-form-item>
    <el-form-item label="Num of nodes in y" label-width="260px">
      <el-input v-model="dataForm.y"></el-input>
    </el-form-item>
    <el-form-item label="Inject point of x" label-width="260px">
      <el-input v-model="dataForm.px"></el-input>
    </el-form-item>
    <el-form-item label="Inject point of y" label-width="260px">
      <el-input v-model="dataForm.py"></el-input>
    </el-form-item>

    <el-form-item label="Waveguide spacing in x(um)" label-width="260px">
      <el-input v-model="dataForm.dx"></el-input>
    </el-form-item>
    <el-form-item label="Waveguide spacing in y(um)" label-width="260px">
      <el-input v-model="dataForm.dy"></el-input>
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
        'z': 5,
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
      }
    }
  },
  methods: {
    // 表单提交
    plot() {
      this.$refs['dataForm'].validate((valid) => {

        if (valid) {
          this.$emit("closeDrawer")
          this.qwsimgcontainer.style.display = "none";
          this.imgloading.style.display = "block";
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
              'dy': this.dataForm.dy,
              'colorbar': this.colorbar,
              'executor': this.$store.state.user.name

            })
          }).then(({ data }) => {
            console.log('data=' + data + 'data.status=' + data.status)
            if (data && data.status === 200) {

              this.num = this.num + 1;
              this.qwsimg = this.$http.adornUrl(`/feynman/server/result?fileName=` + data.data.fileName);
              this.qwsimgcontainer.style.display = "block";
              this.imgloading.style.display = "none";
              //  this.tData.push({ Id: this.num, z: this.dataForm.z, x: this.dataForm.x, y: this.dataForm.y, dx: this.dataForm.dx, dy: this.dataForm.dy, px: this.dataForm.px, py: this.dataForm.py, Fz: this.dataForm.fz, Inn: this.dataForm.inn, Uuid: this.dataForm.uuid });

              this.loadtasks();
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  

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
    qwsimg: {
      set(val) {
        this.$store.commit('feynmandata/updateQwsImg', val)
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