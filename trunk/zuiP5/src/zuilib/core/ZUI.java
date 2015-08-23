package zuilib.core;

import java.util.HashMap;

import processing.core.PApplet;

/**
 * Die Hauptklasse der Bibliothek.<br>
 * Sie beinhaltet die Liste der Manager und ein paar globale Variabeln.<br><br>
 * Die Anwendung ist als PApplet Variable gedacht:<br>
 * <code>ZUI ui;<br>void setup() {<br>  ui = new ZUI(this[,debug level]);<br>}</code>
 * 
 * @author arne.alder
 * @category User Interface
 *
 */

public class ZUI {
  
  /**
   * Repr�sentiert die Anzahl der Manager.
   */
  public int index;
  /**
   * Beinhaltet die Liste der Manager.
   */
  public manager[] managers = new manager[0];
  /**
   * Beinhaltet die Liste der Typen der Manager.<br>
   * Diese werden den Managern beim hinzuf�gen entnommen.<br>
   * Empfohlene Typen sind:
   * <li>draw</li>
   * <li>mouse</li>
   * <li>keyboard</li>
   * <li>window</li>
   * <li>font</li>
   * <li>image</li>
   * <li>animation</li>
   * <li>color</li><br><br>
   * Es k�nnen aber auch eigene Typen benutzt werden,
   * jedoch sollte darauf geachtet werden, das diese nicht sinnlos sind.
   */
  public String[] types = new String[0];
  
  private HashMap<String, Integer> types_map = new HashMap<String, Integer>();
  /**
   * Eine Liste, die zu den jeweiligen Typen die dazu verlinkten Managernummern als Liste wiedergibt.
   */
  private Object[] managers_linked = new Object[0];
  
  private HashMap<String, Integer> managers_map = new HashMap<String, Integer>();
  /**
   * Diese Liste beinhaltet die Managernummern nach Typen sortiert.
   */
  private Object[] managers_by_type = new Object[0];
  /**
   * Gibt das Eltern-PApplet (Processing Applet) wieder.
   */
  public PApplet parent;

  /**
   * Konstante, die die maximale Z-Buffer Tiefe wiedergibt.<br>
   * Sollte vor den Managern definiert werden.<br><br>
   * <b>Default:</b> 10
   */
  public int    MAX_Z_INDEX;
  /**
   * Konstante, die das Debug Level wiedergibt.<br>
   * Sollte vor den Managern definiert werden.<br>
   * Kann auch bei der Instanzierung gesetzt weredn.<br><br>
   * <b>Default: </b> 0 <br><br>
   * Liste der Debug Level:
   * <li><b>0</b> - Keine Anzeige.</li>
   * <li><b>1</b> - Einfache Anzeige des Sketchpfades, des Debug Levels und von Warnungen.</li>
   * <li><b>2</b> - Ausgabe von Informationen �ber die Manager.</li>
   * <li><b>3</b> - Weitere Managersetupinformationen.</li>
   * <li><b>4</b> - Hinzuf�gungsinformationen (wenige (nur vom zuiObjectControler)).</li>
   * <li><b>5</b> - Hinzuf�gungsinformationen (alle).</li>
   * <li><b>6</b> - Einige Information �ber geladene Methoden.</li>
   * <li><b>7</b> - Anzeige der Position als Linien und alle Information �ber geladene Methoden.</li>
   * <li><b>8</b> - Benutzer definiert (nicht vergeben).</li>
   * <li><b>9</b> - Benutzer definiert (nicht vergeben).</li>
   * <li><b>10</b> - Ausgabe der Positionierungskoordinaten (alle).</li>
   */
  public int    DEBUG;
  /**
   * Konstante, die die Defaulte Fontdatei wiedergibt.<br>
   * Sollte vor den Labels definiert werden.<br>
   * Es Empfiehlt sich diese vor dem Instanzieren mehrer Labels mit er selben Schriftart.<br><br>
   * <b>Default: </b> Default.vlw
   */
  public String FONT_FILE;
  /**
   * Konstante, die die Defaulte Fontgr��e wiedergibt.<br>
   * Sollte vor den Labels definiert werden.<br>
   * Es Empfiehlt sich diese vor dem Instanzieren mehrer Labels mit er selben Schriftart.<br><br>
   * <b>Default: </b> 10
   */
  public int    FONT_SIZE;
  
  /**
   * Erstellt eine neue Instanz der Klasse ZUI.
   * @param p Das Eltern PApplet (Processing Applet).
   */
  public ZUI(PApplet p) {
    ZUI_init(p);
    DEBUG = 0;
  }
  
  /**
   * Erstellt eine neue Instanz der Klasse ZUI.
   * @param p Das Eltern PApplet (Processing Applet).
   * @param debug_level
   */
  public ZUI(PApplet p,int debug_level) {
    ZUI_init(p);
    DEBUG = debug_level;
    if(DEBUG >= 1) {
      PApplet.println("* Debug level = "+DEBUG);
      PApplet.println("* Sketchpath =  "+p.sketchPath);
    }
  }
  
  private void ZUI_init(PApplet p) {
    parent = p;
    index = 0;
    
    MAX_Z_INDEX = 10;
    FONT_FILE = "Default.vlw";
    FONT_SIZE = 10;
  }
  
  /**
   * F�gt einen neuen Manager hinzu und registriert ihn.<br>
   * <b>Empfohlen:</b><br>
   * <code> ui.addManager( new manager("newMan",true) );</code>
   * @param newm Der neue Manager.
   * @return Die Array Position des neuen Managers.
   */
  public int addManager(manager newm) {
    managers = (manager[]) PApplet.append(managers,newm);
    managers_map.put(newm.Name, index);
    index += 1;
    registerManager(index-1,newm);
    return index-1;
  }
  
  /**
   * Gibt wieder, ob bereits ein Manager mit dem gegeben Typ hinzugef�gt wurde.
   * @param type Managertyp.
   * @return True wenn bereits ein Manager mit dem gegebenem Typen hinzugef�gt wurde.
   */
  public boolean containsType(String type) {
    Integer i = types_map.get(type);
    if(i != null) return true;
    return false;
  }
  
  /**
   * Verlinkt den gegeben Manager mit dem gegeben Typen.<br>
   * Wenn alles klappt f�hrt der aktuelle Manager hinter dem Typ sein Setup auf dem Manager aus.
   * @param curm Die Nummer des Manager, der mit dem Typen verlinkt werden soll.
   * @param type Managertyp.
   * @return True wenn bereits ein Manager mit dem gegebenem Typen hinzugef�gt
   * wurde und das verlinken demzufolge erfolgreich war.
   */
  public boolean createLink(int curm,String type) {
    if(containsType(type)) {
      int[] mans = getLinked(type);
      int pos = getTypeIndex(type);
      mans = (int[]) PApplet.append(mans,curm);
      managers_linked[pos] = mans;
      return true;
    }
    return false;
  }
  
  /**
   * Verlinkt den gegeben Manager mit dem gegeben Typen.<br>
   * Wenn alles klappt f�hrt der aktuelle Manager hinter dem Typ sein Setup auf dem Manager aus.
   * @param currentname Der Name des Manager, der mit dem Typen verlinkt werden soll.
   * @param type Managertyp.
   * @return True wenn bereits ein Manager mit dem gegebenem Typen hinzugef�gt
   * wurde und das verlinken demzufolge erfolgreich war.
   */
  public boolean createLink(String currentname,String type) {
    Integer i = managers_map.get(currentname);
    if(i != null) return createLink(i,type);
      return false;
    }
  
  /**
   * Gibt einen Manager an Position <i>m</i> wieder.
   * @param m Nummer des Managers.
   * @return Gibt den angefragen Manager wieder, falls vorhanden, ansonsten null.
   */
  public manager get(int m) {
    return getManager(m);
  }
  
  /**
   * Gibt einen Manager mit Name <i>sname</i> wieder.
   * @param sname Name des Managers.
   * @return Gibt den angefragen Manager wieder, falls vorhanden, ansonsten null.
   */
  public manager get(String sname) {
    return getManager(sname);
  }
  
  /**
   * Gibt einen Manager mit Name <i>sname</i> wieder.
   * @param sname Name des Managers.
   * @return Gibt den angefragen Manager wieder, falls vorhanden, ansonsten null.
   */
  public manager getByName(String sname) {
    return getManagerByName(sname);
  }
  
  /**
   * Gibt den aktuellen Manager vom Typ <i>type</i> wieder.
   * @param type Typ des Managers.
   * @return Gibt den angefragen Manager wieder, falls vorhanden, ansonsten null.
   */
  public manager getByType(String type) {
    return getManagerByType(type);
  }
  
  /**
   * Gibt eine Nummernliste mit allen mit dem Typ <i>type</i> verlinken Managern wieder.
   * @param type Typ des Managers.
   * @return Gibt eine Liste mit den Nummern der Manager wieder.
   */
  public int[] getLinked(String type) {
    if(containsType(type)) {
      int pos = getTypeIndex(type);
      return (int[]) managers_linked[pos];
    }
    return new int[0];
  }
  
  /**
   * Gibt einen Manager an Position <i>m</i> wieder.
   * @param i Nummer des Managers.
   * @return Gibt den angefragen Manager wieder, falls vorhanden, ansonsten null.
   */
  public manager getManager(int i) {
    if( i < index ) {
        return managers[i];
      } else {
        return null;
    }
  }
  
  /**
   * Gibt einen Manager mit Name <i>sname</i> wieder.
   * @param sname Name des Managers.
   * @return Gibt den angefragen Manager wieder, falls vorhanden, ansonsten null.
   */
  public manager getManager(String sname) {
    Integer i = managers_map.get(sname);
    if(i != null) return managers[i];
    return null;
  }
  
  /**
   * Gibt einen Manager mit Name <i>sname</i> wieder.
   * @param sname Name des Managers.
   * @return Gibt den angefragen Manager wieder, falls vorhanden, ansonsten null.
   */
  public manager getManagerByName(String sname) {
    return get(sname);
  }
  
  /**
   * Gibt den aktuellen Manager vom Typ <i>type</i> wieder.
   * @param type Typ des Managers.
   * @return Gibt den angefragen Manager wieder, falls vorhanden, ansonsten null.
   */
  public manager getManagerByType(String type) {
    if(containsType(type)) {
      int pos = getTypeIndex(type);
      int[] mans = (int[]) managers_by_type[pos];
      int m = 0;
      for( int i = 0 ; i < mans.length ; i += 1 ) {
        m = mans[i];
        if(managers[m].isEnable()) {
          return get(m);
        }
      }
    }
    PApplet.println("[CRITICAL ERROR]:  Required manager with type "+type+" doesn't exists.");
    PApplet.println("                   Import required manager or checking your code.");
    getPApplet().die("[CRITICAL ERROR]");
    return null;
  }
  
  /**
   * Gibt das Eltern-PApplet wieder.
   * @return Das Eltern-PApplet.
   */
  public PApplet getPApplet() {
    return getParent();
  }
  
  /**
   * Gibt das Eltern-PApplet wieder.
   * @return Das Eltern-PApplet.
   */
  public PApplet getParent() {
    return parent;
  }
  
  /**
   * Gibt die Position des Types in der Liste types wieder.
   * @param type Typ eines Managers.
   * @return Wenn der Typ gefunden wurde die Position, ansonsten <b>-1</b>.
   */
  public int getTypeIndex(String type) {
    Integer i = types_map.get(type);
    if(i != null) return i;
    return -1;
  }
  
  /**
   * Gibt sich selber Wieder.<br>
   * Wird ben�tigt, da dies das Elternelement der gesamten Baumstruktur ist.
   * @return Diese Instance.
   */
  public ZUI getZUI() {
    return this;
  }
  
  /**
   * Verlinkt den angegebenen Manager mit einem Typ.
   * @param im Die ID Nummer des Managers.
   * @param type Der Typ, mit dem der MAnager verlinkt werden soll.
   * @return True wenn die Verlinkung erfolgreich war.
   */
  public boolean linkTo(int im,String type) {
    manager man = get(im);
    if(man != null) {
      return man.link(type);
    }
    return false;
  }
  
  /**
   * Verlinkt den angegebenen Manager mit einem Typ.
   * @param sm Der Name des Managers.
   * @param type Der Typ, mit dem der MAnager verlinkt werden soll.
   * @return True wenn die Verlinkung erfolgreich war.
   */
  public boolean linkTo(String sm,String type) {
    manager man = get(sm);
    if(man != null) {
      return man.link(type);
    }
    return false;
  }
  
  private void registerManager(int n,manager man) {
    man.install(this,n);
    if(DEBUG >= 2) PApplet.print("* registerManager:   "+man.Name+"  "+man.getType()+" ");
    if(containsType(man.getType())) {
      if(DEBUG >= 2) PApplet.println("(classified)");
        int pos = getTypeIndex(man.getType());
        int[] mans = (int[]) managers_by_type[pos];
        if(man.getType().equals("undefined")) {
          PApplet.println("[WARNING]: Type of manager "+man.Name+" is undifined.");
          man.setEnable(false);
        }
        if(man.isEnable()) {
          int m = 0;
          for( int i = 0 ; i < mans.length ; i += 1 ) {
            m = mans[i];
            if(managers[m].isEnable()) {
              managers[m].setEnable(false);
              break;
            }
          }
        }
        mans = (int[]) PApplet.append(mans,n);
        managers_by_type[pos] = mans;
      } else {
        if(DEBUG >= 2) PApplet.println("(add)");
        String type = man.getType();
        if(types_map.get(type) == null) {
          types_map.put(type, types.length);
          types = (String[]) PApplet.append(types,type);
        }
        managers_by_type = (Object[]) PApplet.append(managers_by_type, new int[] {n} );
        managers_linked = (Object[]) PApplet.append(managers_linked, new int[0] );
    }
    man.setup();
  }
  
  /**
   * Setzt einen neuen Manager an die gegebene Position und registriert ihn.
   * @param i Die Position in der Liste (Die ID).
   * @param newm Der Neue Manager.
   * @return True wenn die Position existiert.
   */
  public boolean setManager(int i, manager newm) {
    if( i < index ) {
        managers_map.remove(managers[i].Name);
        managers[i] = newm;
        managers_map.put(newm.Name, i);
        registerManager(i,newm);
        return true;
      } else {
        return false;
    }
  }
  
  /**
   * Schaltet einen Manager an oder aus.
   * @param n Die ID des Managers.
   * @param ben Wenn true wird der angegebene Manager angemacht und alle anderen des gleichen Typs ausgeschaltet.
   */
  public void setManagerEnable(int n,boolean ben) {
    manager man = get(n);
    String type = man.getType();
    int pos = getTypeIndex(type);
    int[] mans = (int[]) managers_by_type[pos];
    man.setEnable(ben);
    if(man.getType().equals("undefined")) {
      man.setEnable(false);
    }
    if(man.isEnable()) {
      int m = 0;
      for( int i = 0 ; i < mans.length ; i += 1 ) {
        m = mans[i];
        if(managers[m].isEnable()) {
          managers[m].setEnable(false);
          break;
        }
      }
    }
  }
  
  /**
   * Schaltet einen Manager an oder aus.
   * @param sname Der Name des Managers.
   * @param ben Wenn true wird der angegebene Manager angemacht und alle anderen des gleichen Typs ausgeschaltet.
   */
  public void setManagerEnable(String sname,boolean ben) {
    Integer i = managers_map.get(sname);
    if(i != null) setManagerEnable(i,ben);
  }

}
