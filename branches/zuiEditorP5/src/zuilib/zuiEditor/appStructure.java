package zuilib.zuiEditor;

import zuilib.core.ZUI;

public class appStructure implements zuiEditorConstants {
  
  public ZUI ui;
  
  public appStructure(ZUI zui) {
    ui = zui;
  }
  
  public void setup() {return;}
  
  public int color(float r, float g, float b, float a) {
    return ui.getPApplet().color(r,g,b,a);
  }

}
