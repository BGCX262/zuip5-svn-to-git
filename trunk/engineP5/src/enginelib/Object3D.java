package enginelib;

import noc.Vector3D;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Object3D implements IObject3D {
  
  public String name;
  public IObject3D parent;
  public PGraphics graphic;
  public Vector3D position;
  public Vector3D rotation;
  public Object3D[] objects;
  public int length;
  public int fill;
  public int stroke;
  
  public Object3D(String sname) {
    objects = new Object3D[0];
    name = sname;
    length = 0;
    position = new Vector3D();
    rotation = new Vector3D();
    fill =   1694498815; //color(255,100)
    stroke = 1677721600; //color(  0,100)
    init();
  }
  
  public void setParentObject(IObject3D curParent) {
    parent = curParent;
  }

  public objectControler3D getControler() {
    return parent.getControler();
  }

  public PGraphics getGraphic() {
    return graphic;
  }

  public PApplet getPApplet() {
    return parent.getPApplet();
  }

  public IObject3D getParent() {
    return parent;
  }
  
  public void display() {
    predraw();
    draw();
    postdraw();
  }
  
  public void predraw() {
    graphic.pushMatrix();
    graphic.translate(position.x,position.y,position.z);
    graphic.rotateX(rotation.x);
    graphic.rotateY(rotation.y);
    graphic.rotateZ(rotation.z);
    graphic.fill(fill);
    graphic.stroke(stroke);
  }
  
  public void postdraw() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].display();
    }
    graphic.popMatrix();
  }
  
  public int addObject(Object3D newobj) {
    objects = (Object3D[]) PApplet.append(objects, newobj);
    newobj.setParentObject(this);
    newobj.setup();
    length += 1;
    return length - 1;
  }

  public Object3D get(int pos) {
    return getObject(pos);
  }

  public Object3D get(String sname) {
    return getObject(sname);
  }

  public Object3D getObject(int pos) {
    if( pos < length ) {
        return objects[pos];
      } else {
        PApplet.println("[WARNING]: getObject:  Requested object no. "+pos+" returned null.");
        return null;
    }
  }

  public Object3D getObject(String sname) {
    for( int i = 0 ; i < length ; i += 1 ) {
      if(objects[i].name.equals(sname)) {
        return objects[i];
      }
    }
    PApplet.println("[WARNING]: getObject:  Requested object "+sname+" returned null.");
    return null;
  }

  public boolean set(int pos, Object3D newobj) {
    return setObject(pos,newobj);
  }

  public boolean setObject(int pos, Object3D newobj) {
    if( pos < length ) {
      objects[pos] = newobj;
      newobj.setParentObject(this);
      newobj.setup();
      return true;
    } else {
      PApplet.println("[WARNING]: setObject:  Setting object "+newobj.name+" on position "+pos+" failed.");
      return false;
    }
  }
  
  public void size(int iwidth, int iheight) {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].size(iwidth, iheight);
    }
  }
  
  public void update() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].update();
    }
  }
  
  public void dispose() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].dispose();
    }
  }
  
  public void mousePressed() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].mousePressed();
    }
  }
  
  public void mouseClicked() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].mouseClicked();
    }
  }
  
  public void mouseDragged() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].mouseDragged();
    }
  }
  
  public void mouseMoved() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].mouseMoved();
    }
  }
  
  public void mouseReleased() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].mouseReleased();
    }
  }
  
  public void keyPressed() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].keyPressed();
    }
  }
  
  public void keyTyped() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].keyTyped();
    }
  }
  
  public void keyReleased() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].keyReleased();
    }
  }
  
  public void post() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].post();
    }
  }
  
  public void pre() {
    for( int i = 0 ; i < length ; i += 1 ) {
      objects[i].pre();
    }
  }
  
  public void setup() {
    graphic = parent.getGraphic();
  }
  
  public void init() {return;};
  public void draw() {return;}

}
