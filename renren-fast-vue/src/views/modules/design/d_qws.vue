<template>

  <div class="mod-config">
    <el-row :gutter="20">
      <el-col :span="14" >
        <el-container>
          <el-main  style="padding:0px;">
            <QImage></QImage>
          </el-main>

          <el-aside width="120px" >
            <Colorbar></Colorbar>
          </el-aside>
        </el-container>
      </el-col>

      <el-col :span="10">
        <QTab></QTab>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import QImage from './d_qws_img'
import QTab from './d_qws_tab'
import Colorbar from './d_colorbar'
export default {
  components: {
    QImage,
    QTab,
    Colorbar
  }
}
</script>

<style>
.site-content > .el-tabs > .el-tabs__content {
    padding: 0px 0px 0px 0px;
    background: bisque;
}
.mod-config{
  padding: 20px 0px 0px 0px;
  /*  rgb(253, 253, 253);*/
   background: rgb(223, 214, 214);;
}
.el-card__body{
   padding: 0px 0px 0px 0px;
}


</style>
