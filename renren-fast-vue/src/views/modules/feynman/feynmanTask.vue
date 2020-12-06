 <template>
  <el-table :data="dataList" height="700px" border empty-text="No Task" style="width: 100%" v-loading="dataListLoading">
    <el-table-column prop="id" label="Task Id" width="80px">
    </el-table-column>
    <el-table-column prop="remark" label="Status" width="80px">
    </el-table-column>
    <el-table-column prop="beginTime" label="Begin Time">
    </el-table-column>
    <el-table-column prop="endTime" label="End Time">
    </el-table-column>
    <el-table-column prop="" label="Result">
      <template slot-scope="scope">
        <el-button type="text" @click="takeDraw(scope.row)" v-if="scope.row.status==1">See</el-button>
      </template>
    </el-table-column>
    <el-table-column prop="taskId" label="" width="60" v-if="false">
    </el-table-column>
    <el-table-column prop="status" label="" width="60" v-if="false">
    </el-table-column>

  </el-table>

</template>

  <script>
export default {
  data() {
    return {
      dataListLoading: false,
      dataList: [],

    }

  },
  props: {
    taskType: {
      Type: String,
      default: null,
    }
  },
  mounted() {
    console.log("feynman task mounted..")
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl(`/feynman/task/list/all`),
        method: 'post',
        data: this.$http.adornData({
          'taskType': this.taskType,
          'executor': this.$store.state.user.name,

        })
      }).then(({ data }) => {
        console.log(data)
        if (data && data.status === 200) {
          this.dataList = data.data

        } else {
          this.dataList = []
        }
        this.dataListLoading = false
      })
    },
    takeDraw(row) {
      console.log(row.taskId);
      this.$store.state.feynmandata.qws_img = this.$http.adornUrl(`/feynman/server/result?fileName=` + row.taskId);
      this.$store.state.feynmandata.qwsimgcontainer.style.display = "block";
      this.$store.state.feynmandata.imgloading.style.display = "none";

    },
  },
  computed: {

    tData2: {
      get() { return this.$store.state.feynmandata.qwsHisData }

    }
  }
}

  </script>