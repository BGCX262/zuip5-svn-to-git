package zuilib.core;

import processing.core.PApplet;
import processing.core.PGraphics;
import zuilib.manager.windowmanager;

public class controlerObject extends propertyObject {
  
  public PGraphics graphic;
  
  public controlerObject(zuiAtomObject curParent) {
    super.setup(curParent);
    graphic = getParentGraphic();
  }
  
  public controlerObject() {
    return;
  }
  
  public void setup(zuiAtomObject curParent) {
    super.setup(curParent);
    graphic = getParentGraphic();
  }
  
  public zuiObject getParent() {
    try {
      return zuiObject.class.cast(getParentObject());
    } catch(Exception e) {}
    if(getZUI().DEBUG > 1) PApplet.println("[WARNING]: getParent is returning null. (Can Mostly be ignored cause its caught.)");
    return null;
  }
  
  public PGraphics getParentGraphic() {
    try {
        PGraphics graphic = getParent().graphic;
        return graphic;
      }catch(Exception e) {
      try {
        PGraphics graphic = ((windowmanager) getParentObject()).graphic;
        return graphic;
      }catch(Exception e2) {}
    }
    PApplet.println("[WARNING]: getGraphic is returning null.");
    return null;
  }

  public void doTask() {} // place holder

}
