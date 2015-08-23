package zuilib.manager;

import zuilib.core.manager;


public class DrawManager extends manager {

  public DrawManager(String sname, boolean benable) {
    super(sname,benable);
    Type = "draw";
  }

  public void draw() {
    if(enable) {
      useFunction("predraw");
      useFunction("draw");
      useFunction("postdraw");
    }
  }
  
  public void pre() {
    if(enable) {
      useFunction("pre");
    }
  }

  public void setup() {
    parent.getParent().registerDraw(this);
    parent.getParent().registerPre(this);
    publishFunction("draw");     /* for main drawing              */
    publishFunction("pre");      /* for background                */
    publishFunction("predraw");  /* for background decoration     */
    publishFunction("postdraw"); /* for moving objects like mouse */
  }

}
