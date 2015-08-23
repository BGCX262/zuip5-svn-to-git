package zuilib.components;

import processing.core.PConstants;

public class CircleHandle_Circle extends circlehandle {
  
  private float boxsize;

  public CircleHandle_Circle(String sname, float fx, float fy, float fsize, float fboxsize, float flength) {
    super(sname,fx,fy,fsize,fboxsize,flength);
    box = new CircleButton(Name.get()+"_Circle",0,0);
    box.setMode(PConstants.CENTER);
    boxsize = fboxsize;
  }
  
  public void setup() {
    super.setup();
    ((CircleButton) box).dimension.set(boxsize);
    setValuePercent(0);
  }

}
