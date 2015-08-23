package zuilib.windows;

import processing.core.PApplet;
import processing.core.PConstants;
import zuilib.utils.vector;


public class EyeWindow extends CircleWindow {
  
  private float angle;
  
  public EyeWindow(String sname, float ix, float iy) {
    super(sname,ix,iy);
    angle = 0;
  }
  
  public void draw() {
    graphic.rotate(-rotation.getAbsolute());
    Color.doBackground();
    graphic.ellipseMode(PConstants.CENTER);
    graphic.ellipse(0, 0, dimension.size, dimension.size);
    
    graphic.rotate(angle);
    Color.doForeground();
    graphic.ellipse(dimension.size/2-area.size/2, 0, area.size, area.size);
    graphic.rotate(-angle);
  }
  
  public void update() {
    super.update();
    angle = vector.VecAng(getMouse());
    float r = dimension.size/2-area.size/2;
    float x = r*PApplet.cos(angle);
    float y = r*PApplet.sin(angle);
    area.position.set(x,y);
  }
}
