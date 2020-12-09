<template xmlns="http://www.w3.org/1999/html">
  <el-dialog :close-on-click-modal="false" :visible.sync="visible" width="900px">
    <div id="app" class="joint-app joint-theme-modern">
      <div class="app-header">
        <div class="app-title">
          <h1 align="center"></h1>
        </div>
        <div class="toolbar-container">
          <el-button type="info" disabled style="width: 45%">DIY QWS</el-button>
          <button id="clearGraph" style="font-size: 20px; margin-left: 10px">
            Clear All
          </button>
          <button id="makeSure" @click="backToQws()" style="font-size: 20px; margin-left: 10px">
            Confirm
          </button>
        </div>
      </div>
      <div class="app-body">
        <div class="stencil-container" id="stencil"></div>
        <div class="paper-container" id="paper" @mousemove="updateXY"></div>
        <div class="inspector-container">
          <el-table :data="tDataNormal" border empty-text="No point yet" style="width: 100%" height="600px">
            <el-table-column prop="id" label="id" width="40">
            </el-table-column>
            <el-table-column prop="x_coordinate" label="x_coor" width="100">
            </el-table-column>
            <el-table-column prop="y_coordinate" label="y_coor" width="100">
            </el-table-column>
          </el-table>
        </div>
      </div>
      <div class="app-footer"></div>
    </div>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      num: 0,
      tData: [],
      tDataNormal: [],
      tabledata: "",
      coordinates: [],
      visible: false,
      x: 0,
      y: 0,
      active: true,
      graph: new joint.dia.Graph(),
      shapAttrs: {
        cell: null,
        height: 60,
        width: 60,
        color: "",
        text: {
          content: "",
          color: "",
          fontSize: "10",
        },
      },
      linkAttrs: {
        cell: null,
        text: "",
        color: "",
      },
    };
  },
  mounted: function () {
    this.$nextTick(() => {
      // 在此处执行你要执行的函数
      this.inti();
    });
    // this.shaps()
  },
  computed: {
    qwsTabledata: {
      get() { return this.$store.state.feynmandata.qwsTabledata },
      set(val) {
        this.$store.commit('feynmandata/updateQwsTabledata', val)
      }
    }
  },
  created() {
    this.inti();
  },
  methods: {
    updateXY: function (event) {
      this.x = event.offsetX;
      this.y = event.offsetY;
    },
    changeTextFontSize(cell, fontSize) {
      cell.attr("label/fontSize", fontSize);
    },
    changeTextContent(cell, content) {
      cell.attr("label/text", content);
    },
    changeTextColor(cell, color) {
      cell.attr("label/fill", color);
    },
    changeHeight(cell, height) {
      cell.size(this.shapAttrs.width, height);
    },
    changeWidth(cell, width) {
      cell.size(width, this.shapAttrs.height);
    },
    changeColor(cell, color) {
      cell.attr("body/fill", color);
    },
    backToQws() {
      console.log("draw backto qws")
      this.visible = false;
      this.qwsTabledata = this.coordinates
      this.qwsTabledata = this.qwsTabledata.map(item => ({ x: (item[0] - 300) * 0.5, y: (item[1] - 300) * 0.5 }))
      // this.coordinates = []
      // this.tData = []

      this.$emit("refreshDrawData", this.coordinates);
    },

    inti() {
      this.visible = true;
      this.graph = new joint.dia.Graph();
      var paper = new joint.dia.Paper({
        el: $("#paper"),
        width: 600,
        height: 600,
        gridSize: 10,
        drawGrid: true,
        model: this.graph,
        defaultLink: new joint.dia.Link({
          attrs: { ".marker-target": { d: "M 10 0 L 0 5 L 10 10 z" } },
        }),
        // ------------------------
        // ------------------------
        // elementView: joint.dia.ElementView.extend({
        //   //双击删除
        //   pointerdblclick: function (evt, x, y) {
        //     joint.dia.CellView.prototype.pointerdblclick.apply(this, arguments);
        //     this.notify("element:pointerdblclick", evt, x, y);
        //     console.log(this.model.attributes.attrs.label.text-1);
        //     this.model.remove();
        //     console.log(this);
        //   },
        // }),
        // ------------------------
        // ------------------------
      });

      for (var index1=1;index1<=this.tData.length;index1++) {
          var lastingPoint = new joint.shapes.standard.Circle({
            position: { x: this.tData[index1-1].x_coordinate, y: this.tData[index1-1].y_coordinate },
            size: { width: 10, height: 10 },
            attrs: {
              body: { stroke: "black", fill: "#f1f0f1" },
              label: { text: "      " + index1, fontSize: 16 },
            },
          });
          this.graph.addCell(lastingPoint);   
      }

      // Canvas from which you take shapes
      var stencilGraph = new joint.dia.Graph();
      var stencilPaper = new joint.dia.Paper({
        el: $("#stencil"),
        width: 600,
        height: 600,
        model: stencilGraph,
        interactive: false,
      });

      var r2 = new joint.shapes.fsa.State({
        position: { x: 10, y: 10 },
        size: { width: 10, height: 10 },
        attrs: {
          body: { stroke: "blue", fill: "#af9bff" },
          label: { text: "圆点", fontSize: 10 },
        },
      });

      stencilPaper.on("cell:pointerdown", (cellView, e, x, y) => {
        console.log("getCloneOnStencilPaper");
        $("body").append(
          '<div id="flyPaper" style="position:fixed;z-index:100;opacity:.7;pointer-event:none;"></div>'
        );
        var flyGraph = new joint.dia.Graph(),
          flyPaper = new joint.dia.Paper({
            el: $("#flyPaper"),
            width: 100,
            height: 100,
            model: flyGraph,
            interactive: false,
          }),
          flyShape = cellView.model.clone(),
          pos = cellView.model.position(),
          offset = {
            x: x - pos.x,
            y: y - pos.y,
          };

        flyShape.position(0, 0);
        flyGraph.addCell(flyShape);
        $("#flyPaper").offset({
          left: e.pageX - offset.x,
          top: e.pageY - offset.y,
        });
        $("body").on("mousemove.fly", (e) => {
          $("#flyPaper").offset({
            left: e.pageX - offset.x,
            top: e.pageY - offset.y,
          });
        });
        $("body").on("mouseup.fly", (e) => {
          var x = e.pageX,
            y = e.pageY,
            target = paper.$el.offset();

          // Dropped over paper ?
          if (
            x > target.left &&
            x < target.left + paper.$el.width() &&
            y > target.top &&
            y < target.top + paper.$el.height()
          ) {
            var s = flyShape.clone();
            s.position(x - target.left - offset.x, y - target.top - offset.y);
            // s.size(10,10)
            // console.log(this.graph)
            this.graph.addCell(s);
          }
          $("body").off("mousemove.fly").off("mouseup.fly");
          flyShape.remove();
          $("#flyPaper").remove();
        });
      });

      paper.on("cell:pointerdblclick", (cellView, e, x, y) => {
        console.log(cellView.model.attributes.attrs.label.text - 1);
        cellView.model.remove();
        // console.log(this);
        for (var i = 0; i < this.tData.length; i++) {
          if (this.tData[i].id == cellView.model.attributes.attrs.label.text) {
            this.tData.splice(i, 1);
            this.tDataNormal.splice(i, 1);
            this.coordinates.splice(i, 1);
          }
        }
        // console.log(this.tData);
      })

      // Draw on paper!!!
      let [_x, _y] = [0, 0];
      paper.on("blank:pointerdown", (e, x, y) => {
        [_x, _y] = [e.offsetX, e.offsetY];
        // console.log(this);
        this.num = this.num + 1;
        this.coordinates = this.coordinates.concat([[_x, _y]]);
        this.tData.push({ id: this.num, x_coordinate: _x, y_coordinate: _y });
        this.tDataNormal.push({
          id: this.num, 
          x_coordinate: (_x-300)*0.5,
          y_coordinate: (_y-300)*0.5
        })
        console.log(this.coordinates);
        console.log("getCloneOnPaper");
        $("body").append(
          '<div id="flyPaper" style="position:fixed;z-index:100;opacity:.7;pointer-event:none;"></div>'
        );
        var flyGraph = new joint.dia.Graph(),
          flyPaper = new joint.dia.Paper({
            el: $("#flyPaper"),
            width: 100,
            height: 100,
            model: flyGraph,
            interactive: false,
          }),
          flyShape = new joint.shapes.standard.Circle({
            position: { x: _x, y: _y },
            size: { width: 10, height: 10 },
            attrs: {
              body: { stroke: "black", fill: "#f1f0f1" },
              label: { text: "      " + this.num, fontSize: 16 },
            },
          }),
          offset = {
            x: x - _x,
            y: y - _y,
          };

        flyShape.position(0, 0);
        flyGraph.addCell(flyShape);
        $("#flyPaper").offset({
          left: e.pageX - offset.x,
          top: e.pageY - offset.y,
        });
        $("body").on("mousemove.fly", (e) => {
          $("#flyPaper").offset({
            left: e.pageX - offset.x,
            top: e.pageY - offset.y,
          });
        });
        $("body").on("mouseup.fly", (e) => {
          var x = e.pageX,
            y = e.pageY,
            target = paper.$el.offset();

          // Dropped over paper ?
          if (
            x > target.left &&
            x < target.left + paper.$el.width() &&
            y > target.top &&
            y < target.top + paper.$el.height()
          ) {
            var s = flyShape.clone();
            s.position(x - target.left - offset.x, y - target.top - offset.y);
            // s.size(10,10)
            // console.log(this.graph)
            this.graph.addCell(s);
          }
          $("body").off("mousemove.fly").off("mouseup.fly");
          flyShape.remove();
          // $("#flyPaper").remove();
        });
      });

      // console.log(paper)
      paper.on("cell:pointerdown", (cellView, evt, x, y) => {
        // alert(JSON.stringify(window.this.shapAttrs))
        var cell = cellView.model;
        if (cell.get("type").startsWith("standard")) {
          // cell.size(60,10)
          this.shapAttrs = {
            cell: cellView.model,
            height: cell.get("size").height,
            width: cell.get("size").width,
            color: cell.get("attrs")["body"].fill,
            text: {
              content: cell.get("attrs")["label"].text,
              color: cell.get("attrs")["label"].fill,
              fontSize: cell.get("attrs")["label"].fontSize,
            },
          };
        }
        console.log("AddShapAttrs");
        if (cell.get("type") == "link") {
        }
      });
      // First, unembed the cell that has just been grabbed by the user.
      paper.on("cell:pointerdown", (cellView, evt, x, y) => {
        var cell = cellView.model;

        if (!cell.get("embeds") || cell.get("embeds").length === 0) {
          // Show the dragged element above all the other cells (except when the
          // element is a parent).
          cell.toFront();
        }

        if (cell.get("parent")) {
          this.graph.getCell(cell.get("parent")).unembed(cell);
        }
        console.log("111");
      });

      paper.on("cell:pointerdown", (cellView, evt, x, y) => {
        var cell = cellView.model;
        // alert(JSON.stringify(cell.get("position").x))
        if (cell.get("type").startsWith("standard")) {
          var disH = cell.get("size").height;
          var disW = cell.get("size").width;

          var centerbefore = cell.getBBox().center();
          var labelbefore = centerbefore.toString();
          var xybefore = labelbefore.split("@");

          // cell.get("position").x+width;
          console.log(
            "postion x==" +
            cell.get("position").x +
            "==vue x" +
            this.x +
            "=====" +
            disW +
            "==" +
            (cell.get("position").x + disW * 0.8)
          );
          console.log(
            "postion y==" +
            cell.get("position").y +
            "==vue y" +
            this.y +
            "=====" +
            disH +
            "==" +
            (cell.get("position").y + disH * 0.8)
          );
          if (
            this.x >= cell.get("position").x + disW * 0.6 &&
            this.y >= cell.get("position").y + disH * 0.6
          ) {
            document.onmousemove = (ev) => {
              var centerafter = cell.getBBox().center();
              var labelafter = centerafter.toString();
              var xyafter = labelafter.split("@");
              var ev = ev || window.event;
              //拖拽时为了对宽和高 限制一下范围，定义两个变量
              var W = xyafter[0] - xybefore[0] + disW;
              var H = xyafter[1] - xybefore[1] + disH;

              cell.size(W, H);
            };
            document.onmouseup = function () {
              document.onmousemove = null;
              document.onmouseup = null;
            };
          }
        }
      });

      let [new_x, new_y] = [0, 0];
      paper.on("cell:pointerup", (cellView, evt, x, y) => {
        [new_x, new_y] = [evt.offsetX, evt.offsetY];
        for (var num = 0; num < this.tData.length; num++) {
          if (this.tData[num].id == cellView.model.attributes.attrs.label.text) {
            this.tData[num].x_coordinate = new_x;
            this.tData[num].y_coordinate = new_y;
            this.tDataNormal[num].x_coordinate = (new_x-300)*0.5;
            this.tDataNormal[num].y_coordinate = (new_y-300)*0.5;
            this.coordinates[num] = [new_x, new_y];
          }
        }
        var cell = cellView.model;
        var cellViewsBelow = paper.findViewsFromPoint(cell.getBBox().center());

        if (cellViewsBelow.length) {
          // Note that the findViewsFromPoint() returns the view for the `cell` itself.
          var cellViewBelow = _.find(cellViewsBelow, function (c) {
            return c.model.id !== cell.id;
          });

          // Prevent recursive embedding.
          if (cellViewBelow && cellViewBelow.model.get("parent") !== cell.id) {
            cellViewBelow.model.embed(cell);
          }
        }
      });

      //放大缩小
      var zoomLevel = 1;
      $("#zoom-in").on("click", function () {
        zoomLevel = Math.min(3, zoomLevel + 0.2);
        var size = paper.getComputedSize();
        paper.translate(0, 0);
        paper.scale(zoomLevel, zoomLevel, size.width / 2, size.height / 2);
      });

      $("#zoom-out").on("click", function () {
        zoomLevel = Math.max(0.2, zoomLevel - 0.2);
        var size = paper.getComputedSize();
        paper.translate(0, 0);
        paper.scale(zoomLevel, zoomLevel, size.width / 2, size.height / 2);
      });
      // 清空确认
      $("#clearGraph").on("click", () => {
        this.graph.clear(), 
        (this.coordinates = []), 
        (this.tData = []), 
        (this.tDataNormal = []),
        (this.num = 0);
      });
      $("#makeSure").on("click", () => {
        (this.coordinates = this.coordinates.map((item) => ({
          x: item[0],
          y: item[1],
        }))),
          console.log(this.coordinates);
      });

      var $ox = $("#ox");
      var $oy = $("#oy");
      $ox.on("input change", function () {
        paper.setOrigin(parseInt(this.value, 10), parseInt($oy.val(), 10));
      });
      $oy.on("input change", function () {
        paper.setOrigin(parseInt($ox.val(), 10), parseInt(this.value, 10));
      });
    },
  },
};
</script>

<style  scoped>
.app-header {
  position: relative;
  width: 100%;
}
#paper {
  background: #f1f0f1;
}

/*#stencil {
        background: #146DFF;
    }*/
html,
body,
.joint-app {
  position: relative;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
.app-title {
  display: inline-block;
  width: 0px;
  height: 30px;
  padding: 0;
  background: #e3f730;
}
.toolbar-container {
  display: inline-block;
  position: absolute;
  height: 100%;
  left: 0px;
  right: 0;
  box-sizing: border-box;
  /*background: red;*/
}
.app-body {
  position: relative;
  height: -moz-calc(100% - 60px);
  height: -webkit-calc(100% - 60px);
  height: calc(100% - 60px);
}
.stencil-container {
  /*position: absolute;*/
  left: 0;
  top: 0;
  /*border-right-color:red;*/
  border-style: solid;
  border-width: 0px;
  width: 0px;
  height: 100%;
  background-color: #4ff890;
}
.paper-container {
  position: absolute;
  top: 0;
  height: 100%;
  overflow: hidden;
  box-sizing: border-box;
  left: 0px;
  right: 240px;
}
.inspector-container {
  position: absolute;
  top: 0;
  left: 610px;
  bottom: 520px;
  width: 240px;
  box-sizing: border-box;
  height: 600px;
}

.joint-inspector {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  overflow: auto;
}
.joint-inspector.joint-theme-modern {
  color: #c6c7e2;
  background: #383b61;
}
.joint-inspector .group {
  overflow: hidden;
  padding: 0;
  padding-bottom: 10px;
}
.joint-inspector .group > .group-label {
  position: relative;
  padding: 5px 4px;
  margin-top: 0;
  margin-bottom: 0;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

</style>
