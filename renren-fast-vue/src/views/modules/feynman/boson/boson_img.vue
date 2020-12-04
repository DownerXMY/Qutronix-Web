<template>
  <div class="div_imgmain">
    <el-card shadow="never" class="card" style="height:740px">
      <div slot="header" class="clearfiximg" align="center">
        <span class="title">Boson Sampling</span>
      </div>

      <div align="center" style="width:800px;height:350px;padding:10px">
        <img src="~@/assets/img/tt.png" style="width:800px;height:350px;padding:10px">
      </div>
 <div align="center" style="width:800px;height:200px;padding:10px">
        <img src="~@/assets/img/tt.png" style="width:800px;height:350px;padding:10px" >
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'd_qws_img',
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
</style>