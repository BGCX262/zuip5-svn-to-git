package zuilib.core;

import processing.core.PApplet;
import zuilib.properties.FunctionController;

public class manager extends zuiAtomObject {
  
  public String Type;
  public String Name;
  public boolean enable;
  public String[] functions = new String[0];
  public Object[] paramclasses = new Object[0];
  public ZUI parent;
  public FunctionController automatic;
  public int id;
  
  public manager(String sname, boolean benable) {
    Type = "undefined";
    Name = sname;
    enable = benable;
    automatic = new FunctionController(this);
    id = -1;
  }
  
  private boolean checkType(String type) {
    if(!parent.containsType(type)) {
      PApplet.println("[CRITICAL ERROR]: Manager "+Name+" needs another manager loaded with type "+type+".");
      getPApplet().die("[CRITICAL ERROR]");
    }
    return true;
  }
  
  public int addTo(ZUI ui) {
    return ui.addManager(this);
  }
  
  public void install(ZUI curParent, int curId) {
    setParentObject(curParent);
    id = curId;
  }
  
  public manager getManager() {
    return this;
  }
  
  public manager getManagerByType(String type) {
    return parent.getManagerByType(type);
  }
  
  public PApplet getPApplet() {
    return parent.getPApplet();
  }
  
  public ZUI getParent() {
    return parent;
  }
  
  public String getType() {
    return Type;
  }
  
  public int id() {
    return id;
  }
  
  public ZUI getZUI() {
    return getParent();
  }
  
  public boolean isEnable() {
    return enable; 
  }
  
  public boolean link(String type) {
    checkType(type);
    manager man = parent.getManagerByType(type);
    man.setupFunctions(this);
    return parent.createLink(Name,type);
  }
  
  public void publishFunction(String newf) {
    publishFunction(newf,null);
  }
  
  @SuppressWarnings("unchecked")
  public void publishFunction(String newf,Class[] params) {
    functions = (String[]) PApplet.append(functions,newf);
    paramclasses = (Object[]) PApplet.append(paramclasses,params);
    
  }
  
  public void setEnable(boolean newenable) {
    enable = newenable;
  }
  
  public void setParentObject(ZUI newz) {
    parent = newz;
  }
  
  public void setup() {return;}
  
  private void setupFunctions(manager man) {
    if(parent.DEBUG >= 3) PApplet.println("* Setup Functions of type "+Type+" on "+man.Name);
    for( int i = 0 ; i < functions.length ; i += 1 ) {
      man.automatic.addFunction(functions[i],(Class[]) paramclasses[i]);
      man.automatic.setFunction(functions[i],functions[i]);
    }
  }

  public void useFunction(String func) {
    useFunction(func,new Object[] {} );
  }
  
  public void useFunction(String func,Object[] params) {
    int[] linked = parent.getLinked(Type);
    for( int i = 0 ; i < linked.length ; i += 1 ) {
      parent.get(linked[i]).automatic.useFunction(func,params);
    }
  }
  
  public void useFunctions(String[] funcs) {
    useFunctions(funcs,new Object[] {} );
  }
  
  public void useFunctions(String[] funcs,Object[] params) {
    int[] linked = parent.getLinked(Type);
    for( int i = 0 ; i < linked.length ; i += 1 ) {
      for( int f = 0 ; f < funcs.length ; f += 1) {
        parent.get(linked[i]).automatic.useFunction(funcs[f],params);
      }
    }
  }
  
}
