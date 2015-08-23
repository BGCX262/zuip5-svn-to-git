package zuilib.components;

import processing.core.PApplet;

public class RectHandle_Rect extends recthandle {
  
  public RectHandle_Rect(String sname, float fx, float fy, float fwidth, float fheight, float flength) {
    super(sname,fx,fy,fwidth,fheight,flength);
    box = new RectButton(Name.get()+"_Rect",0,0);
  }
  
  public void setup() {
    super.setup();
    float size = PApplet.min(dimension.width,dimension.height);
    ((RectButton) box).dimension.set(size, size);
  }
  
}
