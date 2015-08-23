package zuilib.manager;

import java.awt.event.MouseEvent;

import zuilib.utils.vector;

public class simpleMouseManager extends mousemanager {
  
  public vector mousepos;
  public vector offset_pos;

  public simpleMouseManager(String sname, boolean benable) {
    super(sname, benable);
    mousepos = new vector(0,0);
    offset_pos = new vector(0,0);
  }
  
  public void setup() {
    parent.getParent().registerMouseEvent(this);
    publishFunction("mousePressed");
    publishFunction("mouseReleased");
    publishFunction("mouseClicked");
    publishFunction("mouseDragged");
    publishFunction("mouseMoved");
  }
  
  public vector getMouse() {
    return vector.VecAdd(mousepos,offset_pos);
  }
  
  public vector getPosition() {
    return offset_pos;
  }
  
  public void setPosition(float ix, float iy) {
    setPosition(new vector(ix,iy));
  }

  public void setPosition(vector v) {
    offset_pos = new vector(v);
  }

  public void setPositionRelative(float ix, float iy) {
    offset_pos.Add( new vector(ix,iy) );
  }
  
  public void setPositionRelative(vector v) {
    offset_pos.Add( v );
  } 
  
  public void mouseEvent(MouseEvent event) {
    if(enable) {
      mousepos.set(event.getX(),event.getY());
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
  }
  
  public void mouseClicked() {
    if(enable) useFunction("mouseClicked", new Object[] {} );
  }
  
  public void mousePressed() {
    if(enable) useFunction("mousePressed", new Object[] {} );
  }
  
  public void mouseDragged() {
    if(enable) useFunction("mouseDragged", new Object[] {} );
  }
  
  public void mouseReleased() {
    if(enable) useFunction("mouseReleased", new Object[] {} );
  }
  
  public void mouseMoved() {
    if(enable) useFunction("mouseMoved", new Object[] {} );
  }
  

}
