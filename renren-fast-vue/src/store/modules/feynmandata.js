export default {
    namespaced: true,
    state: {
      dx:"",
      qws_colorbar: "colorbar3",
      qws_img:"",
      qswsImg:"",
      qswsView:"",
      bosonImg:"",
      bosonView:"",

      imgloading:"",
      imgloadingQsws:"",
      imgloadingQswsView:"",
      imgloadingBoson:"",
      imgloadingBosonView:"",

      qwsimgcontainer:"",
      qwsimgcontainerQsws:"",
      qwsimgcontainerBoson:"",

      qwsimgcontainerQswsView:"",
      qwsimgcontainerBosonView:"",

      qwsHisData:[],
      qwsTabledata: ''
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
      updateQswsImg (state,qswsImg) {
        state.qswsImg = qswsImg
      },
      updateQswsView (state,qswsView) {
        state.qswsView = qswsView
      },
      updateQwsHisData (state,hisdata) {
        state.qwsHisData = hisdata
      },
      updateQwsTabledata (state,qwsTabledata) {
        state.qwsTabledata = qwsTabledata
      },
      updateQwsimgcontainer (state,qwsimgcontainer) {
        state.qwsimgcontainer = qwsimgcontainer
      },
      updateImgloading (state,imgloading) {
        state.imgloading = imgloading
      },
    
    }
  }
  