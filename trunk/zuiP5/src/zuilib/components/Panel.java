package zuilib.components;

import processing.core.PConstants;
import processing.core.PGraphics;
import zuilib.core.component;
import zuilib.properties.RectDimension;
import zuilib.utils.vector;
import zuilib.windows.BackgroundWindow;

public class Panel extends component {
  
  public boolean enable;
  public BackgroundWindow components;
  public PGraphics panelGraphic;
  public RectDimension dimension;
  public RectDimension displayed;
  public String renderer;

  public Panel(String sname, float fx, float fy, float fwidth, float fheight, String srenderer) {
    super(sname, fx, fy);
    Panel_init(fwidth,fheight,srenderer);
  }
  
  public Panel(String sname, vector pos, float fwidth, float fheight, String srenderer) {
    super(sname, pos);
    Panel_init(fwidth,fheight,srenderer);
  }
  
  public Panel(String sname, float fx, float fy, float fwidth, float fheight) {
    super(sname, fx, fy);
    Panel_init(fwidth,fheight);
  }
  
  public Panel(String sname, vector pos, float fwidth, float fheight) {
    super(sname, pos);
    Panel_init(fwidth,fheight);
  }
  
  private void Panel_init(float fwidth, float fheight) {
    Panel_init(fwidth,fheight,PConstants.JAVA2D);
  }
  
  private void Panel_init(float fwidth, float fheight, String srenderer) {
    mode = PConstants.CORNER;
    renderer = srenderer;
    enable = true;
    dimension = new RectDimension(fwidth,fheight);
    displayed = new RectDimension(fwidth,fheight);
    components = new BackgroundWindow(Name.get()+"_Components", 0, 0);
    components.dimension.set(fwidth, fheight);
  }
  
  public void setup() {
    super.setup();
    dimension.setup(this);
    displayed.setup(this);
    panelGraphic = getPApplet().createGraphics((int) dimension.width, (int) dimension.height, renderer);
    components.setParentObject(this);
    components.setGraphic(panelGraphic);
    components.setup();
    
  }
  
  public int addComponent(component newc) {
    int i = components.addComponent(newc);
    //newc.setGraphic(panelGraphic);
    return i;
  }
  
  public void predraw() {
    super.predraw();
    if(enable) {
      panelGraphic.beginDraw();
      panelGraphic.background(0,0);
      components.display();
      panelGraphic.endDraw();
    }
  }
  
  public void draw() {
    super.draw();
    graphic.imageMode(mode);
    graphic.image(panelGraphic,0,0,dimension.width,dimension.height);
  }
  
  public component getComponent(int i) {
    return (component) components.getComponent(i);
  }
 
  public component getComponent(String sname) {
    return (component) components.getComponent(sname);
  }
  
  public component get(int i) {
    return getComponent(i);
  }
  
  public component get(String sname) {
    return getComponent(sname);
  }
  
  public void keyPressed(char key) {
    super.keyPressed(key);
    if(enable) {
      components.keyPressed(key);
    }
  }
  
  public void keyReleased(char key) {
    super.keyReleased(key);
    if(enable) {
      components.keyReleased(key);
    }
  }
  
  public void keyTyped(char key) {
    super.keyTyped(key);
    if(enable) {
      components.keyTyped(key);
    }
  }
  
  public void mouseClicked() {
    super.mouseClicked();
    if(over() && enable) {
      components.mouseClicked();
    }
  }
  
  public void mouseDragged() {
    super.mouseDragged();
    if(over() && enable) {
      components.mouseDragged();
    }
  }
  
  public void mouseMoved() {
    super.mouseMoved();
    if(over() && enable) {
      components.mouseMoved();
    }
  }
  
  public void mousePressed() {
    super.mousePressed();
    if(over() && enable) {
      components.mousePressed();
    }
  }
  
  public void mouseReleased() {
    super.mouseReleased();
    pressed = false;
    if(over() && enable) {
      components.mouseReleased();
    }
  }
  
  public boolean setComponent(int i,component newc) {
    boolean bool = components.setComponent(i, newc);
    if(bool) newc.setGraphic(panelGraphic);
    return bool;
  }
  
  public boolean setDown(int n) {
    return components.setDown(n);
  }
  
  public boolean setDown(String sname) {
    return components.setDown(sname);
  }
  
  public void setEnable(boolean en) {
    enable = en;
  }
  
  public boolean setOnBottom(int i) {
    return components.setOnBottom(i);
  }
  
  public boolean setOnBottom(String sname) {
    return components.setOnBottom(sname);
  }
  
  public boolean setOnTop(int i) {
    return components.setOnTop(i);
  }
  
  public boolean setOnTop(String sname) {
    return components.setOnTop(sname);
  }
  
  public boolean setUp(int n) {
    return components.setUp(n);
  }
  
  public boolean setUp(String sname) {
    return components.setUp(sname);
  }
  
  public boolean setZIndex(int n, int newz) {
    return components.setZIndex(n, newz);
  }
  
  public boolean setZIndex(String sname, int newz) {
    return components.setZIndex(sname, newz);
  } 
  
  public boolean over() {
    dimension.over = overRect(dimension.width, dimension.height, mode);
    return dimension.over;
  }
  
  public void update() {
    super.update();
    if(panelGraphic.width != (int) displayed.width || panelGraphic.height != (int) displayed.height ) {
      panelGraphic = getPApplet().createGraphics((int) displayed.width, (int) displayed.height, renderer);
    }
    //panelGraphic;
    components.update();
  }
  
}
