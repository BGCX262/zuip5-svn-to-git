package zuilib.core;

import java.lang.reflect.Field;

import processing.core.PApplet;

/**
 * Die Grundklasse.<br>
 * Sie beinhaltet alle grundlegenden Functions, die n�tig sind.<br>
 * Die Functions k�nnen sogut wie von jedem Object, ob Window, Component, Manager oder zuiObject aufgerufen werden.
 * @author arne.alder
 *
 */
public class zuiAtomObject {
 
  /**
   * Das Elternobject.<br>
   */
  public zuiAtomObject parentObject;
  
  /**
   * Gibt den Manager wieder, in dem sich Das zuiObject befindet.
   * @return Den Manager.
   */
  public manager getManager() {
    if(!check("getManager")) {return null;};
    return parentObject.getManager();
  }
  
  /**
   * Gibt den aktuell eingeschalteten Manager des gefragten Typs wieder.
   * @param type der Typ des Managers.
   * @return Den Manager.
   */
  public manager getManagerByType(String type) {
    if(!check("getManagerByType")) {return null;};
    return parentObject.getManagerByType(type);
  }
  
  /**
   * Gibt das aktuelle PApplet wieder, in dem sich das ZUI befindet.
   * @return Das PApplet.
   */
  public PApplet getPApplet() {
    if(!check("getPApplet")) {return null;};
    return parentObject.getPApplet();
  }
  
  /**
   * Gibt das Elternobject des aktuellen Objectes wieder.
   * @return das Elternobject als zuiAtomObject.
   */
  public zuiAtomObject getParentObject() {
    check("getParentObject");
    return parentObject;
  }
  
  /**
   * Gibt das aktuelle ZUI wieder, in dem das Object sich befindet.
   * @return Das aktuelle ZUI.
   */
  public ZUI getZUI() {
    if(!check("getZUI")) {return null;};
    return parentObject.getZUI();
  }
  
  public String getThisName() {
    if(!check("getThisName")) {return "";};
    Field[] fields = parentObject.getClass().getFields();
    for(int i = 0 ; i < fields.length ; i += 1 ) {
      try {
        if(fields[i].get(parentObject).equals(this)) {
          return fields[i].getName();
        }
      } catch(Exception e) {}
    }
    if(getZUI().DEBUG == 0) return "";
    PApplet.println("[WARNING]: getThisName:  doesn't found this object. ("+this+")  (Can Mostly be ignored.)");
    PApplet.println("           Maybe you try to use setup in the constructor.");
    PApplet.println("           Mostly a property defines a controler with the proerty's parentObject so you can ignore this warning.");
    return "";
  }
  
  private boolean check(String func) {
    if(parentObject == null) {
      PApplet.println("[WARNING]: "+func+":  parentObject wasn't defined.");
      return false;
    }
    return true;
  }
  
 ///////////////////////////public
  
  /**
   * Setzt das Elternobject.<br>
   * Wird fast immer nach der benutzergesteuerten Initialisierung vom Object erwartet.<br>
   * <b>H�ufige Fehlerquelle!</b>
   */
  public void setParentObject(zuiAtomObject newzao) {
    parentObject = newzao;
  }
  
}

