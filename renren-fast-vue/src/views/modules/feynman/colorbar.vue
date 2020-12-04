<template>
  <div class="div_colorbar">
    <el-card shadow="never" class="card">
      <div slot="header" class="clearfiximg" align="center">
        <el-dropdown @command="colorTypeSelect">
          <span class="el-dropdown-link">
            {{colorType}}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-for="item in options"
                              v-text="item.label"
                              :command="item.value"
                              :key="item.value"
            ></el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <img :src="colorbarPath" class="img_colorbar">
    </el-card>

  </div>
</template>
<script>
export default {
  data() {
    return {
      options: [{
        value: 'colorbar3',
        label: 'colorbar3'
      }, {
        value: 'hot',
        label: 'hot'
      }, {
        value: 'colorbar',
        label: 'colorbar'
      }, {
        value: 'rainbow',
        label: 'rainbow'
      }, {
        value: 'CMRmap',
        label: 'CMRmap'
      }, {
        value: 'hot_r',
        label: 'hot_r'
      }, {
        value: 'RdYlBu_r',
        label: 'RdYlBu_r'
      }, {
        value: 'coolwarm',
        label: 'coolwarm'
      }],
      value: '',
      MPQW_Path4: '',
      colorType: 'rainbow'
    }
  },
  methods: {
    colorTypeSelect(type) {  // 下拉列表的点击事件
      this.colorType = type
       this.colorbarPath = window.SITE_CONFIG.cdnUrl + '/static/img/colorbar_'+type+'.png'
    }
  }
}
</script>
<style >
.div_colorbar {
  padding-left: 0px;
  padding-right: 0px;
  padding-top: 20px;
  width: 100%;
  height: 740px;
  background: white;
  border: 0px solid #eee;
  border-style: dashed;
}
.img_colorbar {
  /*height: 665px;
  width: 40%;*/
  width: auto;
  height: auto;
  border: 0px solid #eee;
}
</style>