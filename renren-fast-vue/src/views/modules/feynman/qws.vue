<template>
  <el-container style="height: 500px; border: 1px solid #eee">

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <span>Two Dimensional Quantum Walks</span>
      </el-header>

      <el-main>
        <img :src="qwsPath">
      </el-main>
    </el-container>
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
        label-width="120px">
        <el-form-item label="Prpagation Distance z(mm)" prop="z">
          <el-input v-model="dataForm.z" placeholder="Prpagation Distance z(mm)"></el-input>
        </el-form-item>
      </el-form>
    </el-aside>

  </el-container>
</template>

<script>
  import { getUUID } from '@/utils'
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          z: 5,
          uuid: '',
        },
        qwsPath: '',
        dataRule: {
          z: [
            { required: true, message: 'Prpagation Distance z(mm)', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
       // 获取验证码
      getQwsResult () {
        this.dataForm.uuid = getUUID()
        this.captchaPath = this.$http.adornUrl(`/captcha.jpg?uuid=${this.dataForm.uuid}`)
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/order/order/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'z': this.dataForm.z,
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
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
      }
    }
  }
</script>
