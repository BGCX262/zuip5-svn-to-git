package zuilib.components;

import processing.core.PConstants;
import zuilib.properties.CircleDimension;


public class CircleShape extends shape {
  
  public CircleDimension dimension;
  
  public CircleShape(String sname, float fx, float fy, float fsize, int ccolor, int cborder) {
    super(sname,fx,fy,ccolor,cborder);
    CircleShape_init(fsize);
  }
  
   
  public CircleShape(String sname, float fx, float fy, float fsize) {
    super(sname,fx,fy);
    CircleShape_init(fsize);
  }
  
  private void CircleShape_init(float fsize) {
    dimension = new CircleDimension(fsize);
    mode = PConstants.CENTER;
  }
  
  public boolean over() {
    dimension.over = overCircle(dimension.size, mode);
    return dimension.over;
  }
  
  public void draw() {
    super.draw();
    graphic.ellipseMode(mode);
    graphic.ellipse(0,0, dimension.size, dimension.size);
  }
  
}
