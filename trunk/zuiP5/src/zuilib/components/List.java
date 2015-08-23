package zuilib.components;

import processing.core.PApplet;
import zuilib.core.component;
import zuilib.utils.listAlgorithm;
import zuilib.utils.vector;

public class List extends RectGroupControler {
  
  public listAlgorithm algorithm;

  public List(String sname, float fx, float fy, float fwidth, float fheight, listAlgorithm lalgorithm) {
    super(sname, fx, fy,fwidth,fheight);
    algorithm = lalgorithm;
  }
  
  public List(String sname, vector vpos, float fwidth, float fheight, listAlgorithm lalgorithm) {
    super(sname,vpos,fwidth,fheight);
    algorithm = lalgorithm;
  }
  
  public void setup() {
    super.setup();
    autoSize.setEnable(false);
    algorithm.install(this);
    components.unRegisterPre("postdraw","display");
  }
  
  public int addComponent(component newc) {
    if(!algorithm.isListFull()) return super.addComponent(newc);
    PApplet.println("[WARNING]: addComponent: can't add component "+newc.Name.get()+" cause list is full.");
    return -1;
  }
  
  /**
   * Stellt das Fenster komplett dar.
   */
  public void display() {
    predraw();
    draw();
    if(components.enable) {
      algorithm.preprocessing();
      component com = null;
      //vector pos = null;
      for(int i = 0 ; i < components.length ; i += 1 ) {
        com = algorithm.getComponent(i);
        if(com.isVisible()) {
          //graphic.pushMatrix(); // FIXM List: this is shit!! replace with offsets
          //pos = algorithm.getPosition(i);
          com.position.setOffset(algorithm.getPosition(i));
          //graphic.translate(pos.x, pos.y);
          com.display();
          //graphic.popMatrix();
        }
      }
    }
    postdraw();
    behavior.display();
  }

}
