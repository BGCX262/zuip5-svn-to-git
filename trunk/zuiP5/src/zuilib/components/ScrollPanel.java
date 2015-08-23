package zuilib.components;

import zuilib.core.zuiBehavior;
import zuilib.utils.vector;

public class ScrollPanel extends Panel {
  
  public scrollbar hscrollbar;
  public scrollbar vscrollbar;
  
  public ScrollPanel(String sname, float fx, float fy, float fwidth, float fheight, String srenderer) {
    super(sname, fx, fy,fwidth,fheight,srenderer);
    ScrollPanel_init(10);
  }
  
  public ScrollPanel(String sname, vector pos, float fwidth, float fheight, String srenderer) {
    super(sname, pos,fwidth,fheight,srenderer);
    ScrollPanel_init(10);
  }
  
  public ScrollPanel(String sname, float fx, float fy, float fwidth, float fheight) {
    super(sname, fx, fy,fwidth,fheight);
    ScrollPanel_init(10);
  }
  
  public ScrollPanel(String sname, vector pos, float fwidth, float fheight) {
    super(sname, pos,fwidth,fheight);
    ScrollPanel_init(10);
  }
  
  public ScrollPanel(String sname, float fx, float fy, float fwidth, float fheight, float scrollbarsize, String srenderer) {
    super(sname, fx, fy,fwidth,fheight,srenderer);
    ScrollPanel_init(scrollbarsize);
  }
  
  public ScrollPanel(String sname, vector pos, float fwidth, float fheight, float scrollbarsize, String srenderer) {
    super(sname, pos,fwidth,fheight,srenderer);
    ScrollPanel_init(scrollbarsize);
  }
  
  public ScrollPanel(String sname, float fx, float fy, float fwidth, float fheight, float scrollbarsize) {
    super(sname, fx, fy,fwidth,fheight);
    ScrollPanel_init(scrollbarsize);
  }
  
  public ScrollPanel(String sname, vector pos, float fwidth, float fheight, float scrollbarsize) {
    super(sname, pos,fwidth,fheight);
    ScrollPanel_init(scrollbarsize);
  }

  private void ScrollPanel_init(float scrollbarsize) {
    vscrollbar = new RectScrollbar(Name.get()+"_VSrollbar",dimension.width,0,scrollbarsize,dimension.height,0);
    hscrollbar = new RectScrollbar(Name.get()+"_HSrollbar",0,dimension.height,dimension.width,scrollbarsize,0);
  }
  
  public void setup() {
    super.setup();
    hscrollbar.setParentObject(this);
    vscrollbar.setParentObject(this);
    hscrollbar.setup();
    vscrollbar.setup();
    hscrollbar.behavior.addBehavior( new ScrollbarSrollPanelBehavior("ScrollHorizontally",true) );
    vscrollbar.behavior.addBehavior( new ScrollbarSrollPanelBehavior("ScrollVertically",false) );
    properties.setPreMarker("postdraw", 1);
  }
  
  public void postdraw() {
    properties.pre_postdraw(1);
    if(hscrollbar.isVisible()) hscrollbar.display();
    if(vscrollbar.isVisible()) vscrollbar.display();
    super.postdraw();
  }
  
  public void update() {
    super.update();
    if(vscrollbar.dimension.height != dimension.height) {
      vscrollbar.dimension.height = dimension.height;
      hscrollbar.position.setY(dimension.height);
    }
    if(hscrollbar.dimension.width != dimension.width) {
      hscrollbar.dimension.width = dimension.width;
      vscrollbar.position.setX(dimension.width);
    }
    if(vscrollbar.length != displayed.height-components.dimension.height) {
      vscrollbar.length = displayed.height-components.dimension.height;
    }
    if(hscrollbar.length != displayed.width-components.dimension.width) {
      hscrollbar.length = displayed.width-components.dimension.width;
    }
    hscrollbar.update();
    vscrollbar.update();
  }
  
  public void mousePressed() {
    super.mousePressed();
    hscrollbar.mousePressed();
    vscrollbar.mousePressed();
  }
  
  public void mouseClicked() {
    super.mouseClicked();
    hscrollbar.mouseClicked();
    vscrollbar.mouseClicked();
  }
  
  public void mouseDragged() {
    super.mouseDragged();
    hscrollbar.mouseDragged();
    vscrollbar.mouseDragged();
  }
  
  public void mouseMoved() {
    super.mouseMoved();
    hscrollbar.mouseMoved();
    vscrollbar.mouseMoved();
  }
  
  public void mouseReleased() {
    super.mouseReleased();
    hscrollbar.mouseReleased();
    vscrollbar.mouseReleased();
  }
  
  protected class ScrollbarSrollPanelBehavior extends zuiBehavior { // to keep the onChange function from scrollbar free
    private float prev_value = 0;
    public boolean horizontally;
    public ScrollbarSrollPanelBehavior(String sname, boolean bhorz) {
      super(sname);
      horizontally = bhorz;
    }
    
    public void update() {
      float value = ((scrollbar) object).getValue();
      if(value != prev_value) {
        ScrollPanel panel = ((ScrollPanel) object.getParentObject());
        if(horizontally) {
          panel.components.position.setX(value);
        } else {
          panel.components.position.setY(value);
        }
      }
      prev_value = value;
    }
  }

}




