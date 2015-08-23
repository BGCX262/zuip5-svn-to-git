package betraylib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import processing.core.PApplet;

public class Judas implements judasConsts {

  public Object parent;
  public int MAX_HOOPS = 3;
  
  public Judas(Object curParent) {
    parent = curParent;
  }
  
  public objectTree getAll() {
    return getAll(parent.getClass().getName(),parent,0,true);
  }
  
  public objectTree getAll(Object curParent) {
    return getAll(curParent.getClass().getName(),curParent,0,true);
  }
  
  public objectTree getAll(boolean usemethods) {
    return getAll(parent.getClass().getName(),parent,0,usemethods);
  }
  
  public objectTree getAll(Object curParent,boolean usemethods) {
    return getAll(curParent.getClass().getName(),curParent,0,usemethods);
  }
  
  public objectTree getAll(String sname, Object curParent,int hoop,boolean usemethods) {
    objectTree result = new objectTree(sname,curParent);
    Field[] fields = getVariables(curParent);
    for(int i = 0 ; i < fields.length ; i += 1) {
      /*
      if(fields[i].getClass().equals(String.class) ||
         fields[i].getClass().equals(int.class) ||
         fields[i].getClass().equals(Integer.class) ||
         fields[i].getClass().equals(Float.class) ||
         fields[i].getClass().equals(float.class) ||
         fields[i].getClass().equals(double.class) ||
         fields[i].getClass().equals(boolean.class) ||
         fields[i].getClass().equals(byte.class) ||
         fields[i].getClass().equals(char.class) ||
         fields[i].getName().equals("POSITIVE_INFINITY") ||
         fields[i].getName().equals("NEGATIVE_INFINITY") ||
         fields[i].getName().equals("NaN") ||
         fields[i].getName().equals("MIN_VALUE") ||
         fields[i].getName().equals("MAX_VALUE") ||
         fields[i].getName().equals("MAX_NORMAL") ||
         fields[i].getName().equals("MIN_NORMAL") ||
         fields[i].getName().equals("TYPE") ||
         fields[i].getName().equals("SIZE") ||
         fields[i].getName().equals(fields[i].getClass().getName())) {
           if(!fields[i].getName().equals("POSITIVE_INFINITY") &&
              !fields[i].getName().equals("NaN") &&
              !fields[i].getName().equals("MAX_VALUE") &&
              !fields[i].getName().equals("MIN_VALUE") &&
              !fields[i].getName().equals("MAX_NORMAL") &&
              !fields[i].getName().equals("MIN_NORMAL") &&
              !fields[i].getName().equals("TYPE") &&
              !fields[i].getName().equals("SIZE") &&
              !fields[i].getName().equals("NEGATIVE_INFINITY")) {
             PApplet.println("[ ] "+fields[i].getName()+" ("+curParent.getClass().getName()+")");
             result.add( new objectTree( fields[i].getName(),fields[i],FIELD ) );
           }
        } else {
          PApplet.println("[R] "+fields[i].getName()+" ("+curParent.getClass().getName()+")");
          try {
            result.add( getAll( fields[i].get(curParent) ) );
          } catch (Exception e) {}
      }*/
      //PApplet.println(fields[i].getName()+" "+fields[i].getModifiers());
      if(/*fields[i].getModifiers() == 1*/true) {
        try {
          if(fields[i].getType().equals(String.class) ||
              fields[i].getType().equals(int.class) ||
              fields[i].getType().equals(Integer.class) ||
              fields[i].getType().equals(Float.class) ||
              fields[i].getType().equals(float.class) ||
              fields[i].getType().equals(double.class) ||
              fields[i].getType().equals(boolean.class) ||
              fields[i].getType().equals(byte.class) ||
              fields[i].getType().equals(char.class) ||
              hoop >= MAX_HOOPS ||
              fields[i].getType().equals(Long.class)) {
          PApplet.println("[ ] "+fields[i].getName()+" ("+curParent.getClass().getName()+")");
          result.add( new objectTree( fields[i].getName(),fields[i],FIELD ) );
          } else {
            PApplet.println("[R] "+fields[i].getName()+" ("+curParent.getClass().getName()+")");
            result.add( getAll( fields[i].getName(),fields[i].get(curParent), hoop+1, usemethods ) );
          }
        } catch (Exception e) {
          PApplet.println(e);
          PApplet.println("[ ] "+fields[i].getName()+" ("+curParent.getClass().getName()+")");
          result.add( new objectTree( fields[i].getName(),fields[i],FIELD ) );
        }
      }
      
    }
    if(usemethods) {
      Method[] methods = getFunctions(curParent);
      for(int i = 0 ; i < methods.length ; i += 1) {
        result.add( new objectTree( methods[i].getName(),methods[i],METHOD ) );
      }
    }
    return result;
  }
  
  public String[] getAllsName() {
    return getAllsName(parent);
  }
  
  public objectTree get() {
    return get(parent);
  }
  
  public objectTree get(Object curParent) {
    objectTree result = new objectTree(curParent.getClass().getName(),curParent);
    Field[] fields = getVariables(curParent);
    Method[] methods = getFunctions(curParent);
    for(int i = 0 ; i < fields.length ; i += 1) {
      result.add( new objectTree( fields[i].getName(),fields[i],FIELD ) );
    }
    for(int i = 0 ; i < methods.length ; i += 1) {
      result.add( new objectTree( methods[i].getName(),methods[i],METHOD ) );
    }
    return result;
  }
  
  public String[] getAllsName(Object curParent) {
    Field[] fields = getVariables(curParent);
    String[] result = new String[0];
    result = (String[]) PApplet.concat(result, getFunctionsName(curParent));
    result = (String[]) PApplet.concat(result, getVariablesName(curParent));
    for(int i = 0 ; i < fields.length ; i += 1) {
      try {
        result = (String[]) PApplet.concat(result, getAllsName( fields[i].get(curParent) ));
      } catch (Exception e) {}
    }
    return result;
  }
  
  public String[] getAllFunctionsName() {
    return getAllFunctionsName(parent);
  }
  
  public String[] getAllFunctionsName(Object curParent) {
    Field[] fields = getVariables(curParent);
    String[] result = new String[0];
    result = (String[]) PApplet.concat(result, getFunctionsName(curParent));
    for(int i = 0 ; i < fields.length ; i += 1) {
      try {
        result = (String[]) PApplet.concat(result, getAllFunctionsName( fields[i].get(curParent) ));
      } catch (Exception e) {}
    }
    return result;
  }
  
  public String[] getAllVariablesName() {
    return getAllVariablesName(parent);
  }
  
  public String[] getAllVariablesName(Object curParent) {
    Field[] fields = getVariables(curParent);
    String[] result = new String[0];
    result = (String[]) PApplet.concat(result, getVariablesName(curParent));
    for(int i = 0 ; i < fields.length ; i += 1) {
      try {
        result = (String[]) PApplet.concat(result, getAllVariablesName( fields[i].get(curParent) ));
      } catch (Exception e) {}
    }
    return result;
  }
  
  public String[] getFunctionsName(Object curParent) {
    Method[] methods = getFunctions(curParent);
    String[] result = new String[0];
    for(int i = 0 ; i < methods.length ; i += 1) {
      result = (String[]) PApplet.append(result, methods[i].getName());
    }
    return result;
  }
  
  public Method[] getFunctions(Object curParent) {
    try {
     return curParent.getClass().getMethods();
    } catch(Exception e) { return new Method[0];}
  }
  
  public String[] getVariablesName(Object curParent) {
    Field[] fields = getVariables(curParent);
    String[] result = new String[0];
    for(int i = 0 ; i < fields.length ; i += 1) {
      result = (String[]) PApplet.append(result, fields[i].getName());
    }
    return result;
  }
  
  public Field[] getVariables(Object curParent) {
    try {
      return curParent.getClass().getFields();
    } catch(Exception e) { return new Field[0];}
  }
}
