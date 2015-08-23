import processing.core.*; import zuilib.manager.*; import zuilib.core.*; import noc.*; import enginelib.*; import enginelib.manager.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; import javax.sound.midi.*; import javax.sound.midi.spi.*; import javax.sound.sampled.*; import javax.sound.sampled.spi.*; import java.util.regex.*; import javax.xml.parsers.*; import javax.xml.transform.*; import javax.xml.transform.dom.*; import javax.xml.transform.sax.*; import javax.xml.transform.stream.*; import org.xml.sax.*; import org.xml.sax.ext.*; import org.xml.sax.helpers.*; public class zuilib3D extends PApplet {


 // Vector 3D class from nature of code http://www.shiffman.net/teaching/nature/library/




ZUI ui;
float depth = 400;
objectControler3D env;

public void setup() {
  size(200, 200, P3D);
  
  ui = new ZUI(this,5);
  ui.addManager( new DrawManager("simpleDraw",true) );
  ui.addManager( new simpleMouseManager("simpleMouse",true) );
  ui.addManager( new KeyboardManager("simpleKeyboard",true) );
  
  ui.addManager( new engine3DManager("simple3D",true) );
  env = ((engine3DManager) ui.get("simple3D")).get();
  
  Object3D grid = new Object3D("Grid1");
  env.addObject( grid );
  grid.position.setXYZ(0,0,-depth/2);
  createGrid(grid,40,40*2,depth);
}

public void draw() {
  background(250);
  env.get("Grid1").rotation.setXYZ(frameCount*PI/300,frameCount*PI/300,0);
}

public void createGrid(Object3D grid, float boxSize, float margin, float depth) {
  strokelessBox3D box3;
  int boxFill;
  for (float i=-depth/2+margin; i<=depth/2-margin; i+=boxSize) {
    for (float j=-height+margin; j<=height-margin; j+=boxSize) {
      for (float k=-width+margin; k<=width-margin; k+=boxSize) {
        // base fill color on counter values, abs function 
        // ensures values stay within legal range
        boxFill = color(abs(i), abs(j), abs(k),50);
        
        box3 = new strokelessBox3D("Box_"+i+"_"+j+"_"+k,boxSize);
        grid.addObject( box3 );
        box3.fill = boxFill;
        box3.position.setXYZ(k,j,i);
      }
    }
  }
}

//////////////////////////

class strokelessBox3D extends Object3D {
  
  float boxSize;
  
  strokelessBox3D(String sname, float fboxSize) {
    super(sname);
    boxSize = fboxSize;
  }
  
  public void draw() {
    graphic.noStroke();
    graphic.box(boxSize);
  }
  
}
  static public void main(String args[]) {     PApplet.main(new String[] { "zuilib3D" });  }}