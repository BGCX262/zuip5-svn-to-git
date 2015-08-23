package zuilib.utils;

import processing.core.PApplet;
import zuilib.core.component;

public class listArray1D extends listAlgorithm {
  
  public float[] position;
  public int size;
  
  public listArray1D(int isize) {
    size = isize;
    position = new float[size];
  }
  
  public void preprocessing() {
    super.preprocessing();
    int end = PApplet.min(parent.components.length, size);
    float pre = 0f;
    position = new float[size];
    component com = null;
    for(int i = 0 ; i < end ; i += 1 ) {
      position[i] = pre;
      com = getComponent(i);
      //PApplet.println(i+": "+com.getClass().getName()+"  "+com.dimension.getClass().getName());
      if(com.isVisible()) pre += com.dimension.getBoundingBox().y;
    }
  }
  
  public vector getPosition(int n) {
    return new vector(0,position[n]);
  }
  
  public boolean isListFull() {
    return parent.components.length >= size;
  }

}
