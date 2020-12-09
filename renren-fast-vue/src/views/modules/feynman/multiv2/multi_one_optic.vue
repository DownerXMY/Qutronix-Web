<template>
 <div class="div_imgmain" style="width:100%;height:350px;padding-top:20px;padding-right:30px">
    <el-card shadow="never" class="card" >

      <div align="center" style="width:100%;height:300px">
        <img :src="multiImg" style="width:100%;height:100%;" class="qwsimgcontainer" ref="qwsimgcontainer">
        <img :src="bsPath" style="width:300px;height:300px" ref="imgloading">
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'multi_one_optic',

  data() {
    return {
      bsPath: window.SITE_CONFIG.cdnUrl + '/static/img/' + 'loading.gif',
      multiImg:""
    }
  },

  mounted() {
    console.log("multi_one_optic")
this.$refs.qwsimgcontainer.style.display = "none";
this.$refs.imgloading.style.display = "none";//block
  }
  ,
 methods: {
    loading() {
          console.log("multi_one_optic loading")
      this.$refs.imgloading.style.display = "block";//block
      this.$refs.qwsimgcontainer.style.display = "none";//blok
    },
    done(fileName) {
        this.multiImg= this.$http.adornUrl(`/feynman/server/result?fileName=` +fileName);
      this.$refs.imgloading.style.display = "none";//block
      this.$refs.qwsimgcontainer.style.display = "block";//blok
    },
      fail(){
       this.$refs.imgloading.style.display = "none";//block
      this.$refs.qwsimgcontainer.style.display = "none";//blok
    }


  },
  computed: {
  
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
.div.el-card__header {
  padding: 0px 0px 0px 0px;
}
#text3d {
  color: #70869d;
  font: bold 30px/0px arial, sans-serif, "宋体";
  letter-spacing: 0.1em;
  text-shadow: -1px -1px 1px #efede3, 0px 1px 0 #2e2e2e, 0px 2px 0 #2c2c2c,
    0px 3px 0 #2a2a2a, 0px 4px 0 #282828, 0px 5px 0 #262626, 0px 6px 0 #242424;
}
</style>