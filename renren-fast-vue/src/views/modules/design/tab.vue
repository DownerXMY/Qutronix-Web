<template>
    <div style="padding-left: 10px; padding-right: 10px;">
    <el-card shadow="never" class="card">
        <el-tabs v-model="activeName">
            <el-tab-pane label="Configuration Parameter" name="first" :key="'first'">
                <child1></child1>
            </el-tab-pane>
    
            <el-tab-pane label="Manually Set" name="second" :key="'second'">
                <child2></child2>    
            </el-tab-pane>
        </el-tabs>
     </el-card>
  </div>
</template>

<script>
import tabZujianChild1 from './child1'
import tabZujianChild2 from './child2'
export default {
    name: 'tabZujian',
    components:{
        child1:tabZujianChild1,
        child2:tabZujianChild2
    },
    data() {
        return {
            //默认第一个选项卡
            activeName: "first",
        }
    },
    mounted(){
        
    }
}
</script>