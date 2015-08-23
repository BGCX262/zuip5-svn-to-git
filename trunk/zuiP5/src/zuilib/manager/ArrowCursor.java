package zuilib.manager;

import processing.core.PGraphics;


public class ArrowCursor extends Cursor {
  
  public int Color;
  public int Stroke;
  public int size;
  public int opac;
  
  public ArrowCursor(int isize, int istroke, int ccolor, int iopac) {
    Stroke = istroke;
    Color = ccolor;
    size = isize;
    opac = iopac;
  }
  
  public void display() {
    PGraphics graphic = getPApplet().g;
    graphic.stroke(Color);
    graphic.fill(Color,opac);
    graphic.scale(size/10);
    graphic.strokeWeight(Stroke/(size/10));
    graphic.beginShape();
    graphic.vertex(0,0);
    graphic.vertex(10,6);
    graphic.vertex(6,6);
    graphic.vertex(6,10);
    graphic.vertex(0,0);
    graphic.endShape();
    graphic.strokeWeight(1);
  }
  
}
