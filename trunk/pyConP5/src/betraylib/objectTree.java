package betraylib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import processing.core.PApplet;

public class objectTree implements judasConsts {
  
  public String name;
  public String classname;
  public Object object;
  public int type;
  public objectTree[] list;
  public int length;

  public objectTree(String sname, Object oobject) {
    int itype = OTHER;
    objectTree_init(sname,oobject,itype);
    if(object.getClass().equals(Field.class)) itype = FIELD;
    if(object.getClass().equals(Method.class)) itype = METHOD;
  }
  
  public objectTree(String sname, Object oobject, int itype) {
    objectTree_init(sname,oobject,itype);
  }
  
  private void objectTree_init(String sname, Object oobject, int itype) {
    list = new objectTree[0];
    length = 0;
    name = sname;
    object = oobject;
    classname = object.getClass().getName();
    type = itype;
    if(type != FIELD && type != METHOD) type = OTHER;
  }
  
  public void add(objectTree newitem) {
    list = (objectTree[]) PApplet.append(list, newitem);
    length += 1;
  }
  
  public Object get() {
    return object;
  }
  
  public objectTree get(int i) {
    if(i < length) {
        return list[i];
      } else {
        PApplet.println("[WARNING]: getObject:  Requested object no. "+i+" returned null.");
        return null;
    }
  }
  
  public objectTree get(String sname) {
    for( int i = 0 ; i < length ; i += 1 ) {
      if(list[i].name.equals(sname)) {
        return list[i];
      }
    }
    PApplet.println("[WARNING]: getObject:  Requested object "+sname+" returned null.");
    return null;
  }
  
}
