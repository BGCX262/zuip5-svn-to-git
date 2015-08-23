package zuilib.components;

import zuilib.core.component;
import zuilib.properties.ColorController;
import zuilib.utils.ColorBundle;


public class shape extends component {
  
  public ColorController Color;
  
  public shape(String sname, float fx, float fy, int ccolor, int cborder) {
    super(sname,fx,fy);
    Color = new ColorController(new ColorBundle(ccolor,ccolor,cborder));
  }
  
  public shape(String sname, float fx, float fy) {
    super(sname,fx,fy);
    Color = new ColorController();
  }
  
  public void setup() {
    super.setup();
    Color.setup(this);
  }
  
}
