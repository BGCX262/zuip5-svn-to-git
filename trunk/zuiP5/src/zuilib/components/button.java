package zuilib.components;

import zuilib.properties.ColorController;
import zuilib.utils.vector;

public class button extends interactivecomponent {
  
  public ColorController Color;
  
  public button(String sname, float fx, float fy) {
    super(sname,fx,fy);
    Color = new ColorController();
  }
  
  public button(String sname, vector start_pos) {
    super(sname,start_pos);
    Color = new ColorController();
  }
  
  public void setup() {
    super.setup();
    Color.setup(this);
    addFunction("mouseClicked");
    addFunction("keyPressed");
  }

  public void update() {
    super.update();
    Color.setHighlighting(over());
  }
  
  public void keyPressed(char key) {
    super.keyPressed(key);
    useFunction("keyPressed");
  }
  
  public void mouseClicked() {
    super.mouseClicked();
    if(pressed) useFunction("mouseClicked");
    pressed = false;
  }

  public void mousePressed() {
    if(!getLock()) {
      setLock(over());
      pressed = getLock();
    }
    super.mousePressed();
  }
  
  public void mouseReleased() {
    super.mouseReleased();
    //pressed = false;
  }
}
