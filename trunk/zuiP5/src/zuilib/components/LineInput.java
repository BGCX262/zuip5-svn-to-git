package zuilib.components;



public class LineInput extends input {
  
  private String curText;
  private float width;

  public LineInput(String sname, float fx, float fy, String newText,float fwidth, float ffontheight) {
    super(sname, fx, fy, ffontheight);
    curText = newText;
    width = fwidth;
  }
  
  public void setup() {
    super.setup();
    addLine(curText);
    dimension.width = width;
  }
  
  public void setText(String newtext) {
    super.setText(0, newtext);
    dimension.width = width;
  }
  
  public void draw() {
    super.draw();
    graphic.rectMode(mode);
    graphic.rect(0,0,dimension.width,dimension.height);
  }

}
