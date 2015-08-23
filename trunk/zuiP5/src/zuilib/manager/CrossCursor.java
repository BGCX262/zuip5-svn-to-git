package zuilib.manager;

import processing.core.PGraphics;

public class CrossCursor extends Cursor {
  
  public int Color;
  public int Stroke;
  public int size;
  
  public CrossCursor(int isize, int istroke, int ccolor) {
    Stroke = istroke;
    Color = ccolor;
    size = isize;
  }
  
  public void display() {
    PGraphics graphic = getPApplet().g;
    graphic.stroke(Color);
    graphic.strokeWeight(Stroke);
    graphic.line(-size/2,0,+size/2,0);
    graphic.line(0,-size/2,0,+size/2);
    graphic.strokeWeight(1);
  }
  
}
