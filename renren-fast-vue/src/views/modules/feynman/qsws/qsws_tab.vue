<template>
  <div class="div_tab">
    <el-tabs v-model="activeName">
      <el-tab-pane label="Parameter Set" name="first" :key="'first'">
        <child1></child1>
      </el-tab-pane>

      <el-tab-pane label="Manually Set" name="second" :key="'second'">
        <child2></child2>
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>
import tabZujianChild1 from './qsws_para'
import tabZujianChild2 from './qsws_manual'
export default {
  name: 'tabZujian',
  components: {
    child1: tabZujianChild1,
    child2: tabZujianChild2
  },
  data() {
    return {
      //默认第一个选项卡
      activeName: "first",
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

  }
}
</script>
<style >
.div_tab {
  padding-left: 10px;
  padding-right: 20px;
  padding-top: 10px;
  width: 680px;
  height: 740px;
  background: white;
}
</style>