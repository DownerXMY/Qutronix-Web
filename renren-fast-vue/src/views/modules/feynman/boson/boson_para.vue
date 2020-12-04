<template>
  <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
    label-width="220px">

    <el-form-item label="Choose Decomposition Style">
      <el-input v-model="dx"></el-input>
    </el-form-item>
    <el-form-item label="Num of Nodes:">
      <el-input v-model="dataForm.x"></el-input>
    </el-form-item>
    <el-form-item label="Initial State:">
      <el-input v-model="dataForm.y"></el-input>
    </el-form-item>
    <el-form-item label="">
      <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top:10px;" justify="center">
        <el-col :span="10" justify="center">
          <el-button type="success">Plot</el-button>
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
export default {
  data() {
    return {
      dataForm: {
        z: 5,
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

  },
  mounted() {

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