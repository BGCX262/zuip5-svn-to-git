package zuilib.components;

import processing.core.PConstants;



public class CircleCheckbox extends checkbox {
  
  public CircleCheckbox(String sname, float fx, float fy, String stext, float fsize) {
    super(sname,fx,fy,stext,fsize);
  }
  
  public void draw() {
    Color.doBackground();
    graphic.ellipseMode(PConstants.CENTER);
    graphic.ellipse(dimension.height/2,dimension.height/2+3,dimension.height,dimension.height);
    Color.doForeground();
    if(checked) {
      graphic.ellipse(dimension.height/2,dimension.height/2+3,dimension.height-4,dimension.height-4);
    }
    text.draw();
  }
  
}
