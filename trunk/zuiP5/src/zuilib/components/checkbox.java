package zuilib.components;

public class checkbox extends TextButton {
  
  public boolean checked;
  
  public checkbox(String sname, float fx, float fy, String stext, float fsize) {
    super(sname,fx,fy,"   "+stext,fsize);
    checked = false;
  }
  
  public void setup() {
    super.setup();
    addFunction("onCheck");
  }
  
  public boolean over() {
    dimension.over = overRect(text.position, dimension.width+dimension.height, dimension.height, mode);
    return dimension.over;
  }
  
  public void mouseClicked() {
    boolean bool = pressed;
    super.mouseClicked();
    if(bool) {
      checked = !checked;
      useFunction("onCheck");
    }
  }
  
  public boolean isChecked() {
    return checked;
  }
  
  public void setCheck(boolean newcheck) {
    checked = newcheck;
  }
  
}
