package zuilib.core;

import zuilib.utils.vector;

/**
 * Die Komponenetenklasse.<br>
 * Sie ist lediglich zur Namensgebung und zum Auseinanderhalten zwischen Fesntern und komponenten.
 * @author arne.alder
 *
 */
public class component extends zuiObject {
  

  public component(String sname,vector pos) {
    super(sname,pos);
  }
  
  /**
   * Erstellt eine neue Instanz einer Komponente.
   * @param sname Name der neuen Komponente.
   * @param fx X-Position der neuen Komponente. (Relativ)
   * @param fy Y-Position der neuen Komponente. (Relativ)
   */
  public component(String sname, float fx,float fy) {
    super(sname,fx,fy);
  }
  
  /**
   * Kann dazu benutzt werden, nicht ein umständige formulierungen, wie:<br>
   * <code> component com = new Component("name",0,0);<br>
   * win.addComponent(com);</code><br>
   * zu benutzen, sondern einfacher:<br>
   * <code>component com = new Component("name",0,0);
   * com.addTo(win);</code><br>
   * <i>(Ist etwas kürzer, aber sonst gleich)</i>
   * @param win
   * @return
   */
  public int addTo(window win) {
    return win.addComponent( this );
  }
}
