package zuilib.components;

import processing.core.PApplet;
import processing.core.PConstants;
import zuilib.properties.RectDimension;
import zuilib.utils.vector;

public class recthandle extends handle {
  
  public boolean horizontally;
  public RectDimension dimension;
  
  public recthandle(String sname, float fx, float fy, float fwidth, float fheight, float flength) {
    super(sname,fx,fy,flength);
    horizontally = (fwidth >= fheight);
    dimension = new RectDimension(fwidth, fheight);
  }
  
  public void setup() {
    super.setup();
    dimension.setup(this);
  }
  
  public void draw() {
    super.draw();
    Color.doBackground();
    if(horizontally) {
        float h = dimension.height/2;
        graphic.line(0,h,dimension.width,h);
      } else {
        float w = dimension.width/2;
        graphic.line(w,0,w,dimension.height);
    }
  }
  
  public boolean over() {
    dimension.over = overRect(dimension.width,dimension.height,PConstants.CORNER);
    return dimension.over;
  } 
  
  public void boxMove() {
    vector start = new vector(0,0), end = new vector(0,0);
    float min = 0;
    float len = 0;
    if(horizontally) {
        len = dimension.width;
        min = dimension.height;
        end = new vector(1,0);
      } else {
        len = dimension.height;
        min = dimension.width;
        end = new vector(0,1);
    }
    start.Add(vector.VecMul(new vector(end.y,end.x), min/2));
    start.Add(vector.VecMul(end, -len));
    vector vdist = vector.VecSub(getMouse(), start);
    vdist.x *= end.x;
    vdist.y *= end.y;
    float max = len*2;
    float dist = vdist.getValue();// - box.dimension.getSize();
    dist = PApplet.constrain(dist, len, max)-len;
    value = dist/(max/2);
    box.position.set( vector.VecMul(vector.VecNorm(end),value*(len-PApplet.min(dimension.width,dimension.height))) );
  }
  
  public void setValuePercent(float fvalue) {
    value = fvalue;
    if(horizontally) {
        box.position.set( ((dimension.width-PApplet.min(dimension.width,dimension.height))*value) , 0 );
      } else {
        box.position.set( 0, ((dimension.height-PApplet.min(dimension.width,dimension.height))*value));
    }
  }
  
  public void setValue(float fvalue) {
    float fv = fvalue / length;
    setValuePercent(fv);
  }
  
}
