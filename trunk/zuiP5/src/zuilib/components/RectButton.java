package zuilib.components;

import processing.core.PConstants;
import zuilib.properties.RectDimension;


public class RectButton extends button {
  
  public RectDimension dimension;
  
  public RectButton(String sname, float fx, float fy) {
    super(sname,fx,fy);
    mode = PConstants.CORNER;
    dimension = new RectDimension();
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
