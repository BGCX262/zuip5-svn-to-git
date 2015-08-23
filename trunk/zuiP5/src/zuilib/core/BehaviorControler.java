package zuilib.core;

import processing.core.PApplet;

public class BehaviorControler extends controlerObject implements zuiControl {

  public boolean enable;
  public zuiBehavior[] behaviors;
  public int length;
  
  public BehaviorControler(zuiObject curParent) {
    super(curParent);
    behaviorControler_init(true);
  }
  
  public BehaviorControler(zuiObject curParent, boolean benable) {
    super(curParent);
    behaviorControler_init(benable);
  }
  
  public BehaviorControler() {
    super();
    behaviorControler_init(true);
  }
  
  public BehaviorControler(boolean benable) {
    super();
    behaviorControler_init(benable);
  }
  
  private void behaviorControler_init(boolean benable) {
    enable = benable;
    behaviors = new zuiBehavior[0];
    length = 0;
  }
  
  public int addBehavior(zuiBehavior newb) {
    if(getParent().getZUI().DEBUG >= 5) PApplet.println("* Add Behavior "+newb.Name+" ("+newb.getClass().getName()+
         ") to "+getParent().Name.get()+" ("+getParent().getClass().getName()+")");
    behaviors = (zuiBehavior[]) PApplet.append(behaviors, newb);
    newb.install(getParent());
    length += 1;
    return length-1;
  }
  
  public zuiBehavior getBehavior(int i) {
    if( i < length ) {
        return behaviors[i];
      } else {
        PApplet.println("[WARNING]: getBehavior:  Requested behavior no. "+i+" returned null.");
        return null;
    }
  }
  
  public zuiBehavior getBehavior(String sname) {
    for( int i = 0 ; i < length ; i += 1 ) {
      if(behaviors[i].Name.equals(sname)) {
        return behaviors[i];
      }
    }
    PApplet.println("[WARNING]: getBehavior:  Requested behavior "+sname+" returned null.");
    return null;
  }
  
  public zuiBehavior get(int i) {
    return getBehavior(i);
  }
  
  public zuiBehavior get(String sname) {
    return getBehavior(sname);
  }
  
  public boolean setBehavior(int i, zuiBehavior newb) {
    if( i < length ) {
      behaviors[i] = newb;
      newb.install(getParent());
      return true;
    } else {
      PApplet.println("[WARNING]: setBehavior:  Setting behavior "+newb.Name+" on position "+i+" failed.");
      return false;
    }
  }
  
  public boolean set(int i, zuiBehavior newb) {
    return setBehavior(i,newb);
  }

  public void display() {
    if(enable) {
    for( int i = 0 ; i < length ; i += 1 ) {
      behaviors[i].display();
    }
    }
  }

  public void draw() {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].draw();
      }
    }
  }

  public void keyPressed(char key) {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].keyPressed(key);
      }
    }
  }

  public void keyReleased(char key) {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].keyReleased(key);
      }
    }
  }

  public void keyTyped(char key) {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].keyTyped(key);
      }
    }
  }

  public void mouseClicked() {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].mouseClicked();
      }
    }
  }

  public void mouseDragged() {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].mouseDragged();
      }
    }
  }

  public void mouseMoved() {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].mouseMoved();
      }
    }
  }

  public void mousePressed() {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].mousePressed();
      }
    }
  }

  public void mouseReleased() {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].mouseReleased();
      }
    }
  }

  public void postdraw() {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].postdraw();
      }
    }
  }

  public void predraw() {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].predraw();
      }
    }
  }

  public void update() {
    if (enable) {
      for (int i = 0; i < length; i += 1) {
        behaviors[i].update();
      }
    }
  }

}
