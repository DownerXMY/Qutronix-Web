 <template>
  <el-table :data="tData2" height="340" border empty-text="No record yet" style="width: 100%">
    <el-table-column prop="Id" label="Id" width="40">
    </el-table-column>
    <el-table-column prop="z" label="z" width="40">
    </el-table-column>
    <el-table-column prop="x" label="x" width="40">
    </el-table-column>
    <el-table-column prop="y" label="y" width="40">
    </el-table-column>
    <el-table-column prop="dx" label="dx" width="60">
    </el-table-column>
    <el-table-column prop="dy" label="dy" width="60">
    </el-table-column>
    <el-table-column prop="px" label="px" width="60">
    </el-table-column>
    <el-table-column prop="py" label="py" width="60">
    </el-table-column>
    <el-table-column prop="Fz" label="Fz" width="60">
    </el-table-column>
    <el-table-column prop="Inn" label="Inn" width="60">
    </el-table-column>
    <el-table-column prop="imgUrl" label="imgUrl" width="80">
      <template slot-scope="scope">
        <el-button size="mini" type="success" @click="takeDraw(scope.row)">plot</el-button>
      </template>
    </el-table-column>
    <el-table-column prop="Uuid" label="Uuid" width="60" v-if="show">
    </el-table-column>
  </el-table>

</template>

  <script>
export default {
  data() {
    return {
      tData: this.tData2
    }

  }, mounted() {

  },
  computed: {

    tData2: {
      get() { return this.$store.state.feynmandata.qwsHisData }

    }
  }
}

  </script>