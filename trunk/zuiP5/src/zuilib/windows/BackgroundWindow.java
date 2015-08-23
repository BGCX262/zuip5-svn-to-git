package zuilib.windows;

import processing.core.PConstants;
import zuilib.core.window;
import zuilib.properties.ColorController;
import zuilib.properties.RectDimension;


public class BackgroundWindow extends window {
  
  public RectDimension dimension;
  public ColorController Color;

  public BackgroundWindow(String sname, float fx, float fy, float fwidth, float fheight) {
    super(sname,fx-fwidth/2,fy-fheight/2);
    dimension = new RectDimension(fwidth,fheight);
    Color = new ColorController();
  }
  
  public BackgroundWindow(String sname, float fx, float fy) {
    super(sname,fx,fy);
    dimension = new RectDimension();
    Color = new ColorController();
  }
  
  public void setup() {
    super.setup();
    Color.setup(this);
    dimension.setup(this);
  }
  
  public void draw() {
    super.draw();
    Color.doBackground();
    graphic.noStroke();
    graphic.rectMode(PConstants.CORNER);
    graphic.rect(0,0,dimension.width,dimension.height);
  }
  
  public boolean over() {
    dimension.over = overRect(dimension.width,dimension.height,PConstants.CORNER);
    return dimension.over;
  } 

}
