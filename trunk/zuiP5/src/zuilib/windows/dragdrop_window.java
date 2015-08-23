package zuilib.windows;

import zuilib.core.window;
import zuilib.properties.ColorController;
import zuilib.properties.areaDimensionController;
import zuilib.utils.vector;


public class dragdrop_window extends window {
  
  public areaDimensionController area;
  private vector diff;
  public ColorController Color;
  
  public dragdrop_window(String sname, float fx, float fy) {
    super(sname,fx,fy);
    diff = new vector(0,0);
    Color = new ColorController();
  }
  
  public dragdrop_window(String sname, vector start_pos) {
    super(sname,start_pos);
    diff = new vector(0,0);
    Color = new ColorController();
  }
  
  public void setup() {
    super.setup();
    Color.setup(this);
    Color.setForegroundBorder(false);
  }
  
  public void update() {
    super.update();
    Color.setHighlighting(over_area());
  }
  
  public void mouseDragged() {
    if(getLock() && pressed) {
        position.set( vector.VecSub(getParentMouse(),diff) );
      } else {
        super.mouseDragged();
    }
  }
  
  public void mousePressed() {
    super.mousePressed();
    if(!getLock()) {
      setLock(over_area());
      pressed = getLock();
      diff = vector.VecSub(getParentMouse(),position.get());
    }
  }
  
  public void mouseReleased() {
    pressed = false;
    super.mouseReleased();
  }
  
  public boolean over_area() {
    return area.getOver();
  }
}
