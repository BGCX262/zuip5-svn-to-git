package zuilib.manager;

import processing.core.PApplet;
import zuilib.utils.vector;

public class CursorMouseManager extends simpleMouseManager {

  public vector pos;
  public Cursor cursor;
  public boolean visible;
  public float angle;

  public CursorMouseManager(String sname, boolean benable) {
    super(sname,benable);
    pos = new vector(0,0);
    visible = true;
    cursor = null;
    angle = 0;
  }

  public void display() {
    if(enable) {
      if(cursor != null) {
        parent.getParent().pushMatrix();
        vector v = vector.VecAdd(pos,offset_pos);
        parent.getParent().translate(v.x,v.y);
        if(cursor.rotating) {
          parent.getParent().rotate(angle+cursor.rotation);
        }
        cursor.display();
        parent.getParent().popMatrix();
      }
    }
  }
  
  public void pre() {
    if(enable) update();
  }

  public void postdraw() {
    if(enable) display();
  }
  
  public void draw() {return;}
  public void predraw() {return;}

  public vector getPosition() {
    return vector.VecAdd(pos,offset_pos);
  }

  public void mouseClicked() {
    super.mouseClicked();
    if(enable) {
      if(cursor != null) {
        cursor.mouseClicked();
      }
    }
  }

  public void mouseDragged() {
    super.mouseDragged();
    if(enable) {
      if(cursor != null) {
        cursor.mouseDragged();
      }
    }
  }

  public void mouseMoved() {
    super.mouseMoved();
    if(enable) {
      if(cursor != null) {
        cursor.mouseMoved();
      }
    }
  }

  public void mousePressed() {
    super.mousePressed();
    if(enable) {
      if(cursor != null) {
        cursor.mousePressed();
      }
    }
  }

  public void mouseReleased() {
    super.mouseReleased();
    if(enable) {
      if(cursor != null) {
        cursor.mouseReleased();
      }
    }
  }

  public void setAngle(float fangle) {
    angle = fangle;
  }

  public void setCursor(Cursor newc) {
    if(getZUI().DEBUG >= 5) PApplet.println("* Set Cursor "+newc.getClass().getName());
    cursor = newc;
    newc.setParentObject(this);
    if(cursor == null) {
      parent.getParent().cursor();
    } else {
      parent.getParent().noCursor();
    }
  } 

  public void setRotation(float frot) {
    if(cursor != null) {
      cursor.rotation = frot;
    }
  }

  public void setup() {
    super.setup();
    this.link("draw");
  }

  public void setVisible(boolean bvis) {
    visible = bvis;
    if(bvis && cursor == null) {
      parent.getParent().cursor();
    } 
    else {
      parent.getParent().noCursor();
    }
  }

  public void update() {
    if(enable) {
      pos.Set(mousepos);
      pos.Add(offset_pos);
      if(cursor != null) {
        cursor.update();
      }
    }
  }

}
