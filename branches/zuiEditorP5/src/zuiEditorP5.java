import processing.core.PApplet;
import zuilib.core.*;
import zuilib.zuiEditor.MainApp;


public class zuiEditorP5 extends PApplet {
  private static final long serialVersionUID = 4832344962807914505L;
  
  public ZUI ui;
  public MainApp app;
  
  public void setup() {
    size(512,320);
    smooth();
    
    ui = new ZUI(this,5);
    app = new MainApp(ui);
    
    app.setup();
  }
  
  public void draw() {
    background(89);
  }

}
