package zuilib.components;

import processing.core.PConstants;
import zuilib.properties.RectDimension;


public class TextButton extends button {
  
  public Label text;
  public RectDimension dimension;

  public TextButton(String sname, float fx, float fy, String stext, float fsize) {
    super(sname,fx,fy);
    mode = PConstants.CORNER;
    text = new Label(Name.get()+"_Label",0,0,stext,fsize);
    dimension = text.dimension;
  }
  
  public void setup() {
    super.setup();
    text.setParentObject(this);
    text.setup();
    text.scale.setEnable(false);
    text.rotation.setEnable(false);
    text.position.set(0,0);
    text.Color = Color;
  }

  public boolean over() {
    text.over();
    return dimension.over;
  }
  
  public void draw() {
    super.draw();
    graphic.rectMode(mode);
    Color.doBackground();
    if((Color.isHigh() && Color.highlight.isBorder()) || (!Color.isHigh() && Color.normal.isBorder())) {
      graphic.rect(0,0,dimension.width,dimension.height);
    }
    Color.doForeground();
    if(text.visibility.is()) text.draw();
  }
  
  public void update() {
    super.update();
    text.update();
  }
}
