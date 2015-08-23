package zuilib.components;

import processing.core.PApplet;

public class RectHandle_Circle extends recthandle {
  
  public RectHandle_Circle(String sname, float fx, float fy, float fwidth, float fheight, float flength) {
    super(sname,fx,fy,fwidth,fheight,flength);
    box = new CircleButton(Name.get()+"_Circle",0,0);
  }
  
  public void setup() {
    super.setup();
    ((CircleButton) box).dimension.set(PApplet.min(dimension.width,dimension.height));
  }
  
}
