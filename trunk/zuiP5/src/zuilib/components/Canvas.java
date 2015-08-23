package zuilib.components;

import processing.core.PConstants;
import zuilib.properties.RectDimension;


public class Canvas extends interactivecomponent {
  
  public RectDimension dimension;
 
  public Canvas(String sname, float fx, float fy, float fwidth, float fheight) {
    super(sname,fx,fy);
    mode = PConstants.CORNER;
    dimension = new RectDimension(fwidth,fheight);
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
