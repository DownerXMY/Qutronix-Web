export default {
    namespaced: true,
    state: {
      dx:"",
      qws_colorbar: "colorbar3",
      qws_img:"",
      imgloading:"",
      qwsimgcontainer:"",
      qwsHisData:[],
    },
    mutations: {
      updateDx (state, dx) {
        state.dx = dx
      },
      updateQwsColorbar (state,colorbar) {
        state.qws_colorbar = colorbar
      },
      updateQwsImg (state,img) {
        state.qws_img = img
      },
      updateQwsHisData (state,hisdata) {
        state.qwsHisData = hisdata
      },
    
    }
  }
  