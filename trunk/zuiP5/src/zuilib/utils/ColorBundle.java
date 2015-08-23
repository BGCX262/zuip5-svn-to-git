package zuilib.utils;

public class ColorBundle {
  
  public ColorControl normal;
  public ColorControl highlight;
  public String Name;
  
  public ColorBundle(int cfore,int cback, int cborder) {
    ColorBundle_init(toString(),cfore,cback,cborder,cfore,cback,cborder);
  }
  
  public ColorBundle(ColorControl ccnormal) {
    ColorBundle_init(toString(),ccnormal.fore,ccnormal.back,ccnormal.border,ccnormal.fore,ccnormal.back,ccnormal.border);
  }

  public ColorBundle(int cfore,int cback, int cborder, int chighfore, int chighback, int chighborder) {
    ColorBundle_init(toString(),cfore,cback,cborder,chighfore,chighback,chighborder);
  }
  
  public ColorBundle(ColorControl ccnormal, ColorControl cchigh) {
    ColorBundle_init(toString(),ccnormal.fore,ccnormal.back,ccnormal.border,cchigh.fore,cchigh.back,cchigh.border);
  }
  
  public ColorBundle(String sname,int cfore,int cback, int cborder) {
    ColorBundle_init(sname,cfore,cback,cborder,cfore,cback,cborder);
  }
  
  public ColorBundle(String sname,ColorControl ccnormal) {
    ColorBundle_init(sname,ccnormal.fore,ccnormal.back,ccnormal.border,ccnormal.fore,ccnormal.back,ccnormal.border);
  }

  public ColorBundle(String sname,int cfore,int cback, int cborder, int chighfore, int chighback, int chighborder) {
    ColorBundle_init(sname,cfore,cback,cborder,chighfore,chighback,chighborder);
  }
  
  public ColorBundle(String sname,ColorControl ccnormal, ColorControl cchigh) {
    ColorBundle_init(sname,ccnormal.fore,ccnormal.back,ccnormal.border,cchigh.fore,cchigh.back,cchigh.border);
  }

  private void ColorBundle_init(String sname,int cfore, int cback, int cborder, int chighfore, int chighback, int chighborder) {
    Name = sname;
    normal = new ColorControl(cfore,cback,cborder);
    highlight = new ColorControl(chighfore,chighback,chighborder);
  }
  
  public void set(int cfore,int cback, int cborder) {
    ColorBundle_init(toString(),cfore,cback,cborder,cfore,cback,cborder);
  }
  
  public void set(ColorControl ccnormal) {
    ColorBundle_init(toString(),ccnormal.fore,ccnormal.back,ccnormal.border,ccnormal.fore,ccnormal.back,ccnormal.border);
  }

  public void set(int cfore,int cback, int cborder, int chighfore, int chighback, int chighborder) {
    ColorBundle_init(toString(),cfore,cback,cborder,chighfore,chighback,chighborder);
  }
  
  public void set(ColorControl ccnormal, ColorControl cchigh) {
    ColorBundle_init(toString(),ccnormal.fore,ccnormal.back,ccnormal.border,cchigh.fore,cchigh.back,cchigh.border);
  }
  
  public void set(String sname,int cfore,int cback, int cborder) {
    ColorBundle_init(sname,cfore,cback,cborder,cfore,cback,cborder);
  }
  
  public void set(String sname,ColorControl ccnormal) {
    ColorBundle_init(sname,ccnormal.fore,ccnormal.back,ccnormal.border,ccnormal.fore,ccnormal.back,ccnormal.border);
  }

  public void set(String sname,int cfore,int cback, int cborder, int chighfore, int chighback, int chighborder) {
    ColorBundle_init(sname,cfore,cback,cborder,chighfore,chighback,chighborder);
  }
  
  public void set(String sname,ColorControl ccnormal, ColorControl cchigh) {
    ColorBundle_init(sname,ccnormal.fore,ccnormal.back,ccnormal.border,cchigh.fore,cchigh.back,cchigh.border);
  }
  
  public void set(ColorBundle cbcolors) {
    Name = cbcolors.Name;
    normal = new ColorControl(cbcolors.getNormal());
    highlight = new ColorControl(cbcolors.getHighlight());
  }
  
  public void setColor(int cfore,int cback, int cborder) {
    set(cfore,cback,cborder);
  }
  
  public void setColor(ColorControl ccnormal) {
    set(ccnormal);
  }

  public void setColor(int cfore,int cback, int cborder, int chighfore, int chighback, int chighborder) {
    set(cfore,cback,cborder,chighfore,chighback,chighborder);
  }
  
  public void setColor(ColorControl ccnormal, ColorControl cchigh) {
    set(ccnormal,cchigh);
  }
  
  public void setColor(ColorBundle cbcolors) {
    set(cbcolors);
  }
  
  public void setNormal(ColorControl newcc) {
    normal = new ColorControl(newcc);
  }
  
  public void setNormal(int cfore,int cback, int cborder) {
    normal = new ColorControl(cfore,cback,cborder);
  }
  
  public void setHighlight(ColorControl newcc) {
    highlight = new ColorControl(newcc);
  }
  
  public void setHighLight(int cfore,int cback, int cborder) {
    highlight = new ColorControl(cfore,cback,cborder);
  }
  
  public ColorControl getNormal() {
    return normal;
  }
  
  public ColorControl getHighlight() {
    return highlight;
  }

}
