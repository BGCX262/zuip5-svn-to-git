package zuilib.components;

import dynamicLoad.FunctionLoad;
import zuilib.core.component;
import zuilib.properties.FunctionController;
import zuilib.utils.vector;

public class interactivecomponent extends component {
  
  public FunctionController iaObj;
  
  public interactivecomponent(String sname, vector start_pos) {
    super(sname,start_pos);
  }
  
  public interactivecomponent(String sname, float fx, float fy) {
    super(sname,fx,fy);
  } 
  
  public int addFunction(String newonfunc) {
    return iaObj.addFunction(newonfunc);
  }
  
  public boolean changeFunction(int i,String newonfunc) {
    return iaObj.changeFunction(i,newonfunc);
  }
  
  public FunctionLoad getFunction(String onfunc) {
    return iaObj.getFunction(onfunc);
  } 
  
  public int getFunctionIndex(String onfunc) {
    return iaObj.getFunctionIndex(onfunc);
  }
  
  public void setup() {
    super.setup();
    iaObj = new FunctionController(this,getPApplet());
    iaObj.setup(this);
  }
  
  public void setFunctionOwner(Object curOwner) {
    iaObj.setFunctionOwner(curOwner);
  }
  
  public boolean isSetFunction(String onfunc) {
    return iaObj.isSetFunction(onfunc);
  }
  
  public boolean setFunction(String onfunc, String newm) {
    return iaObj.setFunction(onfunc,newm);
  }
  
  public void useFunction(String onfunc) {
    iaObj.useFunction(onfunc);
  }
}
