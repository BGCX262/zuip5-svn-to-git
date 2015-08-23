package zuilib.components;

import zuilib.properties.HoverController;
import zuilib.properties.HoverPosition;
import zuilib.properties.RectDimension;
import zuilib.properties.zuiObjectController;
import zuilib.utils.vector;

public class NumberEdit extends interactivecomponent {
  
  public RectDimension dimension;
  public zuiObjectController numberControl;
  public HoverController hover;
  public RectHandle_Rect gauge;
  public Label number;
  public float value;
  

  public NumberEdit(String sname, vector start_pos) {
    super(sname, start_pos);
    numberedit_init();
  }

  public NumberEdit(String sname, float fx, float fy) {
    super(sname, fx, fy);
    numberedit_init();
  }
  
  private void numberedit_init() {
    value = 0f;
    number = new Label("NumberText",0,0,"0");
    dimension = number.dimension;
    numberControl = new zuiObjectController(number);
    gauge = new RectHandle_Rect("NumberGauge",7,-4,100,7,2);
    hover = new HoverController(gauge);
    gauge.setValue(1);
    hover.addObjectDimensionToOffset = true;
    hover.objectPosition = HoverPosition.BOTTOMLEFT;
    hover.countdown = 12;
  }
  
  public void setup() {
    super.setup();
    hover.setup(this);
    numberControl.setup(this);
  }
  
  public boolean over() {
    number.over();
    return dimension.over;
  }
  
  public void draw() {
    super.draw();
    graphic.rectMode(mode);
    graphic.rect(0,0,dimension.width,dimension.height);
  }

}
