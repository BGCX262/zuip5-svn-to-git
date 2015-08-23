package zuilib.components;

import processing.core.PConstants;
import zuilib.core.component;
import zuilib.manager.fontmanager;
import zuilib.properties.ColorController;
import zuilib.properties.RectDimension;
import zuilib.utils.zuiFont;


public class Label extends component {
  
  public String text;
  public zuiFont Font;
  public String fontname;
  public RectDimension dimension;
  public ColorController Color;
  
  public Label(String sname, float fx, float fy, String stext) {
    super(sname,fx,fy);
    Label_init(stext,"",0);
  }
  
  public Label(String sname, float fx, float fy, String stext, String sfont) {
    super(sname,fx,fy);
    Label_init(stext,sfont,0);
  }
  
  public Label(String sname, float fx, float fy, String stext, float fsize) {
    super(sname,fx,fy);
    Label_init(stext,"",fsize);
  }
  
  public Label(String sname, float fx, float fy, String stext, String sfont, float fsize) {
    super(sname,fx,fy);
    Label_init(stext,sfont,fsize);
  }
  
  private void Label_init(String stext, String sfont, float fsize) {
    mode = PConstants.CORNER;
    fontname = sfont;
    text = stext;
    dimension = new RectDimension(0,fsize);
    Color = new ColorController();
  }
  
  public void setup() {
    super.setup();
    Color.setup(this);
    Color.setAsForeground();
    if(fontname.equals("")) {
      fontname = getZUI().FONT_FILE;
    }
    fontmanager fontman = (fontmanager) getManagerByType("font");
    Font = fontman.get(fontname);
    if( dimension.height == 0 ) {
        dimension.height  = getZUI().FONT_SIZE  +4;
      } else {
        dimension.height +=                     +4;
    }
    setText(text);
  }
  
  public void setText(String stext) {
    text = stext;
    dimension.width = Font.textWidth(text, dimension.height-4) +4;
  }
  
  public boolean over() {
    dimension.over = overRect(dimension.width, dimension.height, mode);
    return dimension.over;
  }
  
  public void draw() {
    super.draw();
    graphic.rectMode(mode);
    Font.display(graphic, 2,dimension.height-2, text, dimension.height-4);
  }
  
  public void update() {
    super.update();
    float w = Font.textWidth(text, dimension.height-4) +4;
    if(dimension.width != w) dimension.width = w;
  }
  
}
