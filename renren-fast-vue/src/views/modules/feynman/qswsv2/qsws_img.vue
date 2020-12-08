<template>
  <div class="div_imgmain" style="width:650px;height:800px">
    <el-card shadow="never" class="card" >
      <div slot="header" class="clearfiximg" align="center">
        <span id="text3d">2D Quantum Stochastic Walks</span>
      </div>

      <div align="center" style="width:650px;height:650px">
        <img :src="qswsImg" style="width:auto;height:auto;" class="qwsimgcontainer" ref="qwsimgcontainer">
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
    this.$store.state.feynmandata.imgloadingQsws = this.$refs.imgloading
    this.$store.state.feynmandata.qwsimgcontainerQsws = this.$refs.qwsimgcontainer

  }
  ,
  computed: {
    qswsImg: {
      get() { return this.$store.state.feynmandata.qswsImg },
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
  height: "640px";
  width: "600px";
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
.div.el-card__header{
     padding: 0px 0px 0px 0px;
}
#text3d {
     color: #70869d;
     font: bold 30px/0px arial, sans-serif, "宋体";
    letter-spacing: .1em;
    text-shadow: 
      -1px -1px 1px #efede3, 
      0px 1px 0 #2e2e2e, 
      0px 2px 0 #2c2c2c, 
      0px 3px 0 #2a2a2a,
      0px 4px 0 #282828, 
      0px 5px 0 #262626, 
      0px 6px 0 #242424;
 }
</style>