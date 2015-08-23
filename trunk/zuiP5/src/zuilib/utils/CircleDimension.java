package zuilib.utils;

import zuilib.core.dimensionControler;
import zuilib.core.zuiAtomObject;

public class CircleDimension extends dimensionControler {

  public float size;

  public CircleDimension(zuiAtomObject curParent) {
    super(curParent);
    size = 10;
  }
  
  public CircleDimension(zuiAtomObject curParent, vector start_rotpt) {
    super(curParent, start_rotpt);
    size = 10;
  }
  
  public CircleDimension(zuiAtomObject curParent, vector start_rotpt, float fsize) {
    super(curParent,start_rotpt);
    size = fsize;
  }
  
  public CircleDimension(zuiAtomObject curParent, float fsize) {
    super(curParent);
    size = fsize;
  }
  
  public CircleDimension() {
    super();
    size = 10;
  }
  
  public CircleDimension(vector start_rotpt) {
    super(start_rotpt);
    size = 10;
  }
  
  public CircleDimension(vector start_rotpt, float fsize) {
    super(start_rotpt);
    size = fsize;
  }
  
  public CircleDimension(float fsize) {
    super();
    size = fsize;
  }
  
  public void setDimension(float fsize) {
    size = fsize;
  }
  
  public void setDim(float fsize) {
    setDimension(fsize);
  }
  
  public void set(vector newpos,float fsize) {
    setDimension(fsize);
    setRotationPoint();
  }
  
  public void set(float fsize) {
    setDimension(fsize);
    setRotationPoint();
  }
  
  public void setDimensionRelative(float fsize) {
    size += fsize;
  }
  
  public void setDimRel(float fsize) {
    setDimensionRelative(fsize);
  }
  
  public void setRelative(float fsize) {
    setDimensionRelative(fsize);
    setRotationPoint();
  }
  
  public void setRel(float fsize) {
    setRelative(fsize);
  }
  
  public float getSize() {
    return size;
  }
  
  public void setRotationPoint() {
    setRotationPoint(new vector(0,0));
  }
  
  public void setRotPt() {
    setRotationPoint();
  }
  

}
