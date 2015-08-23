package zuilib.core;

/**
 * Interface für ein zuiObject.<br>
 * Beinhaltet alle Functions, die die haupt Manager (vom typ 'draw', 'mouse' und 'keyboard')
 * bei einem zuiObject mindestens auslösen sollten.
 * @author arne.alder
 *
 */
public interface zuiControl {
  
  public void update();
  public void display();
  public void predraw();
  public void draw();
  public void postdraw();
  
  public void keyPressed(char key);
  public void keyReleased(char key);
  public void keyTyped(char key);
  
  public void mouseClicked();
  public void mouseDragged();
  public void mouseMoved();
  public void mousePressed();
  public void mouseReleased();
  

}
