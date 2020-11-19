<template>
  <el-container style="height: 1000px; border: 1px solid #eee">

    <el-container>
      <el-header style="text-align: center; font-size: 32px">
        <span>Two Dimensional Quantum Walks</span>
      </el-header>

      <el-main>
        <img :src="qwsPath" style="height: 600px;width:600px; border: 1px solid #eee">
      </el-main>
    </el-container>
    <el-aside width="600px" style="background-color: rgb(238, 241, 246)">
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
        label-width="220px">
        <el-row :gutter="20">
          <el-col :span="24" :offset="0">
            <div style="text-align: center; font-size: 22px">A first try of the regular 2d quantum</div>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top  : 10px;">
          <el-col :span="12" :offset="6">
            <el-button type="success" round>An example of 2d quantum walks</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="Prpagation Distance " label-width="200px">

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="z(mm)" prop="z" label-width="60px">
              <el-input v-model="dataForm.z" style="width: 120px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">

          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="Num of nodes in x or y:" label-width="200px">

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="(x)" prop="x" label-width="60px">
              <el-input v-model="dataForm.x" style="width: 120px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="(y)" prop="y" label-width="60px">
              <el-input v-model="dataForm.y" style="width: 120px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="Inject point of x or 1y:" label-width="200px">

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="(x)" prop="px" label-width="60px">
              <el-input v-model="dataForm.px" style="width: 120px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="(y)" prop="py" label-width="60px">
              <el-input v-model="dataForm.py" style="width: 120px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="Waveguide spacing in x direction (um):" label-width="200px">

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="(x)" prop="dx" label-width="60px">
              <el-input v-model="dataForm.dx" style="width: 120px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="(y)" prop="dy" label-width="60px">
              <el-input v-model="dataForm.dy" style="width: 120px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" style="margin-bottom: 10px;margin-top:10px;" justify="center">
          <el-col :span="5" justify="center">
            <el-button type="success" round>Plot2</el-button>
          </el-col>
          <el-col :span="5">
            <el-button type="success" round @click="plot()">Plot Quickly</el-button>
          </el-col>
        </el-row>

      </el-form>
    </el-aside>

  </el-container>
</template>

<script>
import { getUUID } from '@/utils'
export default {
  data() {
    return {
      visible: false,
      dataForm: {
        z: 5,
        'x': 21,
        'y': 21,
        'px': 11,
        'py': 11,
        'dx': 15,
        'dy': 15,
        uuid: ''
      },
      qwsPath: '',
      dataRule: {
        z: [
          { required: true, message: 'Prpagation Distance z(mm)', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getQwsResult()
  },
  methods: {
    // 获取结果
    getQwsResult() {
      this.dataForm.uuid = getUUID()
      this.qwsPath = this.$http.adornUrl(`/feynman/server/result?fileName=tt&uuid=${this.dataForm.uuid}`)
    },
    // 表单提交
    plot() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.qwsPath = this.$http({
            url: this.$http.adornUrl(`/feynman/server/plot`),
            method: 'post',
            data: this.$http.adornData({
              'uuid': getUUID(),
              'z': this.dataForm.z,
              'x': this.dataForm.x,
              'y': this.dataForm.y,
              'px': this.dataForm.px,
              'py': this.dataForm.py,
              'dx': this.dataForm.dx,
              'dy': this.dataForm.dy

            })
          }).then(({ data }) => {
            console.log('data=' + data + 'data.status=' + data.status)
            if (data && data.status === 200) {
              this.qwsPath = this.$http.adornUrl(`/feynman/server/result?fileName=` + data.data.fileName)
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {

        }
      })
    }
  }
}
</script>

<style>
.el-row {
  margin-bottom: 0px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 0px 0;
  background-color: #f9fafc;
}
</style>
