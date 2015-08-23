package enginelib;

import noc.Vector3D;
import processing.core.PApplet;
import processing.core.PConstants;

public class Camera3D extends Object3D {
  
  public Vector3D target;

  public Camera3D(String sname) {
    super(sname);
    target = new Vector3D();
  }
  
  public void setup() {
    super.setup();
    position.setXYZ(0, 0, (graphic.height/2f) / PApplet.tan(PConstants.PI*60f / 360f));
    target.setXYZ(0, 0, 0);
    rotation.setXYZ(0, 1, 0);
  }
  
  public void predraw() {
    graphic.camera(position.x,position.y,position.z,
                   target.x,target.y,target.z,
                   rotation.x,rotation.y,rotation.z);
    super.predraw();
  }
  

}
