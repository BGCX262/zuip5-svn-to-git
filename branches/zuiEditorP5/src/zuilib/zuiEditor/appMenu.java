package zuilib.zuiEditor;

import processing.core.PApplet;
import zuilib.components.TextButton;
import zuilib.extra.zuiLooseBehavior;
import zuilib.utils.ColorBundle;
import zuilib.utils.vector;
import zuilib.windows.BackgroundWindow;

public class appMenu extends appComponent {
  
  public BackgroundWindow background;
  public TextButton filebutton;
  public TextButton editbutton;
  public static final float menuoffset = 90f;

  public appMenu(MainApp curParent, float fx, float fy, float fwidth, float fheight) {
    super(curParent,fx,fy,fwidth,fheight);
  }
  
  public void setup() {
    super.setup();
    if(ui.DEBUG >= 1) PApplet.println("] Setup Menu:");
    //set and add Color
    color = new ColorBundle("Menu",color(234,234,234,234),color(10,10,10,200),0,
        color(255,255,255,255),color(10,10,10,200),0);
    parent.ColorManager.addColor(color);
    //add backgroundwindow
    background = new BackgroundWindow("MenuBackground",position.x,position.y-menuoffset-dimension.y+2);
    background.addTo(parent.WindowManager);
    parent.WindowManager.setOnTop(background.id());
    background.dimension.set(dimension.x,dimension.y+menuoffset);
    background.Color.set("Menu");
    background.behavior.addBehavior( new MenuBehavior("Menu",6,position.x,
        position.y-menuoffset-dimension.y+2,dimension.y-2,menuoffset) );
    //add menuentry file
    filebutton = new TextButton("FileButton",10,
        (dimension.y)/2-FONTSIZE/2-4+menuoffset,MENU_FILEBUTTON,FONTSIZE);
    filebutton.addTo(background);
    filebutton.Color.set("Menu");
    filebutton.Color.setBorder(false);
    //add menuentry edit
    editbutton = new TextButton("EditButton",35,
        (dimension.y)/2-FONTSIZE/2-4+menuoffset,MENU_EDITBUTTON,FONTSIZE);
    editbutton.addTo(background);
    editbutton.Color.set("Menu");
    editbutton.Color.setBorder(false);
    
  }
  
  class MenuBehavior extends zuiLooseBehavior {
    
    public vector showenpos;
    public vector fullpos;
    public vector hiddenpos;
    
    public MenuBehavior(String sname, float floose, float xpos, float hidden, float showen, float rest) {
      super(sname,floose);
      hiddenpos = new vector(xpos,hidden);
      showenpos = new vector(xpos,hidden+showen);
      fullpos   = new vector(xpos,hidden+showen+rest);
    }
    
    public void update() {
      super.update();
      if(object.over()) {
         setPosition(showenpos);
      } else {
        setPosition(hiddenpos);
      }
    }
  }

}
