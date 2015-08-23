package zuilib.zuiEditor;

import processing.core.PApplet;
import zuilib.components.RectButton;
import zuilib.core.window;
import zuilib.core.zuiBehavior;
import zuilib.utils.ColorBundle;
import zuilib.utils.vector;
import zuilib.windows.BackgroundWindow;
import zuilib.windows.CircleWindow;
import zuilib.windows.RectWindow;

public class appEditor extends appComponent {
  
  public window window;
  public String projectname;
  public BackgroundWindow background;
  public ColorBundle color;
  public RectButton[] resizer = new RectButton[8];
  public static int resizerSize = 4;
  public vector windowpos;
  public vector windowsize;

  public appEditor(MainApp curParent, float fx, float fy, float fwidth, float fheight) {
    super(curParent,fx,fy,fwidth,fheight);
    windowpos = new vector(0,0);
  }
  
  public void setup() {
    super.setup();
    if(ui.DEBUG >= 1) PApplet.println("] Setup Editor:");
    color = new ColorBundle("Editor Resize",
        color(10,10,10,100),color(234,234,234,234),color(34,34,34,234),
        color(10,10,10,100),color(255,255,255,234),color(200,0,0,234));
    parent.ColorManager.addColor(color);
    background = new BackgroundWindow("EditorBackground",position.x,position.y);
    background.addTo(parent.WindowManager);
    parent.WindowManager.setZIndex(background.id(), 2);
    background.dimension.set(dimension.x,dimension.y);
    background.Color.setEnable(false);
    background.behavior.addBehavior( new BackgroundUpdate("update") );
    for(int i = 0 ; i < 8 ; i += 1) {
      resizer[i] = new RectButton("Resizer_"+i,4+(resizerSize+4)*i,4);
      resizer[i].addTo(background);
      resizer[i].dimension.set(resizerSize,resizerSize);
      resizer[i].Color.set("Editor Resize");
      resizer[i].behavior.addBehavior( new Resizing("resize",i) );
    }
    
  }
  
  public void newProject(String newname) {
    projectname = newname;
    PApplet.println("] Start new Project:");
    window = new RectWindow(projectname,0,0);
    window.addTo(ui);
    parent.properties.setObject(window);
  }
  
  class Resizing extends zuiBehavior {
    public int id;
    public vector diff;
    public Resizing(String sname, int iid) {
      super(sname);
      id = iid;
    }
    
    public void mousePressed() {
      super.mousePressed();
      diff = vector.VecSub(object.getMouse(),object.position.get());
    }
    
    public void mouseReleased() {
      super.mouseReleased();
      object.pressed = false;
    }
    
    public void mouseDragged() {
      super.mouseDragged();
      vector p = vector.VecSub(object.getMouse(),diff);
      p.Sub(vector.VecSub(window.position.get(),position));
      if(object.pressed) {
        switch(id) {
        case 0://top left
          
          break;
        case 1://top
          
          break;
        case 2://top right
          
          break;
        case 3://left
          object.position.setX(object.position.get().x+p.x);
          PApplet.println(p.x);
          //setWindowWidth(getWindowSize().x+p.x);
          break;
        case 4://right
          setWindowWidth(p.x);
          break;
        case 5://bottom left
          
          break;
        case 6://bottom
          setWindowHeight(p.y);
          break;
        case 7://bottom right
          setWindowSize(p.x,p.y);
          break;
        }
      }
    }
  }
  
  class BackgroundUpdate extends zuiBehavior {
    public BackgroundUpdate(String sname) {super(sname);}
    
    public void update() {
      super.update();
      vector s = getWindowSize();
      if(!windowpos.equals(window.position.get()) || !s.equals(windowsize)) {
        windowpos = window.position.get();
        windowsize = s;
        vector pos = vector.VecSub(window.position.get(),position);
        float w = s.x;
        float h = s.y;
        int r = resizerSize+1;
        resizer[0].position.set(vector.VecSub(pos,new vector(r,r)));        //top left
        resizer[1].position.set(vector.VecAdd(pos,new vector(w/2-r/2,-r))); //top
        resizer[2].position.set(vector.VecAdd(pos,new vector(w,-r)));       //top right
        resizer[3].position.set(vector.VecAdd(pos,new vector(-r,h/2-r/2))); //left
        resizer[4].position.set(vector.VecAdd(pos,new vector(w,h/2-r/2)));  //right
        resizer[5].position.set(vector.VecAdd(pos,new vector(-r,h)));       //bottom left
        resizer[6].position.set(vector.VecAdd(pos,new vector(w/2-r/2,h)));  //bottom
        resizer[7].position.set(vector.VecAdd(pos,new vector(w,h)));        //bottom right
      }
    }
  }
  
  private vector getWindowSize() {
    vector result = new vector(0,0);
    try {
      result = new vector(((RectWindow) window).dimension.width,((RectWindow) window).dimension.height);
    } catch(Exception e) {
      try {
        result = new vector(((CircleWindow) window).dimension.size,((CircleWindow) window).dimension.size);
      } catch(Exception e2) {
        result = new vector(0,0);
      }
    }
    return result;
  }
  
  private void setWindowWidth(float width) {
    vector s = getWindowSize();
    setWindowSize(width,s.y);
  }
  
  private void setWindowHeight(float height) {
    vector s = getWindowSize();
    if(s.x == s.y) setWindowSize(height,s.x);
    else setWindowSize(s.x,height);
  }
  
  private void setWindowSize(float width, float height) {
    try {
      ((RectWindow) window).dimension.width = width;
      ((RectWindow) window).dimension.height = height;
    } catch(Exception e) {
      try {
        ((CircleWindow) window).dimension.size = width;
      } catch(Exception e2) {}
    }
  }

}
