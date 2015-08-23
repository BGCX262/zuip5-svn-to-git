package zuilib.components;

import processing.core.PApplet;

public class RectScrollbar extends scrollbar {

  public RectScrollbar(String sname, float fx, float fy, float fwidth, float fheight, float flength) {
    super(sname, fx, fy, fwidth, fheight, flength);
    RectScrollbar_init();
  }
  
  public RectScrollbar(String sname) {
    super(sname);
    RectScrollbar_init();
  }
  
  private void RectScrollbar_init() {
    box = new RectButton(Name.get()+"_Rect",0,0);
  }
  
  public void setup() {
    super.setup();
    float size = PApplet.min(dimension.width,dimension.height);
    ((RectButton) box).dimension.set(size, size);
  }

}
