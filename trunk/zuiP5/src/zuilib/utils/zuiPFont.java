package zuilib.utils;

import processing.core.PFont;
import processing.core.PGraphics;
import zuilib.manager.simplefontmanager;

public class zuiPFont extends zuiFont {
  
  public PFont font;

  public zuiPFont(simplefontmanager curParent, String newf) {
    super(curParent,newf);
    font = getPApplet().loadFont(Name);
  }
  
  public float textWidth(String text, float size) {
    getPApplet().textFont(font,size);
    return getPApplet().textWidth(text);
  }
  
  public void display(PGraphics graphic, float x, float y, String text, float size) {
    graphic.textFont(font, size);
    graphic.text(text, x, y);
  }

}
