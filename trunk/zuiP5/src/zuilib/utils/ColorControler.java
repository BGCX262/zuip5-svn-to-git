package zuilib.utils;

import processing.core.PApplet;
import processing.core.PGraphics;
import zuilib.core.controlerObject;
import zuilib.core.zuiAtomObject;
import zuilib.manager.colormanager;

public class ColorControler extends controlerObject {
  
  public ColorControl normal;
  public ColorControl highlight;
  private ColorControl current;
  private boolean highlighting; // trigger if current is normal or highlight
  private boolean isbackground; // switch between fore- and background
  public boolean enable; //highlights enable ; if off no higlightling
  public boolean foreborder;
  public boolean backborder;
  private float opaq;
  public boolean transparence;

  public ColorControler(zuiAtomObject curParent) {
    super(curParent);
    normal = new ColorControl();
    highlight = new ColorControl(normal);
    ColorControler_init();
  }
  
  public ColorControler(zuiAtomObject curParent,ColorBundle cbcolors) {
    super(curParent);
    setColor(cbcolors);
    ColorControler_init();
  }
  
  public ColorControler(zuiAtomObject curParent,ColorControl ccnormal, ColorControl cchigh) {
    super(curParent);
    setColor(ccnormal,cchigh);
    ColorControler_init();
  }
  
  public ColorControler(zuiAtomObject curParent,String scolorname) {
    super(curParent);
    if(getPApplet() == null) {
      System.err.println("[CRITICAL ERROR]: ColorControler: ParentObject wasn't set up.");
      System.err.println("                  You should maybe set the initialization of ColorControler into the function setup().");
    }
    setColor(scolorname);
    ColorControler_init();
  }
  
  public ColorControler() {
    super();
    normal = new ColorControl();
    highlight = new ColorControl(normal);
    ColorControler_init();
  }
  
  public ColorControler(ColorBundle cbcolors) {
    super();
    setColor(cbcolors);
    ColorControler_init();
  }
  
  public ColorControler(ColorControl ccnormal, ColorControl cchigh) {
    super();
    setColor(ccnormal,cchigh);
    ColorControler_init();
  }
  
  private void ColorControler_init() {
    enable = true;
    current = normal;
    highlighting = false;
    isbackground = true;
    foreborder = true;
    backborder = true;
    transparence = true;
  }
  
  public void setup(zuiAtomObject curParent) {
    super.setup(curParent);
    if(normal.back == 0 && normal.fore == 0 && normal.border == 0) {
      PGraphics g = getPApplet().g;
      normal.set(g.fillColor,g.fillColor,g.strokeColor);
      highlight.set(normal);
    }
  }
  
  public void setEnable(boolean newenable) {
    enable = newenable;
  }
  
  public boolean isEnable() {
    return enable;
  }
  
  public void setTransparence(boolean newtransparence) {
    transparence = newtransparence;
  }
  
  public boolean isTransparence() {
    return transparence;
  }  
  
  public void setColor(String scolorname) {
    colormanager colorman = ((colormanager) getManagerByType("color"));
    ColorBundle color = colorman.get(scolorname);
    if(color == null) {
      PApplet.println("[ERROR]: setColor: Given color "+scolorname+" does not exists.");
      return;
    }
    setColor(color);
  }
  
  public void setColor(int cfore,int cback, int cborder) {
    setColor(cfore,cback,cborder,cfore,cback,cborder);
  }
  
  public void setColor(ColorControl ccnormal) {
    setColor(ccnormal.fore,ccnormal.back,ccnormal.border,ccnormal.fore,ccnormal.back,ccnormal.border);
  }

  public void setColor(int cfore,int cback, int cborder, int chighfore, int chighback, int chighborder) {
    normal = new ColorControl(cfore,cback,cborder);
    highlight = new ColorControl(chighfore,chighback,chighborder);
    current = normal;
    highlighting = false;
  }
  
  public void setColor(ColorControl ccnormal, ColorControl cchigh) {
    setColor(ccnormal.fore,ccnormal.back,ccnormal.border,cchigh.fore,cchigh.back,cchigh.border);
  }
  
  public void setColor(ColorBundle cbcolors) {
    normal = new ColorControl(cbcolors.getNormal());
    highlight = new ColorControl(cbcolors.getHighlight());
    current = normal;
    highlighting = false;
  }
  
  public void set(String scolorname) {
    setColor(scolorname);
  }
  
  public void set(int cfore,int cback, int cborder) {
    setColor(cfore,cback,cborder,cfore,cback,cborder);
  }
  
  public void set(ColorControl ccnormal) {
    setColor(ccnormal.fore,ccnormal.back,ccnormal.border,ccnormal.fore,ccnormal.back,ccnormal.border);
  }

  public void set(int cfore,int cback, int cborder, int chighfore, int chighback, int chighborder) {
    setColor(cfore,cback,cborder,chighfore,chighback,chighborder);
  }
  
  public void set(ColorControl ccnormal, ColorControl cchigh) {
    setColor(ccnormal.fore,ccnormal.back,ccnormal.border,cchigh.fore,cchigh.back,cchigh.border);
  }  
  
  public void setNormal(ColorControl newcc) {
    normal = new ColorControl(newcc);
    setHighlighting(highlighting);
  }
  
  public void setNormal(int cfore,int cback, int cborder) {
    normal = new ColorControl(cfore,cback,cborder);
    setHighlighting(highlighting);
  }
  
  public void setHighlight(ColorControl newcc) {
    highlight = new ColorControl(newcc);
    setHighlighting(highlighting);
  }
  
  public void setHighLight(int cfore,int cback, int cborder) {
    highlight = new ColorControl(cfore,cback,cborder);
    setHighlighting(highlighting);
  }
  
  public ColorControl getNormal() {
    return normal;
  }
  
  public ColorControl getHighlight() {
    return highlight;
  }
  
  public boolean isHighlighting() {
    return highlighting;
  }
  
  public boolean isHigh() {
    return highlighting;
  }
  
  public void setHighlighting(boolean newhh) {
    highlighting = newhh;
    if(newhh && enable) {
      current = highlight;
    } else {
      current = normal;
    }
  }
  
  public void setAsForeground() {
    isbackground = false;
  }
  
  public void setAsBackground() {
    isbackground = true;
  }
  
  public void setAsFore() {
    setAsForeground();
  }
  
  public void setAsBack() {
    setAsBackground();
  }
  
  public void setNormalBorder(boolean newborder) {
    normal.enable = newborder;
  }
  
  public void setHighlightBorder(boolean newborder) {
    highlight.enable = newborder;
  }
  
  public void setBorder(boolean newborder) {
    normal.enable = newborder;
    highlight.enable = newborder;
  }
  
  public void setForegroundBorder(boolean newborder) {
    foreborder = newborder;
  }
  
  public void setBackgroundBorder(boolean newborder) {
    backborder = newborder;
  }
  
  public void setForeBorder(boolean newborder) {
    setForegroundBorder(newborder);
  }
  
  public void setBackBorder(boolean newborder) {
    setBackgroundBorder(newborder);
  }
  
  public boolean isBorder() {
    return ( normal.enable || highlight.enable );
  }
  
  public void doBackground() {
    setOpaq();
    if(enable) graphic.fill(current.back,opaq); else graphic.noFill();
    doBorder();
  }
  
  public void doForeground() {
    setOpaq();
    if(enable) graphic.fill(current.fore,opaq); else graphic.noFill();
    doBorder();
  }
  
  private void doBorder() {
    graphic.stroke(current.border,opaq);
    if(!foreborder || !current.enable || !enable) graphic.noStroke();
  }
  
  private void setOpaq() {
    opaq = getParent().getOpaqAbsolute();
    if(!transparence) opaq = 255;
  }
  
  public void doTask() {
    if(isbackground) {
        doBackground();
      } else {
        doForeground();
    }
  }

}
