package dynamicLoad;

import java.lang.reflect.Field;
import processing.core.PApplet;

/**
 * FieldLoad läd dynamisch Variablen.<br>
 * To load a Field use this code:<br>
 * <code>FieldLoad fl = new FieldLoad([debug level,]"name of the field",owner instance of the field);</code><br>
 * If the field doesnt exist this will print an error, but not stopping your program.<br>
 * If you want to get the value of the field use this code:<br>
 * <code>fl.get();</code><br><br>
 * HINT: paramters in [ ] are optional
 * @author arne.alder
 * @category reflection
 */

public class FieldLoad extends loaderObject {
  
  /**
   * Representing the loaded Field.
   */
  public Field Variable;
  
  /**
   * Creates a new Instance from FieldLoad.<br>
   * <li>Will search for a field in the given instance Parent.</li>
   * <li>Will checking automaticly.</li>
   * <li>Setting debug level to 0.</li>
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   */
  public FieldLoad(String sname, Object oparent) {
    super(sname,oparent);
    FieldLoad_init();
  }
  
  /**
   * Creates a new Instance from FieldLoad.<br>
   * <li>Will search for a field in the given instance Parent.</li>
   * <li>Will checking automaticly.</li>
   * @param debug_level Specify printed messages.
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   */
  public FieldLoad(int debug_level, String sname, Object oparent) {
    super(debug_level,sname,oparent);
    FieldLoad_init();
  }
  
  private void FieldLoad_init() {
    Variable = null;
    if(!check()) {
      PApplet.println("[ERROR]: Cannot load Variable "+Name+"!");
    } else {
      if (DEBUG >= 1) {
        PApplet.println("  Loaded Variable " + Name + " from "+Parent.toString()+".");
      }
  }
  }

  /**
   * Default call to check if a variable exists and to load this field.
   * @return True if variable could found an loaded.
   */
  public boolean check() {
    return check(Parent,Name);
  }
  
  /**
   * Check if a variable exists and load this field.
   * @param curParent Sets the new parent object.
   * @return True if variable could found an loaded.
   */
  public boolean check(Object curParent) {
    return check(curParent,Name);
  }
  
  /**
   * Check if a variable exists and load this field.
   * @param curName Sets the new name of the variable.
   * @return True if variable could found an loaded.
   */
  public boolean check(String curName) {
    return check(Parent,curName);
  }
  
  /**
   * Main call to check if a variable exists and to load this field.
   * @param curParent Given parent object.
   * @param curName Given variable name.
   * @return True if variable could found an loaded.
   */
  public boolean check(Object curParent, String curName) {
    Accessible = false;
    if(curParent == null) {
      PApplet.println("[ERROR]: No Parent.");
      return false;
    }
    if(curName == "") {
      PApplet.println("[ERROR]: No Name.");
      return false;
    }
    
    Field[] fields = curParent.getClass().getFields();
    for( int i = 0 ; i < fields.length ; i += 1 ) {
      if ((fields[i].getName()).equals(curName)) {
        if (DEBUG >= 2) {
          PApplet.println("  Checking Variable " + fields[i].getName()
              + " (Type " + fields[i].getType()+")");
        }
        return setField(curParent,curName);
      }
    }
    PApplet.println("[ERROR]: No Variable named "+curName+" found.");
    return false;
  }

  private boolean setField(Object curParent, String curName) {
    try {
        Variable = curParent.getClass().getField(curName);
        Variable.setAccessible(true);
      
        Accessible = true;
        Parent = curParent;
        Name = curName;
        return true;
      } catch(SecurityException e) {
        PApplet.println("[ERROR]: "+curName+": "+e);
      } catch(NoSuchFieldException e) {
        PApplet.println("[ERROR]: "+curName+": "+e);
    }
    return false;
  }
  
  /**
   * Returns the Value of the loaded Variable.
   * @return Uncasted Value of the Variable.
   */
  public Object getValue() {
    if(Accessible) {
      if(Variable != null) {
        try {
          Object result = null;
          result = Variable.get(Parent);
          return result;
        } catch (IllegalArgumentException e) {
          PApplet.println("[ERROR]: "+Variable.toString()+": "+e);
          PApplet.println("         "+e.getCause());
        } catch (IllegalAccessException e) {
          PApplet.println("[ERROR]: "+Name+": "+e);
        }
      }
    }
    PApplet.println("[WARNING]: "+Name+":  get: Returned null.");
    return null;
  }
  
  /**
   * Returns the Value of the loaded Variable.
   * @return Uncasted Value of the Variable.
   */
  public Object get() {
    return getValue();
  }
  
  /**
   * Returns the Value of the loaded Variable.
   * @return Uncasted Value of the Variable.
   */
  public Object value() {
    return getValue();
  }
  
  /**
   * Sets the Value of the loaded Variable to a new one.
   * @return True if value could changed.
   */
  public boolean setValue(Object newValue) {
    if(Accessible) {
      if(Variable != null) {
        try {
          Variable.set(Parent,newValue);
          return true;
        } catch (IllegalArgumentException e) {
          PApplet.println("[ERROR]: "+Variable.toString()+": "+e);
          PApplet.println("         "+e.getCause());
        } catch (IllegalAccessException e) {
          PApplet.println("[ERROR]: "+Name+": "+e);
        }
      }
    }
    return false;
  }
  
  /**
   * Sets the Value of the loaded Variable to a new one.
   * @return True if value could changed.
   */
  public boolean set(Object newValue) {
    return setValue(newValue);
  }
  
  /**
   * Sets the Value of the loaded Variable to a new one.
   * @return True if value could changed.
   */
  public boolean value(Object newValue) {
    return setValue(newValue);
  }

}
