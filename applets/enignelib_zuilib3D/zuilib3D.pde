import zuilib.manager.*;
import zuilib.core.*;

import noc.*; // Vector 3D class from nature of code http://www.shiffman.net/teaching/nature/library/
import enginelib.*;
import enginelib.manager.*;


ZUI ui;
float depth = 400;
objectControler3D env;

void setup() {
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

void draw() {
  background(250);
  env.get("Grid1").rotation.setXYZ(frameCount*PI/300,frameCount*PI/300,0);
}

void createGrid(Object3D grid, float boxSize, float margin, float depth) {
  strokelessBox3D box3;
  color boxFill;
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
  
  void draw() {
    graphic.noStroke();
    graphic.box(boxSize);
  }
  
}
