package zuilib.core;

import processing.core.PApplet;
import zuilib.manager.windowmanager;
import zuilib.utils.vector;

public class RotationControler extends controlerObject {

  private float rotation;
  private boolean enable;
  
  public RotationControler(zuiAtomObject curParent) {
    super(curParent);
    rotation = 0;
    enable = true;
  }
  
  public RotationControler(zuiAtomObject curParent,boolean start_enable) {
    super(curParent);
    rotation = 0;
    enable = start_enable;
  }
  
  public RotationControler(zuiAtomObject curParent,float start_rot) {
    super(curParent);
    rotation = start_rot;
    enable = true;
  }
  
  public RotationControler(zuiAtomObject curParent,float start_rot,boolean start_enable) {
    super(curParent);
    rotation = start_rot;
    enable = start_enable;
  }
  
  public RotationControler() {
    super();
    rotation = 0;
    enable = true;
  }
  
  public RotationControler(boolean start_enable) {
    super();
    rotation = 0;
    enable = start_enable;
  }
  
  public RotationControler(float start_rot) {
    super();
    rotation = start_rot;
    enable = true;
  }
  
  public RotationControler(float start_rot,boolean start_enable) {
    super();
    rotation = start_rot;
    enable = start_enable;
  }
  
  public RotationControler(RotationControler prevRotC) {
    super(prevRotC.getParentObject());
    rotation = prevRotC.get();
    enable = prevRotC.isEnable();
  }
  
  public void setEnable(boolean newenable) {
    enable = newenable;
  }
  
  public boolean isEnable() {
    return enable;
  }
  
  public void setRotation(float newrot) {
    rotation = newrot;
  }
  
  public void setRot(float newrot) {
    setRotation(newrot);
  }
  
  public void set(float newrot) {
    setRotation(newrot);
  }
  
  public float get() {
    return getRotation();
  }
  
  public float getRot() {
    return getRotation();
  }
  
  public float getRotation() {
    return rotation;
  }
  
  public float getRotationAbsolute() {
    float rot = 0;
    try {
      if(parentObject.parentObject.parentObject == null) {
          rot = ((windowmanager) getParentObject().getParentObject()).rotation.getRotation();
        } else if(parentObject.parentObject != null) {
          rot = ((zuiObject) getParentObject().getParentObject()).rotation.getRotationAbsolute();
      }
    } catch (Exception e) {
      rot = 0;
    }
    return rotation + rot;
  }
  
  public float getParentsRotation() {
    return getRotationAbsolute() - rotation;
  }
  
  public float getAbsolute() {
    return getRotationAbsolute();
  }
  
  public float getParents() {
    return getParentsRotation();
  }
  
  public void doRotate() {
    if(enable) {
      if(getZUI().DEBUG > 9) PApplet.println("- doRotation: "+getParentObject().getClass().getName()+"  Rotation: "+rotation);
      graphic.rotate(rotation);
    }
  }
  
  public void doTask() {
    doRotate();
  }
  
  public void setRotationRelative(float newrot) {
    rotation += newrot;
  }

  public void setRelative(float newrot) {
    setRotationRelative(newrot);
  }
  
  public void setRotRel(float newrot) {
    setRotationRelative(newrot);
  }

  public void setRel(float newrot) {
    setRelative(newrot);
  }
  
  public vector rotate(vector v) {
    vector result = new vector(v);
    result.Rot(rotation);
    return result;
  }

}
