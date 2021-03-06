package zuilib.components;

import zuilib.core.component;
import zuilib.core.window;
import zuilib.properties.zuiObjectsController;
import zuilib.utils.vector;

/**
 * Die Gruppenklasse.<br>
 * Sie kann Components aufnehmen.
 * @author arne.alder
 *
 */
public class WindowControler extends component {
  
  /**
   * Speicher und Betreuer der Components.
   */
  public zuiObjectsController windows;
  
  /**
   * Erstellt eine neue Isntanz eines Fenster.
   * @param sname Name des neuen Fensters.
   * @param pos Position des neuen Fensters.
   */
  public WindowControler(String sname,vector pos) {
    super(sname,pos);
    WindowControler_init();
  }
  
  /**
   * Erstellt eine neue Isntanz eines Fenster.
   * @param sname Name des neuen Fensters.
   * @param fx X-Position des neuen Fensters.
   * @param fy Y-Position des neuen Fensters.
   */
  public WindowControler(String sname, float fx, float fy) {
    super(sname,fx,fy);
    WindowControler_init();
  }
  
  /**
   * F�gt eine neue Komponente hinzu.
   * @param newc Neue Komponente.
   * @return ID der Komponente.
   */
  public int addWindow(window newc) {
    return windows.addObject(newc, "Window");
  }
  
  /**
   * Gibt eine Komponente mit der ID i wieder.
   * @param i Die ID der Komponente.
   * @return Die Komponente.
   */
  public window getWindow(int i) {
    return (window) windows.getObject(i);
  }
  
  /**
   * Gibt eine Komponente mit dem Namen sname wieder.
   * @param sname Name der Komponente.
   * @return Die Komponente.
   */
  public window getWindow(String sname) {
    return (window) windows.getObject(sname);
  }
  
  /**
   * Gibt eine Komponente mit der ID i wieder.
   * @param i Die ID der Komponente.
   * @return Die Komponente.
   */
  public window get(int i) {
    return getWindow(i);
  }
  
  /**
   * Gibt eine Komponente mit dem Namen sname wieder.
   * @param sname Name der Komponente.
   * @return Die Komponente.
   */
  public window get(String sname) {
    return getWindow(sname);
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
  public boolean setWindow(int i,window newc) {
    return windows.setObject(i, newc);
  }
  
  /**
   * Setzt die Komponente mit der ID n in der Sichtbarkeit nach hinten.
   * @param n Die ID.
   * @return True wenn alles klaro.
   */
  public boolean setDown(int n) {
    return windows.setDown(n);
  }
  
  /**
   * Setzt die Komponente mit dem Namen sname in der Sichtbarkeit nach hinten.
   * @param sname Der Name.
   * @return True wenn alles klaro.
   */
  public boolean setDown(String sname) {
    return windows.setDown(sname);
  }
  
  /**
   * Setzt die Komponente mit der ID n in der Sichtbarkeit nach <b>ganz</b> hinten.
   * @param n Die ID.
   * @return True wenn alles klaro.
   */
  public boolean setOnBottom(int i) {
    return windows.setOnBottom(i);
  }
  
  /**
   * Setzt die Komponente mit dem Namen sname in der Sichtbarkeit nach <b>ganz</b> hinten.
   * @param sname Der Name.
   * @return True wenn alles klaro.
   */
  public boolean setOnBottom(String sname) {
    return windows.setOnBottom(sname);
  }
  
  /**
   * Setzt die Komponente mit der ID n in der Sichtbarkeit nach ganz <b>vorn</b>.
   * @param n Die ID.
   * @return True wenn alles klaro.
   */
  public boolean setOnTop(int i) {
    return windows.setOnTop(i);
  }
  
  /**
   * Setzt die Komponente mit dem Namen sname in der Sichtbarkeit nach ganz <b>vorn</b>.
   * @param sname Der Name.
   * @return True wenn alles klaro.
   */
  public boolean setOnTop(String sname) {
    return windows.setOnTop(sname);
  }
  
  /**
   * Setzt die Komponente mit der ID n in der Sichtbarkeit nach vorn.
   * @param n Die ID.
   * @return True wenn alles klaro.
   */
  public boolean setUp(int n) {
    return windows.setUp(n);
  }
  
  /**
   * Setzt die Komponente mit dem Namen sname in der Sichtbarkeit nach vorn.
   * @param sname Der Name.
   * @return True wenn alles klaro.
   */
  public boolean setUp(String sname) {
    return windows.setUp(sname);
  }
  
  /** 
   * Setzt den Z-Buffer f�r die Komponente mit der ID n;
   * @param n Die ID.
   * @param newz Der Z-Buffer-Wert.
   * @return True wenn alles klaro.
   */
  public boolean setZIndex(int n, int newz) {
    return windows.setZIndex(n, newz);
  }
  
  /** 
   * Setzt den Z-Buffer f�r die Komponente mit dem Namen sname;
   * @param sname Der Name.
   * @param newz Der Z-Buffer-Wert.
   * @return True wenn alles klaro.
   */
  public boolean setZIndex(String sname, int newz) {
    return windows.setZIndex(sname, newz);
  } 
  
  private void WindowControler_init() {
    windows = new zuiObjectsController();
  }
  
  /** 
   * Initialisiert den zuiObjectControler f�r die components.
   */
  public void setup() {
    super.setup();
    windows.setup(this);
    properties.setPostMarker("mouseReleased",1);
  }
  
  /**
   * Gibt das Event an die Komponenten weiter.
   */
  public void update() {
    super.update();
    window win = null;
    for(int i = windows.length-1 ; i >= 0 ; i -= 1 ) {
      win = (window) windows.getSorted(i);
      win.update();
    }
  }
  
}
