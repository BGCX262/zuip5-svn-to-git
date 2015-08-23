package zuilib.windows;

import processing.core.PConstants;
import zuilib.properties.CircleAreaDimension;
import zuilib.properties.CircleDimension;


public class CircleWindow extends dragdrop_window {
  
  public CircleDimension dimension;
  public CircleAreaDimension area;
  
  public CircleWindow(String sname, float fx, float fy) {
    super(sname,fx,fy);
    mode = PConstants.CENTER;
    dimension = new CircleDimension();
    area = new CircleAreaDimension(position.get(),dimension.size);
  }
  
  public void setup() {
    super.setup();
    dimension.setup(this);
    area.setup(this);
  }
  
  public void draw() {
    super.draw();
    Color.doBackground();
    graphic.ellipseMode(mode);
    graphic.ellipse(0,0,dimension.size, dimension.size);
    
    Color.doForeground();
    graphic.ellipse(area.position.get().x,area.position.get().y,area.size,area.size);
  }
 
  public boolean over() {
    dimension.over = overCircle(dimension.size,mode);
    return dimension.over;
  } 
  
  public boolean over_area() {
    area.over = overCircle(area.position,area.size,mode);
    return area.over;
  }
  
}
