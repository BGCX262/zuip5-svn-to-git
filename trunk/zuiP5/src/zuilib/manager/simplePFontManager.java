package zuilib.manager;

import processing.core.PApplet;
import zuilib.utils.zuiFont;
import zuilib.utils.zuiPFont;


public class simplePFontManager extends simplefontmanager {

  public simplePFontManager(String sname, boolean benable) {
    super(sname,benable);
  }

  public int addFont(String newf) {
    if(getZUI().DEBUG >= 5) PApplet.println("* Add PFont "+newf);
    return addFont( new zuiPFont(this,newf) );
  }
  
  public int addFont(zuiPFont newfont) {
    return super.addFont(newfont);
  }

  public zuiPFont getFont(String sname) {
    zuiFont result = super.getFont(sname);
    if(result != null) return (zuiPFont) result;
    return (zuiPFont) getFont( addFont(sname) );
  }

  public boolean setFont(int i,String newf) {
    return super.setFont(i, new zuiPFont(this,newf) );
  }
  
  public boolean set(int i, String newf) {
    return setFont(i,newf);
  }

}
