package zuilib.utils;

import zuilib.core.component;

public class listDynamicArray1D extends listAlgorithm {
  
  public float[] position;
  
  public listDynamicArray1D() {
    position = new float[0];
  }
  
  public void preprocessing() {
    super.preprocessing();
    int size = parent.components.length;
    float pre = 0f;
    position = new float[size];
    component com = null;
    for(int i = 0 ; i < size ; i += 1 ) {
      position[i] = pre;
      com = getComponent(i);
      if(com.isVisible()) pre += com.dimension.getBoundingBox().y;
    }
    //if(parent.dimension.height != pre) parent.dimension.height = pre; FIXME uncomment this
  }
  
  public vector getPosition(int n) {
    return new vector(0,position[n]);
  }
  
  public boolean isListFull() {
    return false;
  }

}
