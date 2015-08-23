package zuilib.core;

import processing.core.PApplet;
import zuilib.manager.windowmanager;
import zuilib.utils.vector;

public class ScaleControler extends controlerObject {

  private float scale;
  private boolean enable;
  private float lod_min;
  private float lod_max;

  public ScaleControler(zuiAtomObject curParent) {
    super(curParent);
    ScaleConstroler_init(1,true);
  }
  
  public ScaleControler(zuiAtomObject curParent,boolean start_enable) {
    super(curParent);
    ScaleConstroler_init(1,start_enable);
  }  
  
  public ScaleControler(zuiAtomObject curParent, float start_scale) {
    super(curParent);
    ScaleConstroler_init(start_scale,true);
  }
  
  public ScaleControler(zuiAtomObject curParent, float start_scale,boolean start_enable) {
    super(curParent);
    ScaleConstroler_init(start_scale,start_enable);
  }
  
  public ScaleControler() {
    super();
    ScaleConstroler_init(1,true);
  }
  
  public ScaleControler(boolean start_enable) {
    super();
    ScaleConstroler_init(1,start_enable);
  }  
  
  public ScaleControler(float start_scale) {
    super();
    ScaleConstroler_init(start_scale,true);
  }
  
  public ScaleControler(float start_scale,boolean start_enable) {
    super();
    ScaleConstroler_init(start_scale,start_enable);
  }
  
  public ScaleControler(ScaleControler prevScaleC) {
    super(prevScaleC.getParentObject());
    ScaleConstroler_init(prevScaleC.get(),prevScaleC.isEnable());
    lod_min = prevScaleC.lod_min;
    lod_max = prevScaleC.lod_max;
  }
  
  private void ScaleConstroler_init(float start_scale,boolean start_enable) {
    scale = start_scale;
    enable = start_enable;
    lod_min = lod_max = 0f;
  }
  
  public void setEnable(boolean newenable) {
    enable = newenable;
  }
  
  public boolean isEnable() {
    return enable;
  }
  
  public void setScale(float newscale) {
    scale = newscale;
  }
  
  public void set(float newscale) {
    setScale(newscale);
  }
  
  public float getScale() {
    return scale;
  }
  
  public float getScaleAbsolute() {
    float s = 1;
    try {
      //s = ((zuiObject) getParent().getParentObject()).scale.getScaleAbsolute();
      if(parentObject.parentObject.parentObject == null) {
          s = ((windowmanager) getParentObject().getParentObject()).scale.getScale();
        } else if(parentObject.parentObject != null) {
          s = ((zuiObject) getParentObject().getParentObject()).scale.getScaleAbsolute();
      }
    } catch (Exception e) {
      s = 1;
    }
    return scale * s;
  }
  
  public float getParentsScale() {
    return getScaleAbsolute() / scale;
  }
  
  public float getParents() {
    return getParentsScale();
  }
  
  public float get() {
    return getScale();
  }
  
  public float getAbsolute() {
    return getScaleAbsolute();
  }
  
  public void doScale() {
    if(enable) {
      if(getZUI().DEBUG > 9) PApplet.println("- doScale: "+getParentObject().getClass().getName()+"  Scale "+scale);
      graphic.scale(scale);
    }
  }
  
  public void doTask() {
    doScale();
  }
  
  public vector unScale(vector v) {
    vector result = new vector(v);
    result.Mul(1/getScale());
    return result;
  }
  
  public vector unScaleGlobal(vector v) {
    vector result = new vector(v);
    result.Mul(1/getScaleAbsolute());
    return result;
  }
  
  public vector doScale(vector v) {
    vector result = new vector(v);
    result.Mul(getScale());
    return result;
  }
  
  public vector doScaleGlobal(vector v) {
    vector result = new vector(v);
    result.Mul(getScaleAbsolute());
    return result;
  }
  
  public void setScaleRelative(float newscale) {
    scale += newscale;
  }

  public void setRelative(float newscale) {
    setScaleRelative(newscale);
  }

  public void setRel(float newscale) {
    setRelative(newscale);
  }
  
  public void setLOD(float fmin, float fmax) { //LOD - Level Of Detail
    lod_min = PApplet.min(fmin,fmax);
    lod_max = PApplet.max(fmin,fmax);
  }
  
  public boolean getLOD() {
    if(lod_min == lod_max) return true;
    float global_scale = getAbsolute();
    return (global_scale == PApplet.constrain(global_scale,lod_min,lod_max));
  }
  

}
