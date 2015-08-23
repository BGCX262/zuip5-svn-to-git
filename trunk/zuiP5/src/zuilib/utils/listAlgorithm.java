package zuilib.utils;

import zuilib.components.List;
import zuilib.core.component;

public class listAlgorithm {
  
  public List parent;
  public boolean sorted;
  
  public void install(List lparent) {
    parent = lparent;
    sorted = false;
  }
  
  public void preprocessing() {return;}
  
  public vector getPosition(int n) {
    return new vector(0,0);
  }
  
  public component getComponent(int n) {
    if(sorted) return (component) parent.components.getSorted(n);
    return (component) parent.components.get(n);
  }

  public boolean isListFull() {
    return true;
  }

}
