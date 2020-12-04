<template>

  <div class="mod-config">
    <el-row :gutter="20">
      <el-col :span="14">
        <el-container>
          <el-main style="padding:0px;">
            <QImage></QImage>
          </el-main>

          <el-aside width="120px">
            <Colorbar></Colorbar>
          </el-aside>
        </el-container>
      </el-col>

      <el-col :span="10">
        <QTab></QTab>
      </el-col>
    </el-row>
    <el-row :gutter="20">
       <el-col :span="24">
        <QResult></QResult>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import QImage from './qws_img'
import QTab from './qws_tab'
import Colorbar from '../colorbar'
import QResult from './qws_result'
export default {
  components: {
    QImage,
    QTab,
    Colorbar,
    QResult
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
  height: auto;
}
.el-card__body {
  padding: 0px 0px 0px 0px;
}
</style>
