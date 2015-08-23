package zuilib.components;

import processing.core.PConstants;
import zuilib.properties.RectDimension;


public class CanvasButton extends button {
  
  public RectDimension dimension;
 
  public CanvasButton(String sname, float fx, float fy, float fwidth, float fheight) {
    super(sname,fx,fy);
    dimension = new RectDimension(fwidth,fheight);
    mode = PConstants.CORNER;
 } 
  
  public void setup() {
    super.setup();
    dimension.setup(this);
    addFunction("onDraw");
    addFunction("onUpdate");
  }
 
 public boolean over() {
    dimension.over = overRect(dimension.width, dimension.height, mode);
    return dimension.over;
  }
  
  public void update() {
    super.update();
    useFunction("onUpdate");
  }
  
  public void draw() {
    super.draw();
    useFunction("onDraw");
  }
  
}
