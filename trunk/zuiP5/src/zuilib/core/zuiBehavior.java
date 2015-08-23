package zuilib.core;

/**
 * Die Vorlageklasse für das Verhalten von zuiObjects.<br>
 * Um ein eigenes Verhalten zu erstellen einfach eine klasse definieren mit:
 * <code>extends zuiBehavior</code> und den code in die gewünschten functions tun.
 * @author arne.alder
 *
 */
public class zuiBehavior implements zuiControl {
  
  /**
   * Das zuiObject, dessen verhalten mit dieser Instanz geändert werden soll.
   */
  public zuiObject object;
  /**
   * Name des Verhaltens.
   */
  public String Name;
  
  /**
   * Erstellt eine neue Instanz eines Verhaltens.
   * @param sname Name des neuen Verhaltens.
   */
  public zuiBehavior(String sname) {
    Name = sname;
  }

  /**
   * Wird vom Besitzer des Verhaltens ausgeführt, um <i>object</i> zu setzen.
   * @param parent
   */
  public void install(zuiObject parent) {
    object = parent;
  }
  
  public void update() {return;}
  public void display() {return;}
  public void postdraw() {return;}
  public void draw()  {return;}
  public void predraw() {return;}
  public void keyPressed(char key)  {return;}
  public void keyReleased(char key) {return;}
  public void keyTyped(char key) {return;}
  public void mouseClicked() {return;}
  public void mouseDragged() {return;}
  public void mouseMoved() {return;}
  public void mousePressed() {return;}
  public void mouseReleased() {return;}
  
}
