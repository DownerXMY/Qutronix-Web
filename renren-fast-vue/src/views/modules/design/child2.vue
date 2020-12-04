<template>
   <div>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card shadow="never" class="card">
          <div slot="header" class="clearfix">
            <span>柱状图动画延迟</span>
          </div>
          <div id="charts_one" style="width:100%;height:300px"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="never" class="card">
          <div slot="header" class="clearfix">
            <span>柱状图框选</span>
          </div>
          <div id="charts_two" style="width:100%;height:300px"></div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20" style="margin-top:20px">
      <el-col :span="12">
        <el-card shadow="never" class="card">
          <div slot="header" class="clearfix">
            <span>柱状图正负图形</span>
          </div>
          <div id="charts_three" style="width:100%;height:300px"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="never" class="card">
          <div slot="header" class="clearfix">
            <span>柱状图堆叠</span>
          </div>
          <div id="charts_four" style="width:100%;height:300px"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    export default {
      name: 'child1',
      mounted(){
          console.log("tab2组件")
      }
    }
</script