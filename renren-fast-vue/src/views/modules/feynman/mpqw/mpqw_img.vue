<template>
  <div class="div_imgmain">
    <el-card shadow="never" class="card" style="height:740px">
      <div slot="header" class="clearfiximg" align="center">
        <span class="title">2D Quantum Stochastic Walks</span>
      </div>

      <div align="center" style="height:700px">
        <img src="~@/assets/img/tt.png" style="width:auto;height:auto;%">
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