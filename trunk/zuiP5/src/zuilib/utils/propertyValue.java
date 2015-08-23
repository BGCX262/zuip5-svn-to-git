package zuilib.utils;

import dynamicLoad.FunctionLoad;

public class propertyValue {
  
  public String name;
  public FunctionLoad getter;
  public FunctionLoad setter;
  public Object OwnerObject;
  public Object Parent;
  @SuppressWarnings("unchecked")
  public Class propertyType;
  
  public propertyValue(String sname) {
    name = sname;
  }
  
  public void setup(Object curParent) {
    Parent = curParent;
  }
  
  public void setup(Object curOwnerObject,Object curParent) {
    Parent = curParent;
    OwnerObject = curOwnerObject;
  }
  
  public void setFunctionOwner(Object curParent) {
    Parent = curParent;
  }
  
  @SuppressWarnings("unchecked")
  public void install(String sgetter, Class ctype, String ssetter) {
    propertyType = ctype;
    if(OwnerObject == null) {
      getter = (sgetter.equals(""))? new FunctionLoad(sgetter,Parent,false) : new FunctionLoad(sgetter,Parent);
      setter = (ssetter.equals(""))? new FunctionLoad(ssetter,Parent,new Class[] {ctype},false) : new FunctionLoad(ssetter,Parent,new Class[] {ctype}); //oi
      return;
    }
    getter = new FunctionLoad(sgetter,Parent,false);
    if(!sgetter.equals("")) getter.check(OwnerObject);
    setter = new FunctionLoad(ssetter,Parent,new Class[] {ctype},false);
    if(!ssetter.equals("")) setter.check(OwnerObject);
  }
  
  public Object get() {
    if(getter != null) return getter.invoke();
    return null;
  }
  
  public boolean set(Object[] params) {
    if(setter == null) return false;
    setter.invoke(params);
    return true;
  }
  
  public boolean set(Object param) {
    if(setter == null) return false;
    setter.invoke( new Object[] {param} );
    return true;
  }

}
