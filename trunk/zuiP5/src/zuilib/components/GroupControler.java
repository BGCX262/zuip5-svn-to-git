package zuilib.components;

import zuilib.core.component;
import zuilib.properties.zuiObjectsController;
import zuilib.utils.vector;

/**
 * Die Gruppenklasse.<br>
 * Sie kann Components aufnehmen.
 * @author arne.alder
 *
 */
public class GroupControler extends component {

  /**
   * Speicher und Betreuer der Components.
   */
  public zuiObjectsController components;
  
  /**
   * Erstellt eine neue Isntanz eines Fenster.
   * @param sname Name des neuen Fensters.
   * @param pos Position des neuen Fensters.
   */
  public GroupControler(String sname,vector pos) {
    super(sname,pos);
    GroupControler_init();
  }
  
  /**
   * Erstellt eine neue Isntanz eines Fenster.
   * @param sname Name des neuen Fensters.
   * @param fx X-Position des neuen Fensters.
   * @param fy Y-Position des neuen Fensters.
   */
  public GroupControler(String sname, float fx, float fy) {
    super(sname,fx,fy);
    GroupControler_init();
  }
  
  /**
   * F�gt eine neue Komponente hinzu.
   * @param newc Neue Komponente.
   * @return ID der Komponente.
   */
  public int addComponent(component newc) {
    return components.addObject(newc, "Component");
  }
  
  /**
   * Gibt eine Komponente mit der ID i wieder.
   * @param i Die ID der Komponente.
   * @return Die Komponente.
   */
  public component getComponent(int i) {
    return (component) components.getObject(i);
  }
  
  /**
   * Gibt eine Komponente mit dem Namen sname wieder.
   * @param sname Name der Komponente.
   * @return Die Komponente.
   */
  public component getComponent(String sname) {
    return (component) components.getObject(sname);
  }
  
  /**
   * Gibt eine Komponente mit der ID i wieder.
   * @param i Die ID der Komponente.
   * @return Die Komponente.
   */
  public component get(int i) {
    return getComponent(i);
  }
  
  /**
   * Gibt eine Komponente mit dem Namen sname wieder.
   * @param sname Name der Komponente.
   * @return Die Komponente.
   */
  public component get(String sname) {
    return getComponent(sname);
  }
  
  /**
   * Gibt das Event an die Komponenten weiter.
   */
  public void mouseReleased() {
    super.mouseReleased();
    pressed = false;
    properties.post_mouseReleased(1);
  }
  
  /**
   * Setzt eine neue Componente an eine Position.
   * @param i Die Listenposition.
   * @param newc Die neue Komponente.
   * @return
   */
  public boolean setComponent(int i,component newc) {
    return components.setObject(i, newc);
  }
  
  /**
   * Setzt die Komponente mit der ID n in der Sichtbarkeit nach hinten.
   * @param n Die ID.
   * @return True wenn alles klaro.
   */
  public boolean setDown(int n) {
    return components.setDown(n);
  }
  
  /**
   * Setzt die Komponente mit dem Namen sname in der Sichtbarkeit nach hinten.
   * @param sname Der Name.
   * @return True wenn alles klaro.
   */
  public boolean setDown(String sname) {
    return components.setDown(sname);
  } 
  
  /**
   * Setzt die Komponente mit der ID n in der Sichtbarkeit nach <b>ganz</b> hinten.
   * @param n Die ID.
   * @return True wenn alles klaro.
   */
  public boolean setOnBottom(int i) {
    return components.setOnBottom(i);
  }
  
  /**
   * Setzt die Komponente mit dem Namen sname in der Sichtbarkeit nach <b>ganz</b> hinten.
   * @param sname Der Name.
   * @return True wenn alles klaro.
   */
  public boolean setOnBottom(String sname) {
    return components.setOnBottom(sname);
  }
  
  /**
   * Setzt die Komponente mit der ID n in der Sichtbarkeit nach ganz <b>vorn</b>.
   * @param n Die ID.
   * @return True wenn alles klaro.
   */
  public boolean setOnTop(int i) {
    return components.setOnTop(i);
  }
  
  /**
   * Setzt die Komponente mit dem Namen sname in der Sichtbarkeit nach ganz <b>vorn</b>.
   * @param sname Der Name.
   * @return True wenn alles klaro.
   */
  public boolean setOnTop(String sname) {
    return components.setOnTop(sname);
  }
  
  /**
   * Setzt die Komponente mit der ID n in der Sichtbarkeit nach vorn.
   * @param n Die ID.
   * @return True wenn alles klaro.
   */
  public boolean setUp(int n) {
    return components.setUp(n);
  }
  
  /**
   * Setzt die Komponente mit dem Namen sname in der Sichtbarkeit nach vorn.
   * @param sname Der Name.
   * @return True wenn alles klaro.
   */
  public boolean setUp(String sname) {
    return components.setUp(sname);
  }
  
  /** 
   * Setzt den Z-Buffer f�r die Komponente mit der ID n;
   * @param n Die ID.
   * @param newz Der Z-Buffer-Wert.
   * @return True wenn alles klaro.
   */
  public boolean setZIndex(int n, int newz) {
    return components.setZIndex(n, newz);
  }
  
  /** 
   * Setzt den Z-Buffer f�r die Komponente mit dem Namen sname;
   * @param sname Der Name.
   * @param newz Der Z-Buffer-Wert.
   * @return True wenn alles klaro.
   */
  public boolean setZIndex(String sname, int newz) {
    return components.setZIndex(sname, newz);
  } 
  
  private void GroupControler_init() {
    components = new zuiObjectsController();
  }
  
  /** 
   * Initialisiert den zuiObjectControler f�r die components.
   */
  public void setup() {
    super.setup();
    components.setup(this);
    properties.setPostMarker("mouseReleased",1);
  }
  
  /**
   * Gibt das Event an die Komponenten weiter.
   */
  public void update() {
    super.update();
    component com = null;
    for(int i = components.length-1 ; i >= 0 ; i -= 1 ) {
      com = (component) components.getSorted(i);
      com.update();
    }
  }
  
}
