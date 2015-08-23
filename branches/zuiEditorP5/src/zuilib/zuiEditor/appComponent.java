package zuilib.zuiEditor;

import zuilib.utils.ColorBundle;
import zuilib.utils.vector;

public class appComponent extends appStructure {
  
  public MainApp parent;
  public vector position;
  public vector dimension;
  public ColorBundle color;

  public appComponent(MainApp curParent, float fx, float fy, float fwidth, float fheight) {
    super(curParent.ui);
    parent = curParent;
    position = new vector(fx,fy);
    dimension = new vector(fwidth, fheight);
    color = new ColorBundle(0,0,0);
  }

}
