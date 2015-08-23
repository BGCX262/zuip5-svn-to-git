package zuilib.components;

import zuilib.properties.HoverController;

public class RectHoverButton extends RectButton {
  
  public HoverController hover;

  public RectHoverButton(String sname, float fx, float fy) {
    super(sname, fx, fy);
    hover = new HoverController();
  }
  
  public void setup() {
    super.setup();
    hover.setup(this);
  }

}
