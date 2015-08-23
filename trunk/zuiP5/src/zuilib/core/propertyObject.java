package zuilib.core;

import java.lang.reflect.Field;
import processing.core.PApplet;
import zuilib.properties.PropertyController;
import zuilib.utils.propertyValue;

public class propertyObject extends zuiAtomObject {
  
  public String propertyName;
  public Object propertyOwner;
  public PropertyController propertyController;
  public int index;
  public boolean allowRegister = true;
  public propertyValue[] propertyValues = new propertyValue[0];
  
  public propertyObject() {
    propertyName = "Property"+Integer.toHexString(hashCode());
    propertyOwner = null;
    propertyController = null;
    index = 0;
  }
  
  public void setup(zuiAtomObject curParent) {
    setParentObject(curParent);
    String thisname = getThisName();
    if(parentObject != null) {
      if(!thisname.equals("")) {
        Field[] similars = new Field[0];
        Field[] fields = parentObject.getClass().getFields();
        Field highest = null;
        for(int i = 0; i < fields.length ; i += 1) {
          if(fields[i].getName().equals(thisname)) {
            if(fields[i].getType().isAssignableFrom( this.getClass() )) {  // if field type is a subclass from this class
                if(similars.length == 0) highest = fields[i];
                similars = (Field[]) PApplet.append(similars,fields[i]);
              } else if (this.getClass().isAssignableFrom( fields[i].getType() )) {  // if this class is a subclass from field type
                similars = (Field[]) PApplet.append(similars,fields[i]);
                if(similars.length == 1) {
                    highest = fields[i];
                  } else if(highest != null) {
                    if(highest.getType().isAssignableFrom( fields[i].getType() )) {
                      highest = fields[i];
                    }
                }
            }
          }
        }
        Object main = null;
        try {
          main = highest.get(parentObject);
        } catch(Exception e) {
          PApplet.println("[ERROR]: Failure in propertyObject setup by getting highest class instance:");
          e.printStackTrace();
        }
        if(main != null) {
          for(int i = 0 ; i < similars.length ; i += 1) {
            try {
              similars[i].set(parentObject, similars[i].getType().cast(main));
            } catch (Exception e) {
              PApplet.println("[ERROR]: Failure in propertyObject setup by ereasing lower class instance:");
              e.printStackTrace();
            }
          }
          if(zuiObject.class.isAssignableFrom( parentObject.getClass() )) {
            if(propertyObject.class.isAssignableFrom( main.getClass() )) {
              propertyController = ((zuiObject) parentObject).properties;
              propertyController.addProperty((propertyObject) main);
            }
          }
        }
      }
    }
  }
  
  public void registerPre(String onfunc, String newm) {
    registerPre(this,onfunc,newm);
  }
  
  public void registerPost(String onfunc, String newm) {
    registerPost(this,onfunc,newm);
  }
  
  public void unRegisterPre(String onfunc, String funcname) {
    unRegisterPre(this,onfunc,funcname);
  }
  
  public void unRegisterPost(String onfunc, String funcname) {
    unRegisterPost(this,onfunc,funcname);
  }
  
  public void registerPre(Object parent, String onfunc,String newm) {
    if(propertyController != null && allowRegister)
      propertyController.registerPre(parent,onfunc,newm);
  }
  
  public void registerPost(Object parent, String onfunc,String newm) {
    if(propertyController != null && allowRegister)
      propertyController.registerPost(parent,onfunc,newm);
  }
  
  public void unRegisterPre(Object parent, String onfunc,String funcname) {
    if(propertyController != null)
      propertyController.unRegisterPre(parent,onfunc,funcname);
  }
  
  public void unRegisterPost(Object parent, String onfunc,String funcname) {
    if(propertyController != null)
      propertyController.unRegisterPost(parent,onfunc,funcname);
  }
  
  public void setPreMarker(String onfunc, int marker) {
    if(propertyController != null)
      propertyController.setPreMarker(onfunc, marker);
  }
  
  public void setPostMarker(String onfunc, int marker) {
    if(propertyController != null)
      propertyController.setPostMarker(onfunc, marker);
  }
  
  public void setRegister(boolean newb) {
    allowRegister = newb;
  }
  
  public void setPropertyOwner(Object curObject) {
    propertyOwner = curObject;
  }
  
  public String getPropertyName() {
    return propertyName;
  }
  
  @SuppressWarnings("unchecked")
  public int addPropertyValue(String svaluename, String sgetter, Class ctype, String ssetter) {
    return addPropertyValue( new propertyValue(svaluename), sgetter,ctype , ssetter);
  }
  
  @SuppressWarnings("unchecked")
  public int addPropertyValue( propertyValue newpv, String sgetter, Class ctype, String ssetter) {
    propertyValues = (propertyValue[]) PApplet.append(propertyValues, newpv);
    newpv.setup(this,propertyOwner);
    newpv.install(sgetter, ctype, ssetter);
    index += 1;
    return index-1;
  }
  
  public int addPropertyValue( propertyValue newpv) {
    propertyValues = (propertyValue[]) PApplet.append(propertyValues, newpv);
    index += 1;
    return index-1;
  }
  
  public propertyValue getPropertyValue(int i) {
    if(i < index) {
      return propertyValues[i];
    }
    return null;
  }
  
  public propertyValue getPropertyValue(String sname) {
    for( int i = 0 ; i < index ; i += 1 ) {
      if(propertyValues[i].name.equals(sname)) {
        return propertyValues[i];
      }
    }
    return null;
  }

}
