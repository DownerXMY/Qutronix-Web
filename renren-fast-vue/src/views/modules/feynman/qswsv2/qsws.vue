<template>

  <div class="mod-config">
    <div class="btnDiv">
      <el-button type="primary" @click="showParameterset()">Parameter Set</el-button>
      <el-button type="primary" @click="showFeynmanTask()">Task</el-button>
    </div>
     <el-row :gutter="20">
      <el-col :span="11">   
             <QImage></QImage>
      </el-col>
    <el-col :span="2"> 
      <div style="padding-top:20px">
           <Colorbar></Colorbar>
           </div>
     </el-col>
      <el-col :span="11"> 
         <QswsView></QswsView>
      </el-col>
    </el-row>
 

    <drawer title="Parameter Set" :display.sync="parameterSet.display" :inner="true" :width="parameterSet.drawerWidth"
      :mask="false">
      <qws-para-set  @closeDrawer="closeDrawer"></qws-para-set>
    </drawer>
    <drawer ref="manualSetDrawer" title="Manually Set" :display.sync="manualSet.display" :inner="true"
      :width="manualSet.drawerWidth" :mask="false">
      <qws-manual-set @showDrawTablePoint="showDrawTablePoint"  @closeDrawer="closeDrawer"></qws-manual-set>
    </drawer>
    <drawer ref="feynmanTaskSetDrawer" title="Task" :display.sync="feynmanTask.display" :inner="true"
      :width="feynmanTask.drawerWidth" :mask="false">
      <feynman-task ref="feynmanTask" :taskType='taskType'  @closeDrawer="closeDrawer"></feynman-task>
    </drawer>
    <drawTablePoint v-if="drawTablePointVisible" ref="drawTablePoint" @refreshDrawData="getDrawData"></drawTablePoint>
  </div>

</template>
<script>
import QImage from './qsws_img'
import QswsView from './qsws_view'
import Colorbar from '../colorbar'
import FeynmanTask from '../feynmanTask'
import drawer from '@/components/drawer/drawer'
import DrawTablePoint from '../draw'
import QwsParaSet from './qsws_para'
import QwsManualSet from './qsws_manual'
export default {
  data() {
    return {
      taskType: 'qsws',
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
      drawTablePointVisible: false,
    };

  },
  components: {
    QImage,
    QswsView,
    Colorbar,
    FeynmanTask,
    drawer,
    DrawTablePoint,
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
      closeDrawer(){
      this.parameterSet.display=false;
      this.manualSet.display=false;
      this.feynmanTask.display=false;
    },
    showParameterset() {
      this.manualSet.display = false
      this.feynmanTask.display = false
      this.parameterSet.display = true

    },
    showManualSet() {
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
    showDrawTablePoint() {
      console.log("qws drawTablePoint")
      this.drawTablePointVisible = true
      this.$nextTick(() => {
        this.$refs.drawTablePoint.inti();
      })
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
