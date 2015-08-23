package enginelib.manager;

import enginelib.objectControler3D;
import zuilib.core.manager;

public class engine3DManager extends manager {

  public objectControler3D environment3D;
  
  public engine3DManager(String sname, boolean benable) {
    super(sname, benable);
    Type = "3D";
  }
  
  public void setup() {
    super.setup();
    environment3D = new objectControler3D(getPApplet(),false);
    this.link("mouse");
    this.link("keyboard");
    this.link("draw");
  }
  
  public objectControler3D getEnvironment3D() {
    return environment3D;
  }
  
  public objectControler3D getEnvironment() {
    return getEnvironment3D();
  }
  
  public objectControler3D get3D() {
    return getEnvironment3D();
  }
  
  public objectControler3D get() {
    return getEnvironment3D();
  }
  
  public void pre() {return;}
  public void draw() {return;}
  public void postdraw() {return;}
  
  public void predraw() {
    environment3D.display();
  }
  
  public void mousePressed() {
    environment3D.mousePressed();
  }
  
  public void mouseClicked() {
    environment3D.mouseClicked();
  }
  
  public void mouseDragged() {
    environment3D.mouseDragged();
  }
  
  public void mouseReleased() {
    environment3D.mouseReleased();
  }
  
  public void mouseMoved() {
    environment3D.mouseMoved();
  }
  
  public void keyTyped(char key) {
    environment3D.keyTyped();
  }
  
  public void keyPressed(char key) {
    environment3D.keyPressed();
  }
  
  public void keyReleased(char key) {
    environment3D.keyReleased();
  }
  

}
