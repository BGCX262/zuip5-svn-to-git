package zuilib.utils;

import zuilib.core.zuiAtomObject;

public class CircleAreaDimension extends areaDimensionControler {

  public float size;

  public CircleAreaDimension(zuiAtomObject curParent) {
    super(curParent);
    CircleDimensionControler_init(10);
  }

  public CircleAreaDimension(zuiAtomObject curParent, vector start_pos) {
    super(curParent, start_pos);
    CircleDimensionControler_init(10);
  }
  
  public CircleAreaDimension(zuiAtomObject curParent, vector start_rotpt, vector start_pos) {
    super(curParent, start_rotpt,start_pos);
    CircleDimensionControler_init(10);
  }
  
  public CircleAreaDimension(zuiAtomObject curParent, vector start_pos, float fsize) {
    super(curParent,start_pos);
    CircleDimensionControler_init(fsize);
  }
  
  public CircleAreaDimension(zuiAtomObject curParent, vector start_rotpt, vector start_pos, float fsize) {
    super(curParent,start_rotpt,start_pos);
    CircleDimensionControler_init(fsize);
  }
  
  public CircleAreaDimension(zuiAtomObject curParent, float fsize) {
    super(curParent);
    CircleDimensionControler_init(fsize);
  }
  
  public CircleAreaDimension() {
    super();
    CircleDimensionControler_init(10);
  }

  public CircleAreaDimension(vector start_pos) {
    super(start_pos);
    CircleDimensionControler_init(10);
  }
  
  public CircleAreaDimension(vector start_rotpt, vector start_pos) {
    super(start_rotpt,start_pos);
    CircleDimensionControler_init(10);
  }
  
  public CircleAreaDimension(vector start_pos, float fsize) {
    super(start_pos);
    CircleDimensionControler_init(fsize);
  }
  
  public CircleAreaDimension(vector start_rotpt, vector start_pos, float fsize) {
    super(start_rotpt,start_pos);
    CircleDimensionControler_init(fsize);
  }
  
  public CircleAreaDimension(float fsize) {
    super();
    CircleDimensionControler_init(fsize);
  }
  
  private void CircleDimensionControler_init(float fsize) {
    size = fsize;
  }
  
  public void setDimension(float fsize) {
    size = fsize;
  }
  
  public void setDim(float fsize) {
    setDimension(fsize);
  }
  
  public void set(vector newpos,float fsize) {
    position.set(newpos);
    setDimension(fsize);
    setRotationPoint();
  }
  
  public void set(float fx, float fy, float fsize) {
    position.set(fx,fy);
    setDimension(fsize);
    setRotationPoint();
  }
  
  public void setDimensionRelative(float fsize) {
    size += fsize;
  }
  
  public void setDimRel(float fsize) {
    setDimensionRelative(fsize);
  }
  
  public void setRelative(vector newpos,float fsize) {
    position.setRelative(newpos);
    setDimensionRelative(fsize);
    setRotationPoint();
  }
  
  public void setRelative(float fx, float fy, float fsize) {
    position.setRelative(fx,fy);
    setDimensionRelative(fsize);
    setRotationPoint();
  }
  
  public void setRel(vector newpos,float fsize) {
    setRelative(newpos,fsize);
  }
  
  public void setRel(float fx, float fy, float fsize) {
    setRelative(fx,fy,fsize);
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
