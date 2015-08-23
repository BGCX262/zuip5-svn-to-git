package zuilib.manager;

import zuilib.core.component;
import zuilib.core.window;
import zuilib.properties.zuiObjectsController;
import zuilib.utils.vector;


public class simpleWindowManager extends windowmanager {
  
  public zuiObjectsController windows;

  public simpleWindowManager(String sname, boolean benable) {
    super(sname,benable);
  }

  public int addWindow(window neww) {
    return windows.addObject(neww,"Window");
  }

  public void display() {
    if(enable) {
      graphic.pushMatrix();
      position.doTask();
      scale.doTask();
      rotation.doTask();
      window win = null;
      for( int i = 0 ; i < windows.length ; i += 1) {
        win = (window) windows.getSorted(i);
        /*
        if(i == windows.length-1) {
         graphic.filter(BLUR,0.6);
        }
        */
        if(win.isVisible()) {win.display();}
      }
      graphic.popMatrix();
    }
  }
  
  public void draw() {
    if(enable) {
      update();
      display();
    }
  }

  public window get(int w) {
    return getWindow(w);
  }

  public component get(int w, int c) {
    window win = getWindow(w);
    if(win != null) {
      return win.getComponent(c);
    }
    return null;
  }

  public component get(int w, String sCname) {
    window win = getWindow(w);
    if(win != null) {
      return win.getComponent(sCname);
    }
    return null;
  }

  public window get(String sWname) {
    return getWindow(sWname);
  }

  public component get(String sWname, int c) {
    window win = getWindow(sWname);
    if(win != null) {
      return win.getComponent(c);
    }
    return null;
  }

  public component get(String sWname, String sCname) {
    window win = getWindow(sWname);
    if(win != null) {
      return win.getComponent(sCname);
    }
    return null;
  }  

  public window getWindow(int i) {
    return (window) windows.getObject(i);
  }

  public window getWindow(String sname) {
    return (window) windows.getObject(sname);
  }

  public void keyPressed(char key) {
    if(enable) {
      window win = null;
      for( int i = windows.length-1 ; i >= 0 ; i -= 1) {
        win = (window) windows.getSorted(i);
        if(win.isVisible()) {win.keyPressed(key);}
      }
    }
  }

  public void keyReleased(char key) {
    if(enable) {
      window win = null;
      for( int i = windows.length-1 ; i >= 0 ; i -= 1) {
        win = (window) windows.getSorted(i);
        if(win.isVisible()) {win.keyReleased(key);}
      }
    }
  }

  public void keyTyped(char key) {
    if(enable) {
      window win = null;
      for( int i = windows.length-1 ; i >= 0 ; i -= 1) {
        win = (window) windows.getSorted(i);
        if(win.isVisible()) {win.keyTyped(key);}
      }
    }
  }

  public void mouseClicked() {
    if(enable) {
      window win = null;
      for( int i = windows.length-1 ; i >= 0 ; i -= 1) {
        win = (window) windows.getSorted(i);
        if(win.isVisible()) {win.mouseClicked();}
      }
    }
  }

  public void mouseDragged() {
    if(enable) {
      window win = null;
      for( int i = windows.length-1 ; i >= 0 ; i -= 1) {
        win = (window) windows.getSorted(i);
        if(win.isVisible()) {win.mouseDragged();}
      }
    }
  }

  public void mouseMoved() {
    if(enable) {
      window win = null;
      for( int i = windows.length-1 ; i >= 0 ; i -= 1) {
        win = (window) windows.getSorted(i);
        if(win.isVisible()) {win.mouseMoved();}
      }
    }
  }

  public void mousePressed() {
    if(enable) {
      window win = null;
      for(int i = windows.length-1 ; i >= 0 ; i -= 1 ) {
        win = (window) windows.getSorted(i);
        if(win.over() && win.isVisible()) {
          setUp(windows.getSortedIndex(i));
          break;
        }
      }
      for(int i = windows.length-1 ; i >= 0 ; i -= 1 ) {
        win = (window) windows.getSorted(i);
        if(win.isVisible()) {win.mousePressed();}
      }
    }
  }

  public void mouseReleased() {
    locked = false;
    if(enable) {
      window win = null;
      for( int i = windows.length-1 ; i >= 0 ; i -= 1) {
        win = (window) windows.getSorted(i);
        if(win.isVisible()) {win.mouseReleased();}
      }
    }
  }

  public void setRotation(float newrot) {
    rotation.set(newrot);
  }
  
  public void setPosition(vector newpos) {
    position.set(newpos);
  }
  
  public void setScale(float newscale) {
    scale.set(newscale);
  }

  public boolean setDown(int n) {
    return windows.setDown(n);
  }

  public boolean setDown(String sname) {
    return windows.setDown(sname);
  }

  public boolean setOnBottom(int i) {
    return windows.setOnBottom(i);
  }

  public boolean setOnBottom(String sname) {
    return windows.setOnBottom(sname);
  }

  public boolean setOnTop(int i) {
    return windows.setOnTop(i);
  }

  public boolean setOnTop(String sname) {
    return windows.setOnTop(sname);
  }

  public void setup() {
    super.setup();
    windows = new zuiObjectsController();
    windows.setRegister(false);
    windows.setup(this);
  }

  public boolean setUp(int n) {
    return windows.setUp(n);
  }

  public boolean setUp(String sname) {
    return windows.setUp(sname);
  }

  public boolean setWindow(int i,window neww) {
    return windows.setObject(i, neww);
  }

  public boolean setZIndex(int n, int newz) {
    return windows.setZIndex(n, newz);
  }

  public boolean setZIndex(String sname, int newz) {
    return windows.setZIndex(sname, newz);
  }

  public void update() {
    if(enable) {
      window win = null;
      for( int i = windows.length-1 ; i >= 0 ; i -= 1) {
        win = (window) windows.getSorted(i);
        win.update();
      }
    }
  }

}
