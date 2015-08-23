package enginelib;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import processing.core.PApplet;

public class objectControler3D extends Object3D {
  
  public PApplet Parent;
  
  public objectControler3D(PApplet curParent) {
    super("");
    init(curParent,true);
  }
  
  public objectControler3D(PApplet curParent,boolean useRegistration) {
    super("");
    init(curParent,useRegistration);
  }
  
  public void init(PApplet curParent, boolean useRegistration) {
    Parent = curParent;
    graphic = Parent.g;
    if(useRegistration) {
      Parent.registerDraw(this);
      Parent.registerKeyEvent(this);
      Parent.registerMouseEvent(this);
      Parent.registerPost(this);
      Parent.registerPre(this);
      Parent.registerDispose(this);
      Parent.registerSize(this);
    }
    
  }
  
  public void draw() {
    update();
    predraw();
    postdraw();
  }
  
  public void predraw() {
    graphic.pushMatrix();
    graphic.translate(graphic.width/2, graphic.height/2,0);
  }
  
  public void display() {
    draw();
  }
  
  public void keyEvent(KeyEvent event) {
    switch (event.getID()) {
    case KeyEvent.KEY_TYPED:
      this.keyTyped();
      break;
    case KeyEvent.KEY_RELEASED:
      this.keyReleased();
      break;
    case KeyEvent.KEY_PRESSED:
      this.keyPressed();
      break;
    }
  }
  
  public void mouseEvent(MouseEvent event) {
    switch (event.getID()) {
    case MouseEvent.MOUSE_PRESSED:
      this.mousePressed();
      break;
    case MouseEvent.MOUSE_RELEASED:
      this.mouseReleased();
      break;
    case MouseEvent.MOUSE_CLICKED:
      this.mouseClicked();
      break;
    case MouseEvent.MOUSE_DRAGGED:
      this.mouseDragged();
      break;
    case MouseEvent.MOUSE_MOVED:
      this.mouseMoved();
      break;
    }
  }

  public objectControler3D getControler() {
    return this;
  }

  public PApplet getPApplet() {
    return Parent;
  }

  public IObject3D getParent() {
    return this;
  }
  
}
