package zuilib.manager;

import java.awt.event.MouseEvent;

public class extendedMouseManager extends simpleMouseManager {

  public extendedMouseManager(String sname, boolean benable) {
    super(sname,benable);
  }
  
  public void setup() {
    super.setup();
    publishFunction("mouseDoubleClicked");
    publishFunction("mouseTripleClicked");
    publishFunction("mouseRightClicked");
    publishFunction("mouseMiddleClicked");
    publishFunction("mouseLeftClicked");
  }

  public void mouseEvent(MouseEvent event) {
    super.mouseEvent(event);
    if(enable) {
      switch (event.getButton()) {
      case MouseEvent.BUTTON1:
        this.mouseLeftClicked();
        break;
      case MouseEvent.BUTTON2:
        this.mouseMiddleClicked();
        break;
      case MouseEvent.BUTTON3:
        this.mouseRightClicked();
        break;
      }
      if(event.getClickCount() == 2) {
        this.mouseDoubleClicked();
      }
      if(event.getClickCount() == 3) {
        this.mouseTripleClicked();
      }
    }
  }

  public void mouseDoubleClicked() {
    if(enable) useFunction("mouseDoubleClicked", new Object[] {} );
  }

  public void mouseTripleClicked() {
    if(enable) useFunction("mouseTripleClicked", new Object[] {} );
  }

  public void mouseRightClicked() {
    if(enable) useFunction("mouseRightClicked", new Object[] {} );
  }

  public void mouseMiddleClicked() {
    if(enable) useFunction("mouseMiddleClicked", new Object[] {} );
  }

  public void mouseLeftClicked() {
    if(enable) useFunction("mouseLeftClicked", new Object[] {} );
  }
  


}
