package enginelib;

import processing.core.PApplet;
import processing.core.PGraphics;

public interface IObject3D {

  public PApplet getPApplet();
  public PGraphics getGraphic();
  public objectControler3D getControler();
  public IObject3D getParent();
  public void setParentObject(IObject3D curParent);
  
  public int addObject(Object3D newobj);
  public boolean setObject(int pos,Object3D newobj);
  public boolean set(int pos,Object3D newobj);
  public Object3D getObject(int pos);
  public Object3D getObject(String sname);
  public Object3D get(int pos);
  public Object3D get(String sname);
  
  public void init();
  public void setup();
  public void size(int iwidth, int iheight);
  public void pre();
  public void update();
  public void display();
  public void predraw();
  public void draw();
  public void postdraw();
  public void post();
  public void dispose();
  
  public void mousePressed();
  public void mouseClicked();
  public void mouseDragged();
  public void mouseReleased();
  public void mouseMoved();
  public void keyTyped();
  public void keyPressed();
  public void keyReleased();

}
