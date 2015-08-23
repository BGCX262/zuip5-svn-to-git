package zuilib.utils;

import dynamicLoad.FunctionLoad;
import processing.core.PApplet;
import zuilib.core.zuiAtomObject;

public class FunctionControler {
  
  public int index = 0;
  public FunctionLoad[] functions = new FunctionLoad[0];
  public Object[] ParamClasses = { };
  public String[] onFunctions = { };
  public Object OwnerObject;
  public Object Parent;
  
  public FunctionControler(Object curParent) {
    FunctionControler_init(curParent,curParent);
  }
  
  public FunctionControler(Object curOwnerObject,Object curParent) {
    FunctionControler_init(curOwnerObject,curParent);
  }
  
  public FunctionControler() {
    FunctionControler_init();
  }

  private void FunctionControler_init(Object curOwnerObject,Object curParent) {
    setup(curOwnerObject,curParent);
    FunctionControler_init();
  }
  
  private void FunctionControler_init() {
    for( int i = 0 ; i < index ; i += 1 ) {
      functions[i] = null;
    }
  }
  
  public void setup(Object curParent) {
    setup(curParent,curParent);
  }
  
  public void setup(Object curOwnerObject,Object curParent) {
    Parent = curParent;
    OwnerObject = curOwnerObject;
  }
  
  public void setFunctionOwner(Object curParent) {
    Parent = curParent;
  }
  
  public int addFunction(String newonfunc) {
    return addFunction(newonfunc,null);
  }
  
  @SuppressWarnings("unchecked")
  public int addFunction(String newonfunc,Class[] params) {
    onFunctions = (String[]) PApplet.append(onFunctions,newonfunc);
    functions = (FunctionLoad[]) PApplet.append(functions,null);
    ParamClasses = (Object[]) PApplet.append(ParamClasses,params);
    index += 1;
    return index-1;
  }
  
  public boolean changeFunction(int i,String newonfunc) {
    return changeFunction(i,newonfunc,null);
  } 
  
  @SuppressWarnings("unchecked")
  public boolean changeFunction(int i,String newonfunc,Class[] params) {
    if( i < index ) {
        onFunctions[i] = newonfunc;
        ParamClasses[i] = params;
        return true;
      } else {
        return false;
    }
  } 
  
  public FunctionLoad getFunction(String onfunc) {
    int i = getFunctionIndex(onfunc);
    if(i == -1) {
      PApplet.println("[WARNING]: getFunction:  Trying to get function "+onfunc+" failed.");
      return null;
    }
    return functions[i];
  }
  
  public int getFunctionIndex(String onfunc) {
    for( int i = 0 ; i < index ; i += 1 ) {
      if(onFunctions[i] == onfunc) {
        return i;
      }
    }
    try {
      if (((zuiAtomObject) Parent).getZUI().DEBUG >= 1) {
        PApplet.println("[WARNING]: getFunctionIndex:  Can't find requested function "+onfunc+".");
      }
    } catch (Exception e) {
    }
    return -1;
  }
  
  public boolean isSetFunction(String onfunc) {
    int i = getFunctionIndex(onfunc);
    if(i == -1) {return false;}
    return (functions[i] != null);
  }
  
  public boolean setFunction(String onfunc, String newm) {
    int i = getFunctionIndex(onfunc);
    if(i == -1) {
      String s = "";
      for( i = 0 ; i < index ; i += 1 ) {
        if(s != "") {s += ", ";}
        s += onFunctions[i];
      }
      PApplet.println("[ERROR]: Requested Function '"+onfunc+"' was not added.");
      PApplet.println("         Existing Functions: "+s);
      return false;
    }
    int debug_level = 0;
    try {
      debug_level  = ((zuiAtomObject) Parent).getZUI().DEBUG;
      debug_level -= 5;
    } catch (Exception e) {
      debug_level  = 0;
    }
    //PApplet.println(ParamClasses[i]);
    if(ParamClasses[i] == null) {
        functions[i] = new FunctionLoad(debug_level,newm,Parent,false);
        functions[i].check(OwnerObject);
      } else {
        //PApplet.println((Class[]) ParamClasses[i]);
        functions[i] = new FunctionLoad(debug_level,newm,Parent,(Class[]) ParamClasses[i]);
    }
    return true;
  }
  
  public void useFunction(String onfunc) {
    int i = getFunctionIndex(onfunc);
    if(i == -1) {return;}
    if(functions[i] != null) {functions[i].invoke();}
  }
  
  public void useFunction(String onfunc,Object[] params) {
    int i = getFunctionIndex(onfunc);
    if(i == -1) {return;}
    if(functions[i] != null) {functions[i].invoke(params);}
  }
}
