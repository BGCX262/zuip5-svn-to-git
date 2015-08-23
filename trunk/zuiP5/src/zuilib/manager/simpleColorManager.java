package zuilib.manager;

import java.util.HashMap;

import processing.core.PApplet;
import zuilib.utils.ColorBundle;

public class simpleColorManager extends colormanager {
  
  public ColorBundle[] colorbundles;
  public int index;
  private HashMap<String, Integer> colorbundles_map = new HashMap<String, Integer>();

  public simpleColorManager(String sname, boolean benable) {
    super(sname, benable);
    colorbundles = new ColorBundle[0];
    index = 0;
  }
  
  public int addColor(ColorBundle newcb) {
    if(getZUI().DEBUG >= 5) PApplet.println("* Add ColorBundle "+newcb.Name);
    colorbundles = (ColorBundle[]) PApplet.append(colorbundles, newcb );
    colorbundles_map.put(newcb.Name, index);
    index += 1;
    return index-1;
  }

  public ColorBundle getColor(String sname) {
    Integer i = colorbundles_map.get(sname);
    if(i != null) return colorbundles[i];
    PApplet.println("[WARNING]: getColor:  Requested ColorBundle "+sname+" returned null.");
    return null;
  }

  public ColorBundle getColor(int i) {
    if( i < index ) {
      return colorbundles[i];
    }
    else {
      PApplet.println("[WARNING]: getColor:  Requested ColorBundle no. "+i+" returned null.");
      return null;
    }
  }

  public ColorBundle get(String sname) {
    return getColor(sname);
  }
  
  public ColorBundle get(int i) {
    return getColor(i);
  }

  public boolean setColor(int i,ColorBundle newcb) {
    if( i < index ) {
      colorbundles_map.remove(colorbundles[i].Name);
      colorbundles[i] = newcb;
      colorbundles_map.put(newcb.Name, i);
      return true;
    } 
    else {
      if(getZUI().DEBUG >= 1) PApplet.println("[WARNING]: setColor:  Setting ColorBundle "+newcb.Name+" on position "+i+" failed.");
      return false;
    }
  }
  
  public boolean set(int i, ColorBundle newcb) {
    return setColor(i,newcb);
  }

}
