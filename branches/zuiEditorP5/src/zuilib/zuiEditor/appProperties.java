package zuilib.zuiEditor;

import processing.core.PApplet;
import zuilib.components.ScrollPanel;
import zuilib.core.zuiObject;
import zuilib.utils.ColorBundle;
import zuilib.windows.BackgroundWindow;

public class appProperties extends appComponent {
  
  public BackgroundWindow background;
  public ScrollPanel list;
  public zuiObject object;

  public appProperties(MainApp curParent, float fx, float fy, float fwidth, float fheight) {
    super(curParent, fx, fy, fwidth, fheight);
    object = null;
  }
  
  public void setup() {
    super.setup();
    if(ui.DEBUG >= 1) PApplet.println("] Setup Property Window:");
    //set and add Color
    color = new ColorBundle("Properties",color(234,234,234,234),color(10,10,10,100),color(34,34,34,234),
        color(255,255,255,234),color(10,10,10,100),color(0,0,0,234));
    parent.ColorManager.addColor(color);
    parent.ColorManager.addColor( new ColorBundle("Property List",
        color(10,10,10,100),color(234,234,234,234),color(34,34,34,234),
        color(10,10,10,100),color(255,255,255,234),color(0,0,0,234)) );
    //add backgroundwindow
    background = new BackgroundWindow("Property List",position.x,position.y);
    background.addTo(parent.WindowManager);
    parent.WindowManager.setZIndex(background.id(), ui.MAX_Z_INDEX-2);
    background.dimension.set(dimension.x,dimension.y);
    background.Color.set("Properties");
    //add scroll list
    list = new ScrollPanel("List",7,14,dimension.x-9,dimension.y-16,5);
    list.addTo(background);
    list.panelGraphic.smooth = ui.getPApplet().g.smooth;
    list.components.Color.set("Property List");
    list.vscrollbar.Color.set("Property List");
    list.hscrollbar.setVisibility(false);
    list.vscrollbar.position.setRel(-list.dimension.width-list.vscrollbar.dimension.width-1,0);
    
    
  }
  
  public void setObject(zuiObject curObject) {
    object = curObject;
    if(object == null) {
      
    }
  }

}
