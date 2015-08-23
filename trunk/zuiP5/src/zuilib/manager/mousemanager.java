package zuilib.manager;

import zuilib.core.manager;
import zuilib.utils.vector;

public class mousemanager extends manager {

  public mousemanager(String sname, boolean benable) {
    super(sname, benable);
    Type = "mouse";
  }
  
  public vector getMouse() {
    return new vector(0,0);
  }
  
  public vector get() {
    return getMouse();
  }
  
  public void mouseClicked() {return;}
  public void mouseMoved() {return;}
  public void mouseReleased() {return;}
  public void mousePressed() {return;}
  public void mouseDragged() {return;}

}
