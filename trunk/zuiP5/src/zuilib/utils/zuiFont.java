package zuilib.utils;

import processing.core.PGraphics;
import zuilib.core.zuiAtomObject;
import zuilib.manager.fontmanager;

public class zuiFont extends zuiAtomObject {

  public String Name;
  
  public zuiFont(fontmanager curParent, String sname) {
    setParentObject(curParent);
    Name = sname;
  }
  
  public float textWidth(String text, float size) {
    return 0f;
  }
  
  public void display(PGraphics graphic, float x, float y, String text, float size) {
    return;
  }

}
