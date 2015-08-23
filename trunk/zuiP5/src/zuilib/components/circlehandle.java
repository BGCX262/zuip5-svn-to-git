package zuilib.components;

import processing.core.PApplet;
import processing.core.PConstants;
import zuilib.properties.CircleDimension;
import zuilib.utils.vector;

public class circlehandle extends handle {
  
  public CircleDimension dimension;
  private float boxsize;
  private float angle;
  
  public circlehandle(String sname, float fx, float fy, float fsize, float fboxsize, float flength) {
    super(sname,fx,fy,flength);
    boxsize = fboxsize;
    dimension = new CircleDimension(fsize);
    angle = 0;
  }
  
  public void setup() {
    super.setup();
    dimension.setup(this);
  }
 
  public void draw() {
    super.draw();
    Color.doBackground();
    graphic.noFill();
    graphic.ellipseMode(PConstants.CENTER);
    float s = dimension.size-boxsize/2;
    graphic.ellipse(0,0,s,s);
  }
  
  public boolean over() {
    dimension.over = overCircle(dimension.size+boxsize/2, mode);
    return dimension.over;
  }
  
  public void boxMove() {
    angle = vector.VecAng(getMouse());
    float r = dimension.size/2-boxsize/4;
    float x = r*PApplet.cos(angle);
    float y = r*PApplet.sin(angle);
    box.position.set(x,y);
    if(angle < 0) { angle += PConstants.TWO_PI;}
    value = angle / PConstants.TWO_PI;
    if(value < 0) {value += 1;}
  }
  
  public void setValuePercent(float fvalue) {
    value = fvalue;
    angle = value*PConstants.TWO_PI;
    float r = dimension.size/2-boxsize/4;
    float x = r*PApplet.cos(angle);
    float y = r*PApplet.sin(angle);
    box.position.set(x,y);
  }
  
  public void setValue(float fvalue) {
    float fv = fvalue / length;
    setValuePercent(fv);
  }
  
}
