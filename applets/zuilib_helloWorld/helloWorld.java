import processing.core.*; import zuilib.windows.*; import zuilib.core.*; import zuilib.manager.*; import zuilib.components.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; import javax.sound.midi.*; import javax.sound.midi.spi.*; import javax.sound.sampled.*; import javax.sound.sampled.spi.*; import java.util.regex.*; import javax.xml.parsers.*; import javax.xml.transform.*; import javax.xml.transform.dom.*; import javax.xml.transform.sax.*; import javax.xml.transform.stream.*; import org.xml.sax.*; import org.xml.sax.ext.*; import org.xml.sax.helpers.*; public class helloWorld extends PApplet {




ZUI ui;

public void setup() {
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
 
public void draw() {
  background(23);
}

/////////////

public void onTimer1() {
  simpleWindowManager winman = (simpleWindowManager) ui.get("simpleWindow");
  winman.rotation.set(winman.rotation.get() + 0.01f);
}



  static public void main(String args[]) {     PApplet.main(new String[] { "helloWorld" });  }}