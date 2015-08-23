package zuilib.components;

public class Timer extends interactivecomponent {
 
  public int interval;
  public int frame;
  public boolean enable;
  
  public Timer(String sname, int iinterval, boolean benable) {
    super(sname,0,0);
    interval = iinterval;
    enable = benable;
    frame = 0;
  }
  
  public void setup() {
    super.setup();
    addFunction("onTimer");
  }
  
  public void update() {
    super.update();
    if(enable) {
      frame = (frame+1)%interval;
      if(frame == 0) {
        useFunction("onTimer");
      }
    }
  }
  
  public void setInterval(int iinterval) {
    interval = iinterval;
    frame = 0;
  }
  
  public void setEnable(boolean benable) {
    enable = benable;
    if(enable) {frame = 0;}
  }
  
}
