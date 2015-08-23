package dynamicLoad;

import processing.core.PApplet;

public class pappletLaunch {
  
  public jarLoad sketch;
  public PApplet Parent;
  public String Filename;
  public String PAppletname;
  public boolean Accessible;
  public PApplet papplet;
  private float xpos, ypos;
  
  public pappletLaunch(PApplet curParent,String curPAppletname) {
    xpos = ypos = 0f;
    pappletLaunch_init(curParent,curPAppletname);
  }
  
  public pappletLaunch(PApplet curParent,String curPAppletname, float fxpos, float fypos) {
    xpos = fxpos;
    ypos = fypos;
    pappletLaunch_init(curParent,curPAppletname);
  }
  
  private void pappletLaunch_init(PApplet curParent,String curPAppletname) {
    papplet = null;
    Accessible = false;
    Parent = curParent;
    PAppletname = curPAppletname;
    set();
  }
  
  public boolean set() {
    return set(PAppletname);
  }
  
  public boolean set(String curPAppletname) {
    return set(Parent,curPAppletname);
  }
  
  public boolean set(PApplet curParent, String curPAppletname) {
    String curFilename = curParent.dataPath(curPAppletname+".jar");
    try {
      sketch = new jarLoad(curFilename,curPAppletname);
      papplet = (PApplet) sketch.get().newInstance();
      papplet.init();
      papplet.setup();
      Accessible = true;
      Parent = curParent;
      Filename = curFilename;
      PAppletname = curPAppletname;
      return true;
    } catch (Exception e) {
      PApplet.println("[ERROR]: "+PAppletname+": "+e);
      PApplet.println("         "+e.getCause());
    }
    papplet = null;
    Accessible = false;
    return false;
  }
  
  public PApplet papplet() {
    if(Accessible) {
      if(papplet != null) {
        return papplet;
      }
    }
    return null;
  }
  
  public PApplet get() {
    return papplet();
  }
  
  public void draw(float fxpos, float fypos) {
    Parent.image(papplet.get(),fxpos, fypos);
  }
  
  public void draw() {
    draw(0,0);
  }
  
  public void display() {
    Parent.pushMatrix();
    Parent.translate(xpos,ypos);
    draw();
    Parent.popMatrix();
  }
  
  public void setPosition(float fxpos, float fypos) {
    xpos = fxpos;
    ypos = fypos;
  }

}
