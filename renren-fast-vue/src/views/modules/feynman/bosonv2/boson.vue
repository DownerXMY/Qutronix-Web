<template>

  <div class="mod-config">
    <div class="btnDiv">
      <el-button type="primary" @click="showParameterset()">Parameter Set</el-button>
      <el-button type="primary" @click="showFeynmanTask()">Task</el-button>
    </div>

    <el-row :gutter="20">
      <el-col :span="22">
        <QImage></QImage>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="22">
        <QswsView></QswsView>
      </el-col>
    </el-row>
    <drawer title="Parameter Set" :display.sync="parameterSet.display" :inner="true" :width="parameterSet.drawerWidth"
      :mask="false">
      <qws-para-set @closeDrawer="closeDrawer" @showDrawPoint="showDrawTablePoint"></qws-para-set>
    </drawer>

    <drawer ref="feynmanTaskSetDrawer" title="Task" :display.sync="feynmanTask.display" :inner="true"
      :width="feynmanTask.drawerWidth" :mask="false">
      <feynman-task ref="feynmanTask" :taskType='taskType' @closeDrawer="closeDrawer"></feynman-task>
    </drawer>
     <DrawTablePoint1 v-if="drawTablePoint1Visible" ref="drawTablePoint1" @refreshDrawData1="getDrawData1"></DrawTablePoint1>
             <DrawTablePoint2 v-if="drawTablePoint2Visible" ref="drawTablePoint2" @refreshDrawData2="getDrawData2"></DrawTablePoint2>
             <DrawTablePoint3 v-if="drawTablePoint3Visible" ref="drawTablePoint3" @refreshDrawData3="getDrawData3"></DrawTablePoint3>
  </div>

</template>
<script>
import QImage from './boson_img'
import QswsView from './boson_view'
import Colorbar from '../colorbar'
import FeynmanTask from '../feynmanTask'
import drawer from '@/components/drawer/drawer'
import QwsParaSet from './boson_para'
import QwsManualSet from './boson_manual'
  import DrawTablePoint1 from '../draw1'
  import DrawTablePoint2 from '../draw2'
  import DrawTablePoint3 from '../draw3'
export default {
  data() {
    return {

      taskType: 'boson',
      drawTablePointVisible: false,
      parameterSet: {
        display: false,
        drawerWidth: '700px',
      },
      manualSet: {
        display: false,
        drawerWidth: '700px',
      },
      feynmanTask: {
        display: false,
        drawerWidth: '700px',
      },
      drawTablePoint1Visible: false,
      drawTablePoint2Visible:false,
      drawTablePoint3Visible:false,
    };

  },
  components: {
    QImage,
    QswsView,
    Colorbar,
    FeynmanTask,
    drawer,
    DrawTablePoint1,
    DrawTablePoint2,
    DrawTablePoint3,
    QwsParaSet,
    QwsManualSet
  },
  methods: {
    // 获取数据列表
    getDrawData(coordinates) {
      console.log("qws getDrawData");
      this.tabledata = coordinates
      this.tabledata = this.tabledata.map(item => ({ x: (item[0] - 300) * 0.5, y: (item[1] - 300) * 0.5 }))
      console.log(this.tabledata);
    },
    closeDrawer() {
      this.parameterSet.display = false;
      this.manualSet.display = false;
      this.feynmanTask.display = false;
    },
    showParameterset() {
      this.manualSet.display = false
      this.feynmanTask.display = false
      this.parameterSet.display = true

    },
     showDrawPint() {
      this.parameterSet.display = false
      this.feynmanTask.display = false
      this.manualSet.display = true
    },
    showFeynmanTask() {
      this.parameterSet.display = false

      this.manualSet.display = false
      this.feynmanTask.display = true
      this.$nextTick(() => {
        this.$refs.feynmanTask.getDataList();
      })
},
     showDrawTablePoint(feature,inputNum) {
       console.log(feature+":"+inputNum)
      if (feature == 'Reck') {
        this.drawTablePoint1Visible = true;
        this.$nextTick(() => {
          this.$refs.drawTablePoint1.inti(inputNum);
        })
      } else {
        if (inputNum % 2 == 0) {
          this.drawTablePoint2Visible = true;
          this.$nextTick(() => {
            this.$refs.drawTablePoint2.inti(inputNum);
          })
        } else {
          this.drawTablePoint3Visible = true;
          this.$nextTick(() => {
            this.$refs.drawTablePoint3.inti(inputNum);
          })
        }
      }
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => { });
    }
  },
  computed: {
    tabledata: {
      get() { return this.$store.state.feynmandata.qwsTabledata },
      set(val) {
        this.$store.commit('feynmandata/updateQwsTabledata', val)
      }
    }
  }
}
</script>

<style>
.site-content > .el-tabs > .el-tabs__content {
  padding: 0px 0px 0px 0px;
  background: bisque;
}
.mod-config {
  padding: 20px 0px 20px 20px;
  /*  rgb(253, 253, 253);*/
  background: rgb(223, 214, 214);
  height: 900px;
}
.el-card__body {
  padding: 0px 0px 0px 0px;
}
.btnDiv {
  padding: 0px 0px 0px 70px;
}
main.el-main {
  width: 800px;
}
aside.el-aside {
  padding: 20px 0px 0px 0px;
}
.el-button--primary {
  color: #fff;
  font-size: 20px;
  width: 160px;
  height: 50px;
  background-color: #42876c;
  border-color: #42876c;
  padding: 10px 10px 10px 10px;
}
.el-button--primary:hover {
  width: 160px;
  height: 50px;
  background-color: #e17c57;
  border-color: #e17c57;
  padding: 10px 10px 10px 10px;
}
.el-button--primary:focus {
  width: 160px;
  height: 50px;
  background-color: #e17c57;
  border-color: #e17c57;
  padding: 10px 10px 10px 10px;
}
.el-button.el-button--primary.el-button--medium {
  width: 160px;
  height: 50px;
  padding: 10px 10px 10px 10px;
}
.el-button + .el-button {
  margin-left: 30px;
}
</style>
