package zuilib.windows;

import processing.core.PConstants;
import zuilib.properties.RectAreaDimension;
import zuilib.properties.RectDimension;


public class CanvasWindow extends dragdrop_interactivewindow {
  
  public RectDimension dimension;
  public RectAreaDimension area;
  
  public CanvasWindow(String sname, float ix, float iy) {
    super(sname,ix,iy);
    mode = PConstants.CORNER;
    dimension = new RectDimension();
    area = new RectAreaDimension(position.get(),dimension.width,dimension.height);
  } 
  
  public void setup() {
    super.setup();
    dimension.setup(this);
    area.setup(this);
    addFunction("onDraw");
    addFunction("onUpdate");
  }
  
  public void update() {
     super.update();
     useFunction("onUpdate");
   }
  
  public void draw() {
     super.draw();
     useFunction("onDraw");
   } 
   
   public boolean over() {
     dimension.over = overRect(dimension.width,dimension.height,mode);
     return dimension.over;
   }
   
   public boolean over_area() {
     area.over = overRect(area.position,area.width,area.height,mode);
     return area.over;
   }
 }
