package zuilib.manager;
import java.awt.event.KeyEvent;

import processing.core.PConstants;

import zuilib.core.manager;
import zuilib.utils.util;


public class KeyboardManager extends manager {

  public char keychar;

  public KeyboardManager(String sname, boolean benable) {
    super(sname,benable);
    Type = "keyboard";
  }

  @SuppressWarnings("static-access")
  private void check() {
    if(parent.getParent().key == parent.getParent().CODED) {
      keychar = parent.getParent().parseChar(parent.getParent().keyCode);
      if(keychar == parent.getParent().RETURN) {
        keychar = parent.getParent().parseChar(parent.getParent().ENTER);
      }
    } 
    else {
      keychar = parent.getParent().key;
    }
  }

  public void keyEvent(KeyEvent event) {
    if(enable) {
      switch (event.getID()) {
      case KeyEvent.KEY_TYPED:
        this.keyTyped();
        break;
      case KeyEvent.KEY_RELEASED:
        this.keyReleased();
        break;
      case KeyEvent.KEY_PRESSED:
        this.keyPressed();
        break;
      }
    }
  }

  public void keyPressed() {
    if(enable) {
      check();
      useFunction("keyPressed", new Object[] { keychar } );
    }
  }

  public void keyReleased() {
    if(enable) {
      check();
      useFunction("keyReleased", new Object[] { keychar } );
    }
  }

  public void keyTyped() {
    if(enable) {
      check();
      if(util.inRange(keychar,32,126)) {
        useFunction("keyTyped", new Object[] { keychar } );
      }
    }
  }

  public void setup() {
    parent.getParent().registerKeyEvent(this);
    publishFunction("keyTyped", new Class[] { char.class } );
    publishFunction("keyReleased", new Class[] { char.class } );
    publishFunction("keyPressed", new Class[] { char.class } );
  }
  
  public boolean isCTRL() {
    return parent.getParent().keyCode == PConstants.CONTROL;
  }
  
  public boolean isSHIFT() {
    return parent.getParent().keyCode == PConstants.SHIFT;
  }
  
  public boolean isALT() {
    return parent.getParent().keyCode == PConstants.ALT;
  }

}
