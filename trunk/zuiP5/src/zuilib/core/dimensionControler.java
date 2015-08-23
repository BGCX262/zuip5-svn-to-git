package zuilib.core;

import zuilib.utils.vector;

public class dimensionControler extends controlerObject {
  
  private vector rotationpoint;
  public boolean over;

  public dimensionControler(zuiAtomObject curParent) {
    super(curParent);
    dimensionControler_init(new vector(0,0));
  }
  
  public dimensionControler(zuiAtomObject curParent,vector start_rotpt) {
    super(curParent);
    dimensionControler_init(start_rotpt);
  }
  
  public dimensionControler() {
    super();
    dimensionControler_init(new vector(0,0));
  }
  
  public dimensionControler(vector start_rotpt) {
    super();
    dimensionControler_init(start_rotpt);
  }
  
  private void dimensionControler_init(vector newrotpt) {
    rotationpoint = new vector(newrotpt);
    over = false;
  }
  
  public void setRotationPoint(vector newrotpt) {
    rotationpoint = new vector(newrotpt);
  }
  
  public void setRotationPoint(float fx, float fy) {
    rotationpoint = new vector(fx,fy);
  }
  
  public vector getRotationPoint() {
    return rotationpoint;
  }
  
  public vector getRotPt() {
    return getRotationPoint();
  }
  
  public void setOver(boolean newover) {
    over = newover;
  }
  
  public boolean getOver() {
    return over;
  }
  
  public float getSize() {
    return 0;
  }
  
  public void setRotationPointRelative(vector relrotpt) {
    rotationpoint.Add(relrotpt);
  }
  
  public void setRotationPointRelative(float fx,float fy) {
    rotationpoint.Add(new vector(fx,fy));
  }
  
  public void setRotPtRel(vector relrotpt) {
    setRotationPointRelative(relrotpt);
  }
  
  public void setRotPtRel(float fx,float fy) {
    setRotationPointRelative(fx,fy);
  }

}
