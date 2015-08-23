package zuilib.utils;

import zuilib.core.zuiAtomObject;

public class RectAreaDimension extends areaDimensionControler {

  public float width;
  public float height;

  public RectAreaDimension(zuiAtomObject curParent) {
    super(curParent);
    RectDimensionControler_init(10,10);
  }

  public RectAreaDimension(zuiAtomObject curParent, vector start_pos) {
    super(curParent, start_pos);
    RectDimensionControler_init(10,10);
  }
  
  public RectAreaDimension(zuiAtomObject curParent, vector start_rotpt, vector start_pos) {
    super(curParent, start_rotpt,start_pos);
    RectDimensionControler_init(10,10);
  }
  
  public RectAreaDimension(zuiAtomObject curParent, vector start_pos, float fwidth, float fheight) {
    super(curParent,start_pos);
    RectDimensionControler_init(fwidth, fheight);
  }
  
  public RectAreaDimension(zuiAtomObject curParent, vector start_rotpt, vector start_pos, float fwidth, float fheight) {
    super(curParent,start_rotpt,start_pos);
    RectDimensionControler_init(fwidth, fheight);
  }
  
  public RectAreaDimension(zuiAtomObject curParent, float fwidth, float fheight) {
    super(curParent);
    RectDimensionControler_init(fwidth, fheight);
  }
  
  public RectAreaDimension() {
    super();
    RectDimensionControler_init(10,10);
  }

  public RectAreaDimension(vector start_pos) {
    super(start_pos);
    RectDimensionControler_init(10,10);
  }
  
  public RectAreaDimension(vector start_rotpt, vector start_pos) {
    super(start_rotpt,start_pos);
    RectDimensionControler_init(10,10);
  }
  
  public RectAreaDimension(vector start_pos, float fwidth, float fheight) {
    super(start_pos);
    RectDimensionControler_init(fwidth, fheight);
  }
  
  public RectAreaDimension(vector start_rotpt, vector start_pos, float fwidth, float fheight) {
    super(start_rotpt,start_pos);
    RectDimensionControler_init(fwidth, fheight);
  }
  
  public RectAreaDimension(float fwidth, float fheight) {
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
  
  public void set(vector newpos,float fwidth, float fheight) {
    position.set(newpos);
    setDimension(fwidth,fheight);
    setRotationPoint();
  }
  
  public void set(float fx, float fy, float fwidth, float fheight) {
    position.set(fx,fy);
    setDimension(fwidth,fheight);
    setRotationPoint();
  }
  
  public void setDimensionRelative(float fwidth, float fheight) {
    width += fwidth;
    height += fheight;
  }
  
  public void setDimRel(float fwidth,float fheight) {
    setDimensionRelative(fwidth,fheight);
  }
  
  public void setRelative(vector newpos,float fwidth, float fheight) {
    position.setRelative(newpos);
    setDimensionRelative(fwidth,fheight);
    setRotationPoint();
  }
  
  public void setRelative(float fx, float fy, float fwidth, float fheight) {
    position.setRelative(fx,fy);
    setDimensionRelative(fwidth,fheight);
    setRotationPoint();
  }
  
  public void setRel(vector newpos,float fwidth, float fheight) {
    setRelative(newpos,fwidth,fheight);
  }
  
  public void setRel(float fx, float fy, float fwidth, float fheight) {
    setRelative(fx,fy,fwidth,fheight);
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
  
  public void setRotPt() {
    setRotationPoint();
  }
  
  

}
