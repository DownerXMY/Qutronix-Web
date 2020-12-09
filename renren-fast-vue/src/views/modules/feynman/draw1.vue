<template xmlns="http://www.w3.org/1999/html">
  <el-dialog
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="80%"
  >
    <div
      id="app"
      class="joint-app joint-theme-modern"
    >
      <div class="app-header">
        <div class="app-title">
          <h1 align="center"></h1>
        </div>
        <div class="toolbar-container ">
          <button
            id="zoom-in"
            style="font-size: 15px;margin-left: 10px"
          >放大</button>
          <button
            id="zoom-out"
            style="font-size: 15px;margin-left: 10px"
          >缩小</button>
          <label
            for="ox"
            title="Position of zero x-coordinate of the paper in pixels"
            style="font-size: 15px;margin-left: 10px"
          >整体左右移动</label>
          <input
            id="ox"
            name="ox"
            type="range"
            value="0"
            min="-200"
            max="200"
            autocomplete="off"
          />
          <label
            for="oy"
            title="Position of zero y-coordinate of the paper in pixels"
            style="font-size: 15px;margin-left: 10px"
          >整体上下移动</label>
          <input
            id="oy"
            type="range"
            value="0"
            min="-200"
            max="200"
            autocomplete="off"
          />
          <button
            id="clearGraph"
            @click="changeMode()"
            style="font-size: 15px;margin-left: 10px"
          >ClearAll</button>
        </div>
      </div>
      <div class="app-body">
        <div
          class="stencil-container"
          id="stencil"
        ></div>
        <div
          class="paper-container"
          id="paper"
          @mousemove='updateXY'
        ></div>
        <div class="inspector-container">
          <div
            class="form-horizontal"
            style="padding: 10px"
          >
            <h1>编辑图形</h1>
            <h2>Text</h2>

            <div class="input-group mb-3 input-group-lg ">
              <div style="font-size: 15px;margin-top: 10px;margin-right: 10px">Alpha</div>
              <input
                type="text"
                class="form-control"
                placeholder=""
                v-model="alphaUpdate1"
              >
            </div>
            <div class="input-group mb-3 input-group-lg ">
              <div style="font-size: 15px;margin-top: 10px;margin-right: 10px">Varphi</div>
              <input
                type="text"
                class="form-control"
                placeholder=""
                v-model="varphiUpdate1"
              >
            </div>
            <div class="input-group mb-3 input-group-lg ">
              <div style="font-size: 15px;margin-top: 10px;margin-right: 10px">Pointwise Setting</div>
              <button
                id="makeSure"
                style="font-size: 20px; margin-left: 10px">
                Confirm
              </button>
            </div>
            <div class="input-group mb-3 input-group-lg ">
              <div style="font-size: 15px;margin-top: 10px;margin-right: 10px">Are you sure?</div>
              <button
                @click="backToBS()"
                style="font-size: 20px; margin-left: 10px">
                Submit
              </button>
            </div>
          </div>
        </div>
        </div>
      </div>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      num: 0,
      nodeNumber: 12,
      tData: [],
      tabledata: "",
      coordinates: [],
      alpha1: "",
      varphi1: "",
      alphaUpdate1: "",
      varphiUpdate1: "",
      chosenList1: [],
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
          fontSize: "10"
        }
      },
      linkAttrs: {
        cell: null,
        text: "",
        color: ""
      }

    };
  },
  mounted: function () {
    this.$nextTick(() => {
      // 在此处执行你要执行的函数
      this.inti();
    });
  },
  computed: {},
  created() {
    this.inti()
  },
  methods: {
    changeMode() {
        this.varphi1 = "";
        this.alpha1 = "";
        this.varphiUpdate1 = "";
        this.alphaUpdate1 = "";
        for (var index=0;index<this.chosenList1.length;index++) {
            this.chosenList1[index].attr('body/fill','#af9bff');
            this.chosenList1[index].attr('label/text','');
        };
        this.chosenList1 = [];
        console.log(this.alphaUpdate1);
        console.log(this.varphiUpdate1);
        console.log(this.alpha1);
        console.log(this.varphi1);
    },
    backToBS() {
        this.visible = false;
        this.$emit("refreshDrawData1");
    },
    updateXY: function (event) {
      this.x = event.offsetX;
      this.y = event.offsetY
    },
    inti(nodes) {
      this.visible = true;
      this.nodeNumber = parseInt(nodes);
      this.graph = new joint.dia.Graph
      var paper = new joint.dia.Paper({
        el: $('#paper'),
        width: 790,
        height: 590,
        gridSize: 10,
        drawGrid: true,
        /*background: {
            color: 'rgba(0, 255, 0, 0.3)'
        },*/
        model: this.graph,
        defaultLink: new joint.dia.Link({
          attrs: {
            '.connection': { stroke: 'blue' },
            '.marker-source': { fill: 'red', d: 'M 10 0 L 0 5 L 10 10 z' },
            '.marker-target': { fill: 'yellow', d: 'M 10 0 L 0 5 L 10 10 z' }
          }
        }),

        elementView: joint.dia.ElementView.extend({
          //双击删除
          pointerdblclick: function (evt, x, y) {
            joint.dia.CellView.prototype.pointerdblclick.apply(this, arguments);
            this.notify('element:pointerdblclick', evt, x, y);
            this.model.remove();
          }
        }),
        linkView: joint.dia.LinkView.extend({
          //双击删除
          pointerdblclick: function (evt, x, y) {
            joint.dia.CellView.prototype.pointerdblclick.apply(this, arguments);
            this.notify('link:pointerdblclick', evt, x, y);
            this.model.remove();
          }
        })
      });

      // Canvas from which you take shapes
      var stencilGraph = new joint.dia.Graph
      var stencilPaper = new joint.dia.Paper({
        el: $('#stencil'),
        width: 790,
        height: 590,
        model: stencilGraph,
        interactive: false
      });

      var r1 = new joint.shapes.fsa.State({
        position: { x: 100, y: 100 },
        size: { width: 10, height: 10 },
        attrs: {
          body: { stroke: "blue", fill: "#af9bff" },
          label: { text: "圆点", fontSize: 10 },
        },
      });

      stencilGraph.addCells([r1]);

      // ----------------------------------------
      // Building construction on thios canvas...
      var constGraph = new joint.dia.Graph
      var constPaper = new joint.dia.Paper({
        el: $('#paper'),
        width: 790,
        height: 590,
        model: constGraph,
        interactive: false
      });

      const per = 2 * (350- 350 % (2*this.nodeNumber-1)) / (2*this.nodeNumber-1);
      for (var number = 1; number < this.nodeNumber; number++) {
        let Nlink1 = new joint.shapes.standard.Link({
          source: { x: 360 - (2 * number - 1) * per / 2, y: per * number },
          target: { x: 360 + per * (this.nodeNumber - 1.5) - 2 * per * (number - 1), y: per * this.nodeNumber }
        });
        let Nlink2 = new joint.shapes.standard.Link({
          source: { x: 360 + per * (this.nodeNumber - 1.5) - per * (2 * number - 1), y: per * this.nodeNumber },
          target: { x: 360 + per * (this.nodeNumber - 1.5) - per * (number - 1), y: per * this.nodeNumber - per * number }
        });
        let Nlink3 = new joint.shapes.standard.Link({
          source: { x: 20, y: per * number },
          target: { x: 360 - (2 * number - 1) * per / 2, y: per * number }
        });
        let Nlink4 = new joint.shapes.standard.Link({
          source: { x: 360 + per * (this.nodeNumber - 1.5) - per * (number - 1), y: per * this.nodeNumber - per * number },
          target: { x: 750, y: per * this.nodeNumber - per * number }
        });

        Nlink1.attr('line/stroke', '#e66f2b');
        Nlink2.attr('line/stroke', '#e66f2b');
        Nlink3.attr('line/stroke', '#e66f2b');
        Nlink3.labels([{
            attrs: {
                text: {
                    text: 'No.'+number,
                    fill: '#41584B',
                    'font-size': 14
                }
            }
        }]);
        Nlink4.attr('line/stroke', '#e66f2b');

        constGraph.addCells([Nlink1, Nlink2, Nlink3, Nlink4]);
      }

      for (var number2 = 1; number2 < this.nodeNumber-1; number2++) {
        let Nlink5 = new joint.shapes.standard.Link({
          source: { x: 360 + per * (this.nodeNumber - 1.5) - 2 * per * number2, y: per * this.nodeNumber },
          target: { x: 360 + per * (this.nodeNumber - 1.5) - 2 * per * number2 + per, y: per * this.nodeNumber }
        })
        Nlink5.attr('line/stroke', '#e66f2b');
        constGraph.addCells([Nlink5]);
      }

      let Nlink6 = new joint.shapes.standard.Link({
        source: { x: 20, y: per * this.nodeNumber },
        target: { x: 360 - per * (this.nodeNumber - 1.5), y: per * this.nodeNumber }
      });
      let Nlink7 = new joint.shapes.standard.Link({
        source: { x: 360 + per * (this.nodeNumber - 1.5), y: per * this.nodeNumber },
        target: { x: 750, y: per * this.nodeNumber }
      });
      Nlink6.attr('line/stroke', '#e66f2b');
      Nlink6.labels([{
          attrs: {
              text: {
                  text: 'No.'+this.nodeNumber,
                  fill: '#41584B',
                  'font-size': 14
              }
          }
      }]);
      Nlink7.attr('line/stroke', '#e66f2b');

      constGraph.addCells([Nlink6, Nlink7]);

      // Add choosing points
      for (var i1 = 1; i1 < this.nodeNumber; i1++) {
        for (var i2 = 1; i2 <= i1; i2++) {
          var NButton = new joint.shapes.standard.Circle({
            position: { x: 360 - (i1 - 1) * per + 2 * per * (i2 - 1), y: per + per * (2 * i1 - 1) / 2 },
            size: { width: 10, height: 10 },
            attrs: {
              body: { stroke: "black", fill: "#af9bff" },
              label: { text: "", fontSize: 16 },
            },
          });
          constGraph.addCells([NButton]);
        }
      }
      // -----------------------------------------
      // -----------------------------------------

      var chosenPoint1 = Object();
      constPaper.on('cell:pointerdown', (cellView, e, x, y) => {
        this.chosenPoint1 = cellView.model;
        this.chosenList1 = this.chosenList1.concat([this.chosenPoint1]);
        console.log(this.chosenPoint1);
        // console.log(this.chosenList1);
      });

      // ---------------------------------------

      stencilPaper.on('cell:pointerdown', (cellView, e, x, y) => {
        $('body').append('<div id="flyPaper" style="position:fixed;z-index:100;opacity:.7;pointer-event:none;"></div>');
        var flyGraph = new joint.dia.Graph,
          flyPaper = new joint.dia.Paper({
            el: $('#flyPaper'),
            width: 100,
            height: 100,
            model: flyGraph,
            interactive: false
          }),
          flyShape = cellView.model.clone(),
          pos = cellView.model.position(),
          offset = {
            x: x - pos.x,
            y: y - pos.y
          };

        flyShape.position(0, 0);
        flyGraph.addCell(flyShape);
        $("#flyPaper").offset({
          left: e.pageX - offset.x,
          top: e.pageY - offset.y
        });
        $('body').on('mousemove.fly', (e) => {
          $("#flyPaper").offset({
            left: e.pageX - offset.x,
            top: e.pageY - offset.y
          });
        });
        $('body').on('mouseup.fly', (e) => {
          var x = e.pageX,
            y = e.pageY,
            target = paper.$el.offset();

          // Dropped over paper ?
          if (x > target.left && x < target.left + paper.$el.width() && y > target.top && y < target.top + paper.$el.height()) {
            var s = flyShape.clone();
            s.position(x - target.left - offset.x, y - target.top - offset.y);
            // s.size(10,10)
            // console.log(this.graph)
            this.graph.addCell(s);
          }
          $('body').off('mousemove.fly').off('mouseup.fly');
          flyShape.remove();
          $('#flyPaper').remove();
        });
      });

      // console.log(paper)
      paper.on('cell:pointerdown', (cellView, evt, x, y) => {
        // alert(JSON.stringify(window.this.shapAttrs))
        var cell = cellView.model;
        console.log(cell)

        if (cell.get("type").startsWith("standard")) {
          // cell.size(60,10)
          this.shapAttrs = {
            cell: cellView.model,
            height: cell.get("size").height,
            width: cell.get("size").width,
            color: cell.get("attrs")['body'].fill,
            text: {
              content: cell.get("attrs")['label'].text,
              color: cell.get("attrs")['label'].fill,
              fontSize: cell.get("attrs")['label'].fontSize
            }
          }
        }
        console.log(cell)
        if (cell.get("type") == "link") { }
      });
      // First, unembed the cell that has just been grabbed by the user.
      paper.on('cell:pointerdown', (cellView, evt, x, y) => {

        var cell = cellView.model;

        if (!cell.get('embeds') || cell.get('embeds').length === 0) {
          // Show the dragged element above all the other cells (except when the
          // element is a parent).
          cell.toFront();
        }

        if (cell.get('parent')) {
          this.graph.getCell(cell.get('parent')).unembed(cell);
        }
      });


      paper.on('cell:pointerdown', (cellView, evt, x, y) => {
        var cell = cellView.model;
        // alert(JSON.stringify(cell.get("position").x))
        if (cell.get("type").startsWith("standard")) {
          var disH = cell.get("size").height
          var disW = cell.get("size").width

          var centerbefore = cell.getBBox().center();
          var labelbefore = centerbefore.toString();
          var xybefore = labelbefore.split("@")

          // cell.get("position").x+width
          console.log("postion x==" + cell.get("position").x + "==vue x" + this.x + "=====" + disW + "==" + (cell.get("position").x + (disW * 0.8)))
          console.log("postion y==" + cell.get("position").y + "==vue y" + this.y + "=====" + disH + "==" + (cell.get("position").y + (disH * 0.8)))
          if ((this.x >= (cell.get("position").x + (disW * 0.6))) && (this.y >= (cell.get("position").y + (disH * 0.6)))) {
            document.onmousemove = (ev) => {
              var centerafter = cell.getBBox().center();
              var labelafter = centerafter.toString();
              var xyafter = labelafter.split("@")
              var ev = ev || window.event;
              //拖拽时为了对宽和高 限制一下范围，定义两个变量
              var W = xyafter[0] - xybefore[0] + disW;
              var H = xyafter[1] - xybefore[1] + disH;

              cell.size(W, H)
            }
            document.onmouseup = function () {
              document.onmousemove = null;
              document.onmouseup = null;
            }
          }
        }

      })

      paper.on('cell:pointerup', (cellView, evt, x, y) => {

        var cell = cellView.model;
        var cellViewsBelow = paper.findViewsFromPoint(cell.getBBox().center());

        if (cellViewsBelow.length) {
          // Note that the findViewsFromPoint() returns the view for the `cell` itself.
          var cellViewBelow = _.find(cellViewsBelow, function (c) { return c.model.id !== cell.id });

          // Prevent recursive embedding.
          if (cellViewBelow && cellViewBelow.model.get('parent') !== cell.id) {
            cellViewBelow.model.embed(cell);
          }
        }
      });

      //放大缩小
      var zoomLevel = 1;
      $('#zoom-in').on('click', function () {
        zoomLevel = Math.min(3, zoomLevel + 0.2);
        var size = paper.getComputedSize();
        paper.translate(0, 0);
        paper.scale(zoomLevel, zoomLevel, size.width / 2, size.height / 2);
      });

      $('#zoom-out').on('click', function () {
        zoomLevel = Math.max(0.2, zoomLevel - 0.2);
        var size = paper.getComputedSize();
        paper.translate(0, 0);
        paper.scale(zoomLevel, zoomLevel, size.width / 2, size.height / 2);
      });
      $('#clearGraph').on('click', () => {
        this.graph.clear()
      });
      $("#makeSure").on("click", () => {
        (this.alpha1 = this.alphaUpdate1),
        (this.varphi1 = this.varphiUpdate1),
        (this.chosenPoint1.attributes.attrs.label.text = "        "+this.alpha1+","+this.varphi1),
        (this.chosenPoint1.attr('body/fill','#df4617'))
      });

      var $ox = $('#ox');
      var $oy = $('#oy');
      $ox.on('input change', function () {
        paper.setOrigin(parseInt(this.value, 10), parseInt($oy.val(), 10));
      });
      $oy.on('input change', function () {
        paper.setOrigin(parseInt($ox.val(), 10), parseInt(this.value, 10));
      });
    }
  }
};
</script>
<style  scoped>
.app-header {
  position: relative;
  width: 100%;
}
#paper {
  background: #30d0c6;
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
  width: 240px;
  height: 100%;
  padding: 0;
  background: #64e4e4;
}
.toolbar-container {
  display: inline-block;
  position: absolute;
  height: 100%;
  left: 240px;
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
  border-width: 1px;
  width: 50px;
  height: 100%;
  background-color: #9093b1;
}
.paper-container {
  position: absolute;
  top: 0;
  height: 100%;
  overflow: hidden;
  box-sizing: border-box;
  left: 240px;
  right: 240px;
}
.inspector-container {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 120px;
  width: 240px;
  box-sizing: border-box;
  height: 590px;
  background-color: #9093b1;
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
