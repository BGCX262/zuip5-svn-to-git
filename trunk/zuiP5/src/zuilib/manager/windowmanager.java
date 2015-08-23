package zuilib.manager;

import processing.core.PGraphics;
import zuilib.core.manager;
import zuilib.core.window;
import zuilib.core.zuiObject;
import zuilib.properties.PositionController;
import zuilib.properties.RotationController;
import zuilib.properties.ScaleController;
import zuilib.utils.vector;

public class windowmanager extends manager {

  public PositionController  position;
  public RotationController  rotation;
  public ScaleController     scale;
  public PGraphics graphic;
  public boolean locked;
  
  public windowmanager(String sname, boolean benable) {
    super(sname, benable);
    Type = "window";
    locked = false;
  }
  
  public void setup() {
    super.setup();
    graphic = getPApplet().g;
    this.link("mouse");
    this.link("keyboard");
    this.link("draw");
    position = new PositionController(new vector(getPApplet().width/2,getPApplet().height/2));
    position.setup(this);
    rotation = new RotationController();
    rotation.setup(this);
    scale    = new ScaleController();
    scale.setup(this);
  }
  
  public window get(String sname) {
    return null;
  }
  
  public window get(int i) {
    return null;
  }
  
  public window getWindow(String sname) {
    return null;
  }
  
  public window getWindow(int i) {
    return null;
  }
  
  public int addWindow(window newwindow) {
    return -1;
  }
  
  public vector getMouse() {
    vector mouse = ((mousemanager) getManagerByType("mouse")).get();
    mouse.Sub( position.get() );
    mouse = scale.unScale(mouse);
    mouse.Rot(-rotation.get());
    return new vector( mouse );
  }
  
  public void pre() {return;}
  public void predraw() {return;}
  public void draw() {return;}
  public void postdraw() {return;}
  public void keyPressed(char key) {return;}
  public void keyReleased(char key) {return;}
  public void keyTyped(char key) {return;}
  public void mousePressed() {return;}
  public void mouseClicked() {return;}
  public void mouseDragged() {return;}
  public void mouseMoved() {return;}
  public void mouseReleased() {return;}
  

}
