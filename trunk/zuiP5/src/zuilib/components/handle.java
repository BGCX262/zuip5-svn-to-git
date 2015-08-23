package zuilib.components;

import zuilib.properties.ColorController;

public class handle extends interactivecomponent {

  public ColorController Color;
  public float length; // max value 
  public float value = 0.0001f;  // percent
  private float prev_value = value;
  public button box;
  
  public handle(String sname, float fx, float fy, float flength) {
    super(sname,fx,fy);
    length = flength;
    Color = new ColorController();
  }
  
  public void setup() {
    super.setup();
    Color.setup(this);
    addFunction("onChange");
    box.setParentObject(this);
    box.setup();
    box.Color = Color;
  }
  
  public void update() {
    super.update();
    box.update();
    if(value != prev_value) {
      prev_value = value;
      onChange();
    }
  }
  
  public void postdraw() {
    Color.doForeground();
    box.display();
    super.postdraw();
  }
  
  public void mousePressed() {
    super.mousePressed();
    if(!getLock()) {
      setLock(over());
      pressed = getLock() && over();
      //box.mousePressed();
      if(pressed) boxMove();
    }
  }
  
  public void boxMove() {return;}
  
  public void mouseDragged() {
    super.mouseDragged();
    if(getLock() && pressed) {
      boxMove();
    }
  }
  
  public void mouseReleased() {
    super.mouseReleased();
    pressed = false;
  }
  
  public void onChange() {
    useFunction("onChange");
  }
  
  public void setValuePercent(float fvalue) {return;}
  public void setValue(float fvalue) {return;}
  
  public float getValue() {
    return length*value;
  }
  
}

