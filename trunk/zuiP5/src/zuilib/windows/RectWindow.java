package zuilib.windows;

import processing.core.PConstants;
import zuilib.properties.RectAreaDimension;
import zuilib.properties.RectDimension;


public class RectWindow extends dragdrop_window {
  
  public RectAreaDimension area;
  public RectDimension dimension;
  
  public RectWindow(String sname, float ix, float iy) {
    super(sname,ix,iy);
    mode = PConstants.CORNER;
    dimension = new RectDimension();
    area = new RectAreaDimension(position.get(),dimension.width,dimension.height);
  }
  
  public void setup() {
    super.setup();
    dimension.setup(this);
    area.setup(this);
  }

  public void draw() {
    super.draw();
    Color.doBackground();
    graphic.rectMode(mode);
    graphic.rect(0,0,dimension.width,dimension.height);
    
    
    Color.doForeground();
    graphic.rect(area.position.get().x,area.position.get().y,area.width,area.height);
    
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
