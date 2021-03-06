import noc.*; // Vector 3D class from nature of code http://www.shiffman.net/teaching/nature/library/
import enginelib.*;

objectControler3D oc3d;
  
void setup() {
  size(320,240,P3D);
  oc3d = new objectControler3D(this);
  oc3d.addObject( new Camera3D("Cam1") );
    
  Object3D tower = new Object3D("Tower1");
  oc3d.addObject(tower);
  createTower(tower,60,25,25,175,16,18,color(175, 87, 20),color(100, 50, 10));
  tower = new Object3D("Tower2");
  oc3d.addObject(tower);
  createTower(tower,60,25,25,275,26,18,color(175, 87, 20,100),color(100, 50, 10,10));
}
  
void draw() {
  background(100, 125, 200);
  
  // add basic light setup
  lights();
  oc3d.get("Cam1").position.setXYZ(-10, -mouseX*4, -400);
  ((Camera3D) oc3d.get("Cam1")).target.y = -mouseY*2-mouseX;
  oc3d.get("Tower1").rotation.setY(frameCount*PI/600);
  oc3d.get("Tower2").rotation.setY(-frameCount*PI/600);
}
  
void createTower(Object3D tower, float brickWidth,float brickHeight,float brickDepth,
                 float radius,float bricksPerLayer, float brickLayers,
                 color fillcolor, color strokecolor) {
  float tempX = 0, tempY = 0, tempZ = 0, angle = 0;
  Cube3D brick;
  for (int i = 0; i < brickLayers; i++){
    // increment rows
    tempY-=brickHeight;
    // alternate brick seams
    angle = 360/bricksPerLayer*i/2;
    for (int j = 0; j < bricksPerLayer; j++){
      tempZ = cos(radians(angle))*radius;
      tempX = sin(radians(angle))*radius;
        
      brick = new Cube3D("Brick_"+i+"_"+j,brickWidth, brickHeight, brickDepth);
      brick.position.setXYZ(tempX, tempY, tempZ);
      brick.rotation.setXYZ(0,radians(angle),0);
      brick.stroke = strokecolor;
      brick.fill = fillcolor;
      // add crenelation
      if (i==brickLayers-1){
        if (j%2 == 0){
          tower.addObject(brick);
        }
      }
      // create main tower
      else {
        tower.addObject(brick);
      }
      angle += 360.0/bricksPerLayer;
    }
  }
}
  
class Cube3D extends Object3D {

  float w, h, d;
  Vector3D[] vertices = new Vector3D[24];
    
  Cube3D(String sname,float fw, float fh, float fd) {
    super(sname);
    w = fw;
    h = fh;
    d = fd;
    // cube composed of 6 quads
    //front
    vertices[0] = new Vector3D(-w/2,-h/2,d/2);
    vertices[1] = new Vector3D(w/2,-h/2,d/2);
    vertices[2] = new Vector3D(w/2,h/2,d/2);
    vertices[3] = new Vector3D(-w/2,h/2,d/2);
    //left
    vertices[4] = new Vector3D(-w/2,-h/2,d/2);
    vertices[5] = new Vector3D(-w/2,-h/2,-d/2);
    vertices[6] = new Vector3D(-w/2,h/2,-d/2);
    vertices[7] = new Vector3D(-w/2,h/2,d/2);
    //right
    vertices[8] = new Vector3D(w/2,-h/2,d/2);
    vertices[9] = new Vector3D(w/2,-h/2,-d/2);
    vertices[10] = new Vector3D(w/2,h/2,-d/2);
    vertices[11] = new Vector3D(w/2,h/2,d/2);
    //back
    vertices[12] = new Vector3D(-w/2,-h/2,-d/2);  
    vertices[13] = new Vector3D(w/2,-h/2,-d/2);
    vertices[14] = new Vector3D(w/2,h/2,-d/2);
    vertices[15] = new Vector3D(-w/2,h/2,-d/2);
    //top
    vertices[16] = new Vector3D(-w/2,-h/2,d/2);
    vertices[17] = new Vector3D(-w/2,-h/2,-d/2);
    vertices[18] = new Vector3D(w/2,-h/2,-d/2);
    vertices[19] = new Vector3D(w/2,-h/2,d/2);
    //bottom
    vertices[20] = new Vector3D(-w/2,h/2,d/2);
    vertices[21] = new Vector3D(-w/2,h/2,-d/2);
    vertices[22] = new Vector3D(w/2,h/2,-d/2);
    vertices[23] = new Vector3D(w/2,h/2,d/2);
  }
    
  void draw(){
    // draw cube
    for (int i=0; i<6; i++){
      graphic.beginShape(QUADS);
      for (int j=0; j<4; j++){
        graphic.vertex(vertices[j+4*i].x, vertices[j+4*i].y, vertices[j+4*i].z);
      }
      graphic.endShape();
    }
  }
    
}
