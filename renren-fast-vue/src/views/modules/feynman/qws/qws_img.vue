<template>
  <div class="div_imgmain">
    <el-card shadow="never" class="card" style="width:700px;height:740px">
      <div slot="header" class="clearfiximg" align="center">
        <span class="title">Two Dimensional Quantum Walks</span>
      </div>

      <div align="center" style="width:700px;height:700px">
        <img :src="qwsimg" style="width:auto;height:auto;" class="qwsimgcontainer" ref="qwsimgcontainer">
        <img :src="bsPath" style="width:600px;height:600px" ref="imgloading">
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'd_qws_img',

  data() {
    return {
      bsPath: window.SITE_CONFIG.cdnUrl + '/static/img/' + 'loading.gif',
    }
  },
  computed: {
    userName: {
      get() { return this.$store.state.user.name }
    }, dx: {
      get() { return this.$store.state.feynmandata.dx },
      set(val) {
        this.$store.commit('feynmandata/updateDx', val)
      }

    }
  },
  mounted() {
    console.log("d_qws_img组件")
    this.$refs.imgloading.style.display = "none";//block
    this.$refs.qwsimgcontainer.style.display = "none";//block
    this.$store.state.feynmandata.imgloading = this.$refs.imgloading
    this.$store.state.feynmandata.qwsimgcontainer = this.$refs.qwsimgcontainer

  }
  ,
  computed: {
    qwsimg: {
      get() { return this.$store.state.feynmandata.qws_img },
    }
  }
}
</script>
<style >
.title {
  font: bold 30px/40px arial, sans-serif, "宋体";
  padding: 0px;
}
.clearfiximg {
  padding: 0px;
}
.div_imgmain {
  padding-left: 10px;
  padding-right: 10px;
  height: 760px;
}
.qwsimgcontainer {
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
}
.qwsLoading {
  height: "600px";
  width: "600px";
  border: "1px solid #eee";
}
</style>