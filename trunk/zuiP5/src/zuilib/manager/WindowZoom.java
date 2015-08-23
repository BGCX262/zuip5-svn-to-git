package zuilib.manager;
import processing.core.PApplet;
import zuilib.core.window;
import zuilib.utils.vector;


public class WindowZoom extends animation {
  
  public vector destination;
  public int frames;
  public int max_frames;
  public vector delta_move;
  public boolean zoomin;
  public boolean zoomout;
  public boolean zoomedin;
  public float zoom;
  public float delta_zoom;
  public vector prev_pos;
  public float prev_zoom;
  public WindowMoveTo move;
  
  
  public WindowZoom(String sname, window wwin,int iframes) {
    super(sname,wwin);
    frames = 0;
    max_frames = iframes;
    destination = new vector(wwin.position.getAbsolute());
    delta_move = new vector(0,0);
    zoomin = false;
    zoomout = false;
    zoomedin = false;
    zoom = 0;
    delta_zoom = 0;
    prev_zoom = 0;
    prev_pos = new vector(0,0);
    move = new WindowMoveTo(Name+"_Move",wwin,iframes);
  }
  
  public void autoZoom(vector vpos, float fzoom) {
    if(zoomedin) {ZoomOut();}
      else {ZoomIn(vpos,fzoom);}
  }
  
  public void update() {
    move.update();
    if(zoomin || zoomout) {
      frames += 1;
      
      object.scale.set( object.scale.get() + delta_zoom);
      
      if(frames == max_frames) {
        object.scale.set(zoom);
        zoomedin = !zoomout;
        zoomin = false;
        zoomout = false;
        frames = 0;
      }
    }
  }
  
  public void ZoomIn(vector vpos, float fzoom) {
    destination = new vector(vpos);
    zoom = fzoom;
    prev_pos = new vector(object.position.getAbsolute());
    prev_zoom = object.scale.get();
    zoomin = true;
    delta_zoom = (zoom-object.scale.get())/PApplet.parseFloat(max_frames);
    move.setDest( vector.VecSub(object.position.getAbsolute(),vector.VecAdd(object.position.getAbsolute(),vector.VecMul(destination,zoom))) );
  }
  
  public boolean ZoomOut() {
    if(zoomedin) {
      zoom = prev_zoom;
      destination = new vector(prev_pos);
      zoomout = true;
      delta_zoom = (zoom-object.scale.get())/PApplet.parseFloat(max_frames);
      move.setDest( prev_pos );
    }
    return zoomedin;
  }
  
}
