package zuilib.manager;
import processing.core.PApplet;
import zuilib.core.window;
import zuilib.utils.vector;


public class WindowMoveTo extends animation {
  
  public vector destination;
  public int frames;
  public int max_frames;
  public vector delta_move;
  public boolean domove;
  
  public WindowMoveTo(String sname, window wwin,int iframes) {
    super(sname,wwin);
    frames = 0;
    max_frames = iframes;
    destination = new vector(wwin.position.getAbsolute());
    delta_move = new vector(0,0);
    domove = false;
  }
  
  public void setDest(vector vpos) {
    domove = true;
    frames = 0;
    destination = new vector(vpos);
    delta_move = vector.VecMul(vector.VecSub(destination,object.position.getAbsolute()),1/PApplet.parseFloat(max_frames));
  }
  
  public void update() {
    if(domove) {
      frames += 1;
      //println(VecSub(object.pos,destination).x+","+VecSub(object.pos,destination).y);
      object.position.set( vector.VecAdd(object.position.get(),delta_move) );
      if(frames == max_frames) { 
        domove = false;
        destination = new vector(object.position.getAbsolute());
      }
    }
  }
}
