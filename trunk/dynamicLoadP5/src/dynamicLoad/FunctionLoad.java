package dynamicLoad;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlException;

import processing.core.PApplet;

/**
 * If you wont to call a method dynamicly by calling method name just use this class.<br>
 * To load a Method use this code:<br>
 * <code>FunctionLoad fl = new FunctionLoad([debug level,]"name of the function",owner instance of the method);</code><br>
 * If the method doesnt exist this will print an error, but not stopping your program.<br>
 * If you left parameters the class will search for method with no, the parent class or the parent super class as parameter.<br>
 * You can change the one - parameter - class - list by using method setParameterObject.<br><br>
 * If you want to call the method use this code:<br>
 * <code>fl.invoke([list of parameters]);</code><br><br>
 * HINT: paramters in [ ] are optional
 * @author arne.alder
 * @category reflection
 */
public class FunctionLoad extends loaderObject {
  
  /**
   * Representing the loaded Method.
   */
  public Method Function;
  /**
   * Used to find the right method with the right parameters.<br>
   * Saves the parent class und parent super class.
   */
  @SuppressWarnings("unchecked")
  public Class[] AcceptClassList;
  public Object AcceptObject;
  /**
   * Saves the parameter classes used to call the right method.
   */
  @SuppressWarnings("unchecked")
  public Class[] ParamClasses;
  
  /**
   * Creates a new Instance from FunctionLoad.<br>
   * <li>Will search for a method with none or parent class or parent super class as parameter.</li>
   * <li>Will checking automaticly.</li>
   * <li>Setting debug level to 0.</li>
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   */
  public FunctionLoad(String sname, Object oparent) {
    super(sname,oparent);
    FunctionLoad_init(null,true);
  }
  
  /**
   * Creates a new Instance from FunctionLoad.<br>
   * <li>Will search for a method with none or parent class or parent super class as parameter.</li>
   * <li>Will checking automaticly.</li>
   * @param debug_level Specify printed messages.
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   */
  public FunctionLoad(int debug_level, String sname, Object oparent) {
    super(debug_level,sname,oparent);
    FunctionLoad_init(null,true);
  }
  
  /**
   * Creates a new Instance from FunctionLoad.<br>
   * <li>Will checking automaticly.</li>
   * <li>Setting debug level to 0.</li>
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   * @param params The parameters of the searched method.
   */
  @SuppressWarnings("unchecked")
  public FunctionLoad(String sname, Object oparent, Class[] params) {
    super(sname,oparent);
    FunctionLoad_init(params,true);
  }
  
  /**
   * Creates a new Instance from FunctionLoad.<br>
   * <li>Will checking automaticly.</li>
   * @param debug_level Specify printed messages.
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   * @param params The parameters of the searched method.
   */
  @SuppressWarnings("unchecked")
  public FunctionLoad(int debug_level, String sname, Object oparent, Class[] params) {
    super(debug_level,sname,oparent);
    FunctionLoad_init(params,true);
  }
  
  /**
   * Creates a new Instance from FunctionLoad.<br>
   * <li>Will search for a method with none or parent class or parent super class as parameter.</li>
   * <li>Setting debug level to 0.</li>
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   * @param checking If false you have to use method check by yourself.
   */
  public FunctionLoad(String sname, Object oparent,boolean checking) {
    super(sname,oparent);
    FunctionLoad_init(null,checking);
  }
  
  /**
   * Creates a new Instance from FunctionLoad.<br>
   * <li>Will search for a method with none or parent class or parent super class as parameter.</li>
   * @param debug_level Specify printed messages.
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   * @param checking If false you have to use method check by yourself.
   */
  public FunctionLoad(int debug_level, String sname, Object oparent,boolean checking) {
    super(debug_level,sname,oparent);
    FunctionLoad_init(null,checking);
  }
  
  /**
   * Creates a new Instance from FunctionLoad.<br>
   * <li>Setting debug level to 0.</li>
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   * @param params The parameters of the searched method.
   * @param checking If false you have to use method check by yourself.
   */
  @SuppressWarnings("unchecked")
  public FunctionLoad(String sname, Object oparent, Class[] params,boolean checking) {
    super(sname,oparent);
    FunctionLoad_init(params,checking);
  }
  
  /**
   * Creates a new Instance from FunctionLoad.<br>
   * @param debug_level Specify printed messages.
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   * @param params The parameters of the searched method.
   * @param checking If false you have to use method check by yourself.
   */
  @SuppressWarnings("unchecked")
  public FunctionLoad(int debug_level, String sname, Object oparent, Class[] params,boolean checking) {
    super(debug_level,sname,oparent);
    FunctionLoad_init(params,checking);
  }
  
  /**
   * Changes the requesting parameter list.<br>
   * Will ingnored if ParamClasses is set.
   * @param paramobj Instance object from witch the method can get its parameters.
   */
  public void setParameterObject(Object paramobj) {
    AcceptObject = paramobj;
    AcceptClassList = new Class[] {paramobj.getClass(),paramobj.getClass().getSuperclass()};
  }
  
  /**
   * Default call to check if a function exists and to load this method.
   * @return True if function could found an loaded.
   */
  public boolean check() {
    boolean bool = check(Parent,Name,AcceptClassList);
    if(!bool) {
      PApplet.println("[ERROR]: Cannot load Function "+Name+"!");
    } else {
      if (DEBUG >= 1) {
        PApplet.println("  Loaded Function " + Name + " from "+Parent.toString()+".");
      }
    }
    return bool;
  }
  
  /**
   * Checks if a function exists and to load this method.<br>
   * Use this if you want load a function with one or none parameter an setting before a new list of all accepted classes for this one parameter.
   * @param curAcceptClassList The new list of all accepted classes for this one parameter.
   * @return True if function could found an loaded.
   */
  @SuppressWarnings("unchecked")
  public boolean check(Class[] curAcceptClassList) {
    return check(Parent,Name,curAcceptClassList);
  }
  
  /**
   * Checks if a function exists and to load this method.<br>
   * Use this if you want load a function with one or none parameter an setting before a new list of all accepted classes for this one parameter.
   * @param paramobj The new list will generated out of the classes of the given object.
   * @return True if function could found an loaded.
   */
  public boolean check(Object paramobj) {
    setParameterObject(paramobj);
    return check();
  }
  
  /**
   * Checks if a function exists and to load this method.<br>
   * Use this if you want load a function with one or none parameter and specifying a new function.
   * @param curParent New parent object.
   * @param curName New function name.
   * @return True if function could found an loaded.
   */
  public boolean check(String curName, Object curParent) {
    setParameterObject(curParent);
    if(check(curParent,Name,AcceptClassList)) {
      Parent = curParent;
      Name = curName;
      return true;
    }
    return false;
  }
  
  /**
   * Checks if a function exists and to load this method.<br>
   * Use this if you want load a function with one or none parameter and specifying a new function by name.
   * @param curName New function name.
   * @return True if function could found an loaded.
   */
  public boolean check(String curName) {
    return check(Parent,curName,AcceptClassList);
  }
  
  /**
   * Main call to check if a function exists and to load this method.
   * @param curParent Given parent object.
   * @param curName Given function name.
   * @param curAcceptClassList Given list of all accepted classes for this one parameter.
   * @return True if function could found an loaded.
   */
  @SuppressWarnings({ "unchecked" })
  public boolean check(Object curParent, String curName, Class[] curAcceptClassList) {
    Accessible = false;
    if(curParent == null) {
      PApplet.println("[ERROR]: No Parent.");
      return false;
    }
    if(curAcceptClassList == null) {
      PApplet.println("[ERROR]: No ClassList.");
      return false;
    }
    if(curName == "") {
      PApplet.println("[ERROR]: No Name.");
      return false;
    }
    
    Method[] methods = curParent.getClass().getMethods();
    for( int i = 0 ; i < methods.length ; i += 1 ) {
      if ((methods[i].getName()).equals(curName)) {
        if (DEBUG >= 2) {
          PApplet.print("  Checking Function " + methods[i].getName()
              + " (params " + methods[i].getParameterTypes().length
              + ") (given parems");
          if (ParamClasses == null) {
            PApplet.print(" null");
          } else {
            for (int c = 0; c < ParamClasses.length; c += 1) {
              PApplet.print(" " + ParamClasses[c].getName());
            }
          }
          PApplet.println(")");
        }
        if(ParamClasses != null) {
            Class[] params = methods[i].getParameterTypes();
            if(ParamClasses.length == params.length) {
              for( int j = 0 ; j < ParamClasses.length ; j += 1 ) {
                if(!params[j].equals(ParamClasses[j])) {break;}
                if(j+1 == ParamClasses.length) {
                  return set(curParent,curName,ParamClasses);
                }
              }
            }
          } else if(methods[i].getParameterTypes().length == 0) {
            return set(curParent,curName,new Class[0]);
          } else if(methods[i].getParameterTypes().length == 1) {
            for( int j = 0 ; j < curAcceptClassList.length ; j += 1 ) {
              if(methods[i].getParameterTypes()[0] == curAcceptClassList[j]) {
                return set(curParent,curName,methods[i].getParameterTypes()[0]);
              }
            }
          } else {
            PApplet.println("[ERROR]: "+this.getClass().getName()+": Method requires too much Parameters");
            return false;
        }
      }
    }
    PApplet.println("[ERROR]: No Method named "+curName+" found.");
    return false;
  }
  
  @SuppressWarnings("unchecked")
  private void FunctionLoad_init(Class[] params,boolean checking) {
    ParamClasses = params;
    Function = null;
    setParameterObject(Parent);
    if(checking) {check();}
  }
  
  /**
   * Call the loaded function if it was loadable.<br>
   * The function will get none or the parent object as parameter.
   */
  public void invoke() {
    if(Accessible) {
      if(Function != null) {
        int len = Function.getParameterTypes().length;
        if(len == 0) {
          invoke(new Object[] {});
        } else if(len == 1) {
          invoke(new Object[] {AcceptObject});
        }
      }
      
    }
  }
  
  /**
   * Call the loaded function if it was loadable.<br>
   * The function will get the given list of parameters.
   * @param params The list of the parameters.
   */
  public void invoke(Object[] params) {
    if(Accessible) {
      if(Function != null) {
        try {
            Function.invoke(Parent,params);
          } catch(InvocationTargetException e) {
            PApplet.println("[ERROR]: "+Function.toString()+": "+e);
            PApplet.print("         ");
            e.getTargetException().printStackTrace();
            PApplet.println("         Maybe a Bug in your code? :P");
          } catch(IllegalAccessException e) {
            PApplet.println("[ERROR]: "+Name+": "+e);
        }
      }
      
    }
  }
  
  @SuppressWarnings("unchecked")
  private boolean set(Object curParent, String curName, Class ParameterClass) {
    Class[] args = (ParameterClass != null) ? new Class[] {ParameterClass} : new Class[] {};
    return set(curParent,curName,args);
  }
  
  @SuppressWarnings("unchecked")
  private boolean set(Object curParent, String curName, Class[] ParameterClasses) {
    Class[] args = (ParameterClasses == null) ? new Class[] {} : ParameterClasses;
    try {
      Function = curParent.getClass().getMethod(curName,args);
      Function.setAccessible(true);
      
      Accessible = true;
      Parent = curParent;
      Name = curName;
      return true;
      } catch(AccessControlException e) {
        PApplet.println("[WARNING]: "+curName+": Access denied for loading Method.");
        return saveSet(curParent,curName,ParameterClasses);
      } catch(SecurityException e) {
        PApplet.println("[ERROR]: "+curName+": "+e);
      } catch(NoSuchMethodException e) {
        PApplet.println("[ERROR]: "+curName+": "+e);
    }
    return false;
  }
  
  @SuppressWarnings("unchecked")
  private boolean saveSet(Object curParent, String curName, Class[] ParameterClasses) {
    Class[] args = (ParameterClasses == null) ? new Class[] {} : ParameterClasses;
    try {
      Function = curParent.getClass().getMethod(curName,args);
      //Function.setAccessible(true);
      PApplet.println("           Loaded Method savely.");
      Accessible = true;
      Parent = curParent;
      Name = curName;
      return true;
      } catch(SecurityException e) {
        PApplet.println("[ERROR]: "+curName+": "+e);
      } catch(NoSuchMethodException e) {
        PApplet.println("[ERROR]: "+curName+": Maybe the Method is not a declared one.");
        PApplet.println("         "+e);
    }
    return false;
  }
  
}
