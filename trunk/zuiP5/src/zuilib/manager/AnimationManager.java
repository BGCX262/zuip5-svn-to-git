package zuilib.manager;
import java.util.HashMap;

import processing.core.PApplet;
import zuilib.core.manager;


public class AnimationManager extends manager {

  public animation[] animations;
  public int index;
  private HashMap<String, Integer> animation_map = new HashMap<String, Integer>();

  public AnimationManager(String sname, boolean benable) {
    super(sname,benable);
    Type = "animation";
    animations = new animation[0];
    index = 0;
  }

  public int addAnimation(animation newa) {
    if(getZUI().DEBUG >= 5) PApplet.println("* Add Animation "+newa.Name+" ("+newa.getClass().getName()+")");
    animations = (animation[]) PApplet.append(animations, newa );
    animation_map.put(newa.Name, index);
    index += 1;
    return index-1;
  }

  public void pre() {
    if(enable) update();
  }
  
  public void predraw() {}
  public void draw() {}
  public void postdraw() {}

  public animation get(int i) {
    return getAnimation(i);
  }

  public animation get(String sname) {
    return getAnimation(sname);
  }

  public animation getAnimation(int i) {
    if( i < index ) {
      return animations[i];
    } 
    else {
      PApplet.println("[WARNING]: getAnimation:  Requested animation no. "+i+" returned null.");
      return null;
    }
  }

  public animation getAnimation(String sname) {
    Integer i = animation_map.get(sname);
    if(i != null) return animations[i];
    PApplet.println("[WARNING]: getAnimation:  Requested animation "+sname+" returned null.");
    return null;
  }

  public boolean setAnimation(int i,animation newa) {
    if( i < index ) {
      animation_map.remove(animations[i].Name);
      animations[i] = newa;
      animation_map.put(newa.Name, i);
      return true;
    } 
    else {
      if(getZUI().DEBUG >= 1) PApplet.println("[WARNING]: setAnimation:  Setting animation "+newa.Name+" on position "+i+" failed.");
      return false;
    }
  }

  public void setup() {
    this.link("draw");
  }

  public void update() {
    for( int i = 0 ; i < index ; i += 1 ) {
      animations[i].update();
    }
  }

}
