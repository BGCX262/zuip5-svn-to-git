import zuilib.windows.*;
import zuilib.core.*;
import zuilib.manager.*;
import zuilib.components.*;

ZUI ui;

void setup() {
  size(320,240);
  smooth();

  ui = new ZUI(this,5);
  ui.FONT_FILE = "Verdana-48.vlw";
  ui.addManager( new DrawManager("simpleDraw",true) );
  ui.addManager( new KeyboardManager("simpleKeyboard",true) );
  ui.addManager( new simpleMouseManager("simpleMouse",true) );
  ui.addManager( new simpleWindowManager("simpleWindow",true) );
  ui.addManager( new simplePFontManager("simpleFont",true) );
    
  simpleWindowManager winman = (simpleWindowManager) ui.get("simpleWindow");
  window win = new RectWindow("testRect", -100, -50);
  winman.addWindow(win);
  ((RectWindow) win).dimension.setDim(200,100);
  ((RectWindow) win).area.set(1, 1, 10, 99);
  ((RectWindow) win).Color.set(color(160),color(200),color(80),color(255,50,50),color(200),color(80));
  ((RectWindow) win).setOpaq(100);
  win.rotation.setEnable(false);
  win.addComponent( new CircleShape("CircleShape1",50,50,45,color(200,0,0,200),color(100,0,0,100)) );
  component com = new zuilib.components.Timer("Timer1",1,true);
  win.addComponent(com);
  ((zuilib.components.Timer) com).setFunction("onTimer", "onTimer1");
  win.addComponent( new zuilib.components.Label("Label",77,45,"Hello World!",10) );
}
 
void draw() {
  background(23);
}

/////////////

void onTimer1() {
  simpleWindowManager winman = (simpleWindowManager) ui.get("simpleWindow");
  winman.rotation.set(winman.rotation.get() + 0.01f);
}



