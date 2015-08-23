package zuilib.utils;

import zuilib.core.dimensionControler;
import zuilib.core.zuiAtomObject;

public class RectDimension extends dimensionControler {

  public float width;
  public float height;

  public RectDimension(zuiAtomObject curParent) {
    super(curParent);
    RectDimensionControler_init(10,10);
  }
  
  public RectDimension(zuiAtomObject curParent, vector start_rotpt) {
    super(curParent, start_rotpt);
    RectDimensionControler_init(10,10);
  }
  
  public RectDimension(zuiAtomObject curParent, vector start_rotpt, float fwidth, float fheight) {
    super(curParent,start_rotpt);
    RectDimensionControler_init(fwidth, fheight);
  }
  
  public RectDimension(zuiAtomObject curParent, float fwidth, float fheight) {
    super(curParent);
    RectDimensionControler_init(fwidth, fheight);
  }
  
  public RectDimension() {
    super();
    RectDimensionControler_init(10,10);
  }
  
  public RectDimension(vector start_rotpt) {
    super(start_rotpt);
    RectDimensionControler_init(10,10);
  }
  
  public RectDimension(vector start_rotpt, float fwidth, float fheight) {
    super(start_rotpt);
    RectDimensionControler_init(fwidth, fheight);
  }
  
  public RectDimension(float fwidth, float fheight) {
    super();
    RectDimensionControler_init(fwidth, fheight);
  }
  
  private void RectDimensionControler_init(float fwidth, float fheight) {
    width = fwidth;
    height = fheight;
  }
  
  public void setDimension(float fwidth, float fheight) {
    width = fwidth;
    height = fheight;
  }
  
  public void setDim(float fwidth,float fheight) {
    setDimension(fwidth,fheight);
  }
  
  public void setDimensionRelative(float fwidth, float fheight) {
    width += fwidth;
    height += fheight;
  }
  
  public void setDimRel(float fwidth,float fheight) {
    setDimensionRelative(fwidth,fheight);
  }  
  
  public void set(float fwidth, float fheight) {
    setDimension(fwidth,fheight);
    setRotationPoint();
  }
  
  public void setRelative(float fwidth, float fheight) {
    setDimensionRelative(fwidth,fheight);
    setRotationPoint();
  }
  
  public void setRel(float fwidth, float fheight) {
    setRelative(fwidth,fheight);
  }
  
  public float getWidth() {
    return width;
  }
  
  public float getHeight() {
    return height;
  }
  
  public void setRotationPoint() {
    setRotationPoint(new vector(width/2,height/2));
  }
  
  public float getSize() {
    return (width+height)/2;
  }
  
  public void setRotPt() {
    setRotationPoint();
  }
  
  

}
