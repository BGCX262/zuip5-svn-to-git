package zuilib.utils;

public class ColorControl {

  public /*color*/ int fore;
  public /*color*/ int back;
  public /*color*/ int border;
  public boolean enable; // borders enable
  
  public ColorControl() {
    ColorControl_init(0,0,0);
  }
  
  public ColorControl(int cfore, int cback, int cborder) {
    ColorControl_init(cfore,cback,cborder);
  }
  
  public ColorControl(ColorControl oldcc) {
    ColorControl_init(oldcc.fore,oldcc.back,oldcc.border);
  }

  private void ColorControl_init(int cfore, int cback, int cborder) {
    fore = cfore;
    back = cback;
    border = cborder;
    enable = true;
  }
  
  public void set(int cfore, int cback, int cborder) {
    ColorControl_init(cfore,cback,cborder);
  }
  
  public void set(ColorControl oldcc) {
    ColorControl_init(oldcc.fore,oldcc.back,oldcc.border);
  }
  
  public void setBorder(boolean newborder) {
    enable = newborder;
  }
  
  public boolean isBorder() {
    return enable;
  }
  
}
