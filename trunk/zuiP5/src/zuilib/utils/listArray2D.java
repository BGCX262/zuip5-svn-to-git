package zuilib.utils;

import processing.core.PApplet;

public class listArray2D extends listAlgorithm {
  
  public vector[][] position;
  public int width;
  public int height;
  
  public listArray2D(int iwidth, int iheight) {
    width = iwidth;
    height = iheight;
    position = new vector[width][height];
  }
  
  public void preprocessing() {
    super.preprocessing();
    vector bb = null;
    float pre_x = 0f;
    float pre_y = 0f;
    float hy = 0f;
    int x = 0;
    int y = 0;
    int end = PApplet.min(parent.components.length,width*height);
    for(int i = 0 ; i < end ; i += 1 ) {
      x += 1;
      if(x == width) {
        pre_x = 0f;
        pre_y += hy;
        hy = 0f;
        x = 0;
        y += 1;
      }
      
      position[x][y] = new vector(pre_x,pre_y);
      bb = getComponent(i).dimension.getBoundingBox();
      hy = PApplet.max(hy, bb.y);
      pre_x += bb.x;
    }
  }
  
  public vector getPosition(int n) {
    int x = n%(width+1)-1;
    int y = PApplet.floor(n/width);
    return position[x][y];
  }
  
  public boolean isListFull() {
    return parent.components.length >= width*height;
  }
  
}
