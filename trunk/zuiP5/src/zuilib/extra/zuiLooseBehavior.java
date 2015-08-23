package zuilib.extra;

import processing.core.PApplet;
import zuilib.core.zuiBehavior;
import zuilib.core.zuiObject;
import zuilib.utils.vector;

public class zuiLooseBehavior extends zuiBehavior {
  
  public float loose;
  public vector newpos;
  
  public zuiLooseBehavior(String sname, float floose) {
    super(sname);
    loose = floose;
  }
  
  public void install(zuiObject parent) {
    super.install(parent);
    newpos = object.position.get();
  }
  
  public void setPosition(vector v) {
    newpos = v;
  }
  
  public void update() {
    vector diff = vector.VecSub(newpos, object.position.get());
    float d = PApplet.abs( vector.VecVal(diff) );
    if(d > 1) {
      diff.Mul(1/loose);
      object.position.setRel(diff);
    } else if(d > 0.001f && d <= 1) {
      object.position.set(newpos);
    }
  }


}
