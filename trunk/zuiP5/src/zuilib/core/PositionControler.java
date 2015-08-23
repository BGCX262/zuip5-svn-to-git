package zuilib.core;

import processing.core.PApplet;
import zuilib.manager.windowmanager;
import zuilib.utils.vector;

public class PositionControler extends controlerObject {
  
  private vector position;
  public boolean rotating;

  public PositionControler(zuiAtomObject curParent) {
    super(curParent);
    position = new vector(0,0);
    rotating = true;
  }
  
  public PositionControler(zuiAtomObject curParent , vector start_pos) {
    super(curParent);
    position = new vector(start_pos);
    rotating = true;
  }
  
  public PositionControler() {
    super();
    position = new vector(0,0);
    rotating = true;
  }
  
  public PositionControler(vector start_pos) {
    super();
    position = new vector(start_pos);
    rotating = true;
  }
  
  public PositionControler(PositionControler prevPosC) {
    super(prevPosC.getParentObject());
    position = new vector(prevPosC.get());
    rotating = prevPosC.isRotating();
  }
  
  public void setRotating(boolean newrot) {
    rotating = newrot;
  }
  
  public boolean isRotating() {
    return rotating;
  }
  
  public void setPosition(vector newpos) {
    position = new vector(newpos);
  }
  
  public void setPosition(float fx, float fy) {
    position = new vector(fx,fy);
  }
  
  public void setPos(vector newpos) {
    setPosition(newpos);
  }
  
  public void setPos(float fx,float fy) {
    setPosition(fx,fy);
  }
  
  public void set(vector newpos) {
    setPosition(newpos);
  }
  
  public void set(float fx,float fy) {
    setPosition(fx,fy);
  }
  
  public vector get() {
    return getPosition();
  }
  
  public vector getAbsolute() {
    return getPositionAbsolute();
  }
  
  public vector getPosition() {
    return new vector(position);
  }
  
  public vector getPos() {
    return getPosition();
  }
  
  public void setX(float fx) {
    position.x = fx;
  }
  
  public void setY(float fy) {
    position.y = fy;
  }
  
  
  @SuppressWarnings("null")
  public vector getPositionAbsolute() {
    vector pos = new vector(0,0);
    try {
        if(getParent().position != this) {pos = getParent().position.getPositionAbsolute();
        } else {
            vector v = null;
            v.Norm();
          }
      } catch (Exception e) {
        try {
          if(parentObject.parentObject.parentObject == null) {
              pos = ((windowmanager) getParentObject().getParentObject()).position.getPosition();
            } else if(parentObject.parentObject != null) {
              pos = ((zuiObject) getParentObject().getParentObject()).position.getPositionAbsolute();
          }
        } catch (Exception e2) {
          pos = new vector(0,0);
        }
    }
    pos.Add(position);
    return new vector(pos);
  }
  
  public vector getParentsPosition() {
    return vector.VecSub(getPositionAbsolute(),position);
  }
  
  public vector getParents() {
    return getParentsPosition();
  }
  /*
  public vector getPositionRotated() {
    vector pos = new vector(position);
    float angle = 0;
    if(rotating) {
      try {
        angle = getParent().rotation.get();
        } catch (Exception e) {
          try {
            angle = ((WindowManager) getParentObject()).rotation.get();
          } catch (Exception e2) {
            angle = 0;
          }
      }
    }
    pos.Rot(angle);
    return pos;
  }
  
  public vector getRotated() {
    return getPositionRotated();
  }
  
  @SuppressWarnings("null")
  public vector getPositionRotatedAbsolute() {
    vector pos = new vector(0,0);
    try {
      if(getParent().position != this) {pos = getParent().position.getPositionRotatedAbsolute();
      } else {
          vector v = null;
          v.Norm();
        }
      } catch (Exception e) {
        try {
            if(parentObject.parentObject.parentObject == null) {
                pos = ((WindowManager) getParentObject().getParentObject()).position.getPositionRotated();
              } else if(parentObject.parentObject != null) {
                pos = ((zuiObject) getParentObject().getParentObject()).position.getPositionRotatedAbsolute();
            }
          } catch (Exception e2) {
            pos = new vector(0,0);
        }
    }
    pos.Add(getPositionRotated());
    return pos;
  }
  
  public vector getRotatedAbsolute() {
    return getPositionRotatedAbsolute();
  }*/
  
  public void doTranslate() {
    if(getZUI().DEBUG >= 7) {
      if(getZUI().DEBUG > 9) PApplet.println("- doTranslate: "+getParentObject().getClass().getName()+" Coords  X: "+position.x+"   Y: "+position.y);
      graphic.stroke(255,100);
      graphic.line(0,0,position.x, position.y);
    }
    graphic.translate(position.x, position.y);
  }
  
  public void doTask() {
    doTranslate();
  }
  /*
  public vector unScaled() {
    vector pos = new vector(position);
    try {
      pos = getParent().scale.unScale(position);
      } catch (Exception e) {
        try {
          pos = ((WindowManager) getParentObject()).scale.unScale(position);
        } catch (Exception e2) {
          if(getZUI().DEBUG > 1) PApplet.println("[WARNING]: unScaled: Parent object doesn't have a property scale.");
          pos = new vector(position);
        }
    }
    return pos;
  }
  
  public vector unScaled(vector givenpos) {
    vector pos = new vector(givenpos);
    try {
      pos = getParent().scale.unScale(givenpos);
      } catch (Exception e) {
        try {
          pos = ((WindowManager) getParentObject()).scale.unScale(givenpos);
        } catch (Exception e2) {
          if(getZUI().DEBUG > 1) PApplet.println("[WARNING]: unScaled: Parent object doesn't have a property scale.");
          pos = new vector(givenpos);
        }
    }
    return pos;
  }
  
  public vector unScaledGlobal() {
    vector pos = new vector(position);
    try {
      pos = getParent().scale.unScaleGlobal(position);
      } catch (Exception e) {
        try {
          pos = ((WindowManager) getParentObject()).scale.unScaleGlobal(position);
        } catch (Exception e2) {
          if(getZUI().DEBUG > 1) PApplet.println("[WARNING]: unScaledGlobal: Parent object doesn't have a property scale.");
          pos = new vector(position);
        }
    }
    return pos;
  }
  
  public vector unScaledGlobal(vector givenpos) {
    vector pos = new vector(givenpos);
    try {
      pos = getParent().scale.unScaleGlobal(givenpos);
      } catch (Exception e) {
        try {
          pos = ((WindowManager) getParentObject()).scale.unScaleGlobal(givenpos);
        } catch (Exception e2) {
          if(getZUI().DEBUG > 1) PApplet.println("[WARNING]: unScaledGlobal: Parent object doesn't have a property scale.");
          pos = new vector(givenpos);
        }
    }
    return pos;
  }
  
  public vector scaled() {
    vector pos = new vector(position);
    try {
      pos = getParent().scale.doScale(position);
      } catch (Exception e) {
        try {
          pos = ((WindowManager) getParentObject()).scale.doScale(position);
        } catch (Exception e2) {
          if(getZUI().DEBUG > 1) PApplet.println("[WARNING]: scaled: Parent object doesn't have a property scale.");
          pos = new vector(position);
        }
    }
    return pos;
  }
  
  public vector scaled(vector givenpos) {
    vector pos = new vector(givenpos);
    try {
      pos = getParent().scale.doScale(givenpos);
      } catch (Exception e) {
        try {
          pos = ((WindowManager) getParentObject()).scale.doScale(givenpos);
        } catch (Exception e2) {
          if(getZUI().DEBUG > 1) PApplet.println("[WARNING]: scaled: Parent object doesn't have a property scale.");
          pos = new vector(givenpos);
        }
    }
    return pos;
  }
  
  public vector scaledGlobal() {
    vector pos = new vector(position);
    try {
      pos = getParent().scale.doScaleGlobal(position);
      } catch (Exception e) {
        try {
          pos = ((WindowManager) getParentObject()).scale.doScaleGlobal(position);
        } catch (Exception e2) {
          if(getZUI().DEBUG > 1) PApplet.println("[WARNING]: scaledGlobal: Parent object doesn't have a property scale.");
          pos = new vector(position);
        }
    }
    return pos;
  }
  
  public vector scaledGlobal(vector givenpos) {
    vector pos = new vector(givenpos);
    try {
      pos = getParent().scale.doScaleGlobal(givenpos);
      } catch (Exception e) {
        try {
          pos = ((WindowManager) getParentObject()).scale.doScaleGlobal(givenpos);
        } catch (Exception e2) {
          if(getZUI().DEBUG > 1) PApplet.println("[WARNING]: scaledGlobal: Parent object doesn't have a property scale.");
          pos = new vector(givenpos);
        }
    }
    return pos;
  }
  */
  public void setPositionRelative(vector newpos) {
    position.Add(newpos);
  }
  
  public void setPositionRelative(float fx, float fy) {
    setPositionRelative(new vector(fx,fy));
  }

  public void setRelative(float fx, float fy) {
    setPositionRelative(fx,fy);
  }

  public void setRelative(vector newpos) {
    setPositionRelative(newpos);
  }
  
  public void setPosRel(vector newpos) {
    setPositionRelative(newpos);
  }
  
  public void setPosRel(float fx, float fy) {
    setPositionRelative(fx,fy);
  }

  public void setRel(float fx, float fy) {
    setRelative(fx,fy);
  }

  public void setRel(vector newpos) {
    setRelative(newpos);
  }
  
  /* TODO public vector drawenPosition() {
    vector pos = scaled();
  } */
  

}
