package zuilib.components;

import processing.core.PConstants;
import zuilib.properties.RectDimension;


public class RectShape extends shape {
  
  public RectDimension dimension;
  
  public RectShape(String sname, float fx, float fy, float fwidth, float fheight, int ccolor, int cborder) {
    super(sname,fx,fy,ccolor,cborder);
    RectShape_init(fwidth,fheight);
  }
  
   
  public RectShape(String sname, float fx, float fy, float fwidth, float fheight) {
    super(sname,fx,fy);
    RectShape_init(fwidth,fheight);
  }
  
  private void RectShape_init(float fwidth, float fheight) {
    dimension = new RectDimension(fwidth,fheight);
    mode = PConstants.CORNER;
  }
  
  public boolean over() {
    dimension.over = overRect(dimension.width, dimension.height, mode);
    return dimension.over;
  }
  
  public void draw() {
    super.draw();
    graphic.rectMode(mode);
    graphic.rect(0,0, dimension.width, dimension.height);
  }
  
}
