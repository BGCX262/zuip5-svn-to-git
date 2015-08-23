package zuilib.zuiEditor;

import processing.core.PApplet;
import zuilib.components.CanvasButton;
import zuilib.components.ScrollPanel;
import zuilib.components.button;
import zuilib.extra.zuiLooseBehavior;
import zuilib.utils.ColorBundle;
import zuilib.utils.vector;
import zuilib.windows.BackgroundWindow;

public class appClipboard extends appComponent {
  
  public boolean hidden;
  
  public BackgroundWindow background;
  public CanvasButton hidebutton;
  public ScrollPanel list;
  


  public appClipboard(MainApp curParent, float fx, float fy, float fwidth, float fheight) {
    super(curParent,fx,fy,fwidth,fheight);
    hidden = true;
  }
  
  public void setup() {
    super.setup();
    if(ui.DEBUG >= 1) PApplet.println("] Setup Clipboard:");
    //set and add Color
    color = new ColorBundle("Clipboard",color(234,234,234,234),color(10,10,10,100),color(34,34,34,234),
        color(255,255,255,234),color(10,10,10,100),color(0,0,0,234));
    parent.ColorManager.addColor(color);
    //add backgroundwindow
    background = new BackgroundWindow("ClipboardBackground",position.x,position.y);
    background.addTo(parent.WindowManager);
    parent.WindowManager.setZIndex(background.id(), ui.MAX_Z_INDEX-2);
    background.dimension.set(dimension.x,dimension.y);
    background.Color.set("Clipboard");
    background.behavior.addBehavior( new WindowBehavior("Hide",6,position.y,position.x+dimension.x-16,16-dimension.x) );
    //add hide button
    hidebutton = new CanvasButton("Hide",2,2,12,12);
    hidebutton.addTo(background);
    hidebutton.Color.set("Clipboard");
    hidebutton.setFunctionOwner(this);
    hidebutton.setFunction("onDraw", "hidebutton_draw");
    hidebutton.setFunction("mouseClicked", "hidebutton_click");
    //add scroll list
    list = new ScrollPanel("List",2,14,dimension.x-9,dimension.y-16,5);
    list.addTo(background);
    list.panelGraphic.smooth = ui.getPApplet().g.smooth;
    list.components.Color.set("Clipboard");
    list.vscrollbar.Color.set("Clipboard");
    list.hscrollbar.setVisibility(false);
    
  }
  
  public void hidebutton_draw(CanvasButton self) {
    if(hidden) {
      self.graphic.line(self.dimension.width-2,2,2,self.dimension.height/2);
      self.graphic.line(2,self.dimension.height/2,self.dimension.width-2,self.dimension.height-2);
    } else {
      self.graphic.line(2,2,self.dimension.width-2,self.dimension.height/2);
      self.graphic.line(self.dimension.width-2,self.dimension.height/2,2,self.dimension.height-2);
    }
  }
  
  public void hidebutton_click(button self) {
    hidden = !hidden;
    if(hidden) {
       self.position.setX(2);
    } else {
      self.position.setX(dimension.x-14);
    }
  }
  
  class WindowBehavior extends zuiLooseBehavior {
    
    public vector showenpos;
    public vector hiddenpos;
    
    public WindowBehavior(String sname, float floose, float ypos, float hidden, float showen) {
      super(sname,floose);
      hiddenpos = new vector(hidden,ypos);
      showenpos = new vector(hidden+showen,ypos);
    }
    
    public void update() {
      super.update();
      if(hidden) {
        setPosition(hiddenpos);
      } else {
        setPosition(showenpos);
      }
    }
  }

}