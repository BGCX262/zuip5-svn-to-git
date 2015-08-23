package zuilib.manager;

import java.util.HashMap;

import processing.core.PApplet;
import zuilib.utils.FontControl;
import zuilib.utils.zuiFont;

public class simplefontmanager extends fontmanager {

  public FontControl[] fonts;
  public int length;
  private HashMap<String, Integer> fonts_map = new HashMap<String, Integer>();

  public simplefontmanager(String sname, boolean benable) {
    super(sname,benable);
    fonts = new FontControl[0];
    length = 0;
  }
  
  public int addFont(zuiFont newfont) {
    fonts = (FontControl[]) PApplet.append(fonts, new FontControl(this, newfont ) );
    fonts_map.put(newfont.Name, length);
    length += 1;
    return length-1;
  }

  public zuiFont getFont(String sname) {
    Integer i = fonts_map.get(sname);
    if(i != null) return fonts[i].get();
    return null;
  }

  public zuiFont getFont(int i) {
    if( i < length ) {
      return fonts[i].get();
    } 
    else {
      PApplet.println("[WARNING]: getFont:  Requested font no. "+i+" returned null.");
      return null;
    }
  }

  public zuiFont get(String sname) {
    return getFont(sname);
  }
  
  public zuiFont get(int i) {
    return getFont(i);
  }

  public boolean setFont(int i,zuiFont newfont) {
    if( i < length ) {
      fonts_map.remove(fonts[i].Name);
      fonts[i] = new FontControl(this, newfont );
      fonts_map.put(fonts[i].Name, i);
      return true;
    } 
    else {
      if(getZUI().DEBUG >= 1) PApplet.println("[WARNING]: setFont:  Setting font "+newfont.Name+" on position "+i+" failed.");
      return false;
    }
  }
  
  public boolean set(int i, zuiFont newfont) {
    return setFont(i,newfont);
  }

}
