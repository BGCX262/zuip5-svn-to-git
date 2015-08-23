package zuilib.components;

import zuilib.utils.vector;

public class scrollbar extends recthandle {
  
  private float newvalue;
  private vector newpos;
  public float loose;

  public scrollbar(String sname, float fx, float fy, float fwidth, float fheight, float flength) {
    super(sname, fx, fy, fwidth, fheight, flength);
    loose = 6;
  }
  
  public scrollbar(String sname) {
    super(sname, 0f,0f,10f,10f,1f);
    loose = 6;
  }

  public void setup() {
    super.setup();
    //box.Color = new ColorControler();
    //box.Color.setup(box);
    newpos = new vector(0,0);
  }
  
  public void setLoose(float floose) {
    loose = floose;
  }
  
  public void boxMove() {
    vector prevpos = box.position.get();
    float prev_value = value;
    super.boxMove();
    if(!prevpos.equals(box.position.get())) {
      newpos = new vector(box.position.get());
      newvalue = value;
    }
    value = prev_value;
    box.position.set(prevpos);
  }
  
  public void setValuePercent(float fvalue) {
    vector prevpos = box.position.get();
    float prev_value = value;
    super.setValuePercent(fvalue);
    if(!prevpos.equals(box.position.get())) {
      newpos = new vector(box.position.get());
      newvalue = value;
    }
    value = prev_value;
    box.position.set(prevpos);
  }
  
  public void draw() {
    Color.doBackground();
    graphic.rectMode(mode);
    graphic.rect(0,0,dimension.width,dimension.height);
  }
  
  public void update() {
    super.update();
    vector diff = vector.VecSub(newpos, box.position.get());
    float d = vector.VecVal(diff);
    if(d > 1) {
      diff.Mul(1/loose);
      box.position.setRel(diff);
      value += (newvalue-value)/loose;
    } else if(d > 0 && d <= 1) {
      value = newvalue;
      box.position.set(newpos);
    }
  }

}
