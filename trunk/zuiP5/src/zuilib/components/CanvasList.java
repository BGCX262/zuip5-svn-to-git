package zuilib.components;

import processing.core.PConstants;
import zuilib.properties.ColorController;
import zuilib.properties.RectAreaDimension;
import zuilib.properties.RectDimension;


public class CanvasList extends interactivecomponent {
  
  public ColorController Color;
  public RectDimension dimension;
  public RectAreaDimension field;
  public RectAreaDimension list;
  public Object[][] table;
  
  public CanvasList(String sname, float fx, float fy, float fwidth, float fheight) {
    super(sname,fx,fy);
    table = new Object[0][0];
    mode = PConstants.CORNER;
    dimension = new RectDimension(fwidth,fheight);
    field = new RectAreaDimension(0,0);
    list = new RectAreaDimension(0,0);
    Color = new ColorController();
  }
  
  public void setup() {
    super.setup();
    dimension.setup(this);
    field.setup(this);
    list.setup(this);
    Color.setup(this);
    
    addFunction("mouseClicked");
    addFunction("mousePressed");
    addFunction("mouseDragged");
    addFunction("mouseMoved");
    addFunction("mouseReleased");
    addFunction("onDraw");
  }

  public void setList(int neww, int newh) {
    list.setDimension(neww,newh);
    table = new Object[neww][newh];
    field.width = dimension.width/neww;
    field.height = dimension.height/newh;
  }
  
  public boolean over() {
    dimension.over = overRect(dimension.width, dimension.height, mode);
    return dimension.over;
  }
  
  public boolean over_field(int x, int y) {
    field.position.set( field.width*x,field.height*y );
    return overRect(field.position, field.width, field.height, mode);
  }
  
  private void mouseEvent(String func) {
    if(isSetFunction(func)) {
      for( int y = 0 ; y < list.height ; y += 1) {
        for( int x = 0 ; x < list.width ; x += 1) {
          if(over_field(x,y)) {
            list.position.set(x, y);
            getFunction(func).invoke();
            break;
          }
        }
      }
    }
  }
  
  public void mousePressed() {
    if(!getLock()) {
      setLock(over());
      pressed = getLock();
    }
    super.mousePressed();
    if(pressed) mouseEvent("mousePressed");
  }
  
  public void mouseClicked() {
    super.mouseClicked();
    if(pressed) mouseEvent("mouseClicked");
    pressed = false;
  }
  
  public void mouseDragged() {
    super.mouseDragged();
    if(pressed) mouseEvent("mouseDragged");
  }
  
  public void mouseMoved() {
    super.mouseMoved();
    mouseEvent("mouseMoved");
  }
  
  public void mouseReleased() {
    super.mouseReleased();
    if(pressed) mouseEvent("mouseReleased");
  }
  
  public void draw() {
    super.draw();
    Color.doBackground();
    graphic.rect(0,0,dimension.width,dimension.height);
    Color.doForeground();
    for( int y = 0 ; y < list.height ; y += 1) {
      for( int x = 0 ; x < list.width ; x += 1) {
        graphic.pushMatrix();
        list.position.set(x, y);
        graphic.translate(field.width*x,field.height*y);
        useFunction("onDraw");
        graphic.popMatrix();
      }
    }
  }
  
}
