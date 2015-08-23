package zuilib.core;

import processing.core.PConstants;
import processing.core.PGraphics;
import zuilib.manager.windowmanager;
import zuilib.properties.BehaviorController;
import zuilib.properties.NameController;
import zuilib.properties.PositionController;
import zuilib.properties.PropertyController;
import zuilib.properties.RotationController;
import zuilib.properties.ScaleController;
import zuilib.properties.VisibilityController;
import zuilib.properties.dimensionController;
import zuilib.utils.util;
import zuilib.utils.vector;

/**
 * Die Mutterklasse für alle anzuzeigene Objekte.<br>
 * Sie beihnhaltet alle Properties, die ein Fenster oder eine Componente hat:
 * <li>Name - ... ja ...</li>
 * <li>dimension - Die Ausmaße des Objects.</li>
 * <li>position - Die Positionierung des Objects.</li>
 * <li>rotation - Die Rotation des Objects.</li>
 * <li>scale - Die Skalierung des Objects.</li>
 * <li>behavior - Das Benutzerdefinierte Verhalten des Objects.</li>
 * @author arne.alder
 *
 */
public class zuiObject extends zuiAtomObject implements zuiControl {

  /**
   * Die Ausmaße des Objects.<br>
   * Wird jedoch oft von genaueren Klassen überschrieben,  wie zB RectDimension, etc.
   */
  public dimensionController dimension;
  /**
   * Variable die angibt, ob der Benutzer auf dem Object die Maustaste drückt.
   */
  public boolean pressed = false;
  /**
   * Gibt die Sichtbarkeit des Objects wieder.<br>
   * Wird vom Object gesetzt.
   */
  public VisibilityController visibility;
  /**
   * Gibt die allgemeine Transparenz des Objects wieder.<br>
   * Diese kann natürlich auch einzeln beim ColorBundle gesetzt werden.
   */
  public float opaq;
  /**
   * Gibt den Zeichenmode des Objects wieder.<br>
   * Es gibt genau zwei zur Auswahl, die mit den gleichnamigen Konstanten von Processing
   * identisch sind (Es sind diese):
   * <li><b>CENTER</b> - Die Position des Objects befindet sich in der Mitte des Gezeichneten.</li>
   * <li><b>CORNER</b> - Die Position des Objects befindet sich in der linken oberen Ecke des Gezeichneten.</li>
   */
  public int mode;
  /**
   * Die Namens-Property.
   */
  public NameController Name;
  /**
   * Die Positions-Property.
   */
  public PositionController position;
  /**
   * Die Rotations-Property.
   */
  public RotationController rotation;
  /**
   * Die Verhaltens-Property.
   */
  public BehaviorController behavior;
  /**
   * Die Skalierungs-Property.
   */
  public ScaleController scale;
  
  public PropertyController properties;
  /**
   * Die Die Graphik auf die aktuell gezeichnet wird.<br>
   * <b>Default: </b> PApplet.g
   */
  public PGraphics graphic;
  /**
   * Die ID des Objects.<br>
   * Sie ist zugleich auch die Position im Auflistenden Object wie zB der windowmanager oder window.
   */
  private int id;
  private vector mouse;
  private vector prev_mouse;
  
  /**
   * Initialisiert ein zuiObject mit namen und Position.
   * @param sname Der Name des Objects.
   * @param ix Die X-Koordinate der Position des Objects.
   * @param iy Die Y-Koordinate der Position des Objects.
   */
  public zuiObject(String sname, float ix, float iy) {
    zuiObject_init(sname,new vector(ix,iy));
  }
  
  /**
   * Initialisiert ein zuiObject mit Namen und Position.
   * @param sname Der Name des Objects.
   * @param start_pos Die Position des Objects.
   */
  public zuiObject(String sname, vector vstart_pos) {
    zuiObject_init(sname,vstart_pos);
  }
  
  /**
   * Initialisiert ein zuiObject mit generiertem Namen und Position.
   * @param start_pos Die Position des Objects.
   */
  public zuiObject(float ix, float iy) {
    zuiObject_init(new vector(ix,iy));
  }
  
  /**
   * Initialisiert ein zuiObject mit generiertem Namen und Position.
   * @param start_pos Die Position des Objects.
   */
  public zuiObject(vector vstart_pos) {
    zuiObject_init(vstart_pos);
  }
  
  /** 
   * Stellt das Object dar.
   */
  public void display() {
    properties.pre_display(0);
    predraw();
    draw();
    postdraw();
    //behavior.display();
    properties.post_display(0);
  }
  
  /**
   * Wird meist vom Object aufgerufen, welches dieses Object auflistet.
   * @param curParent Das Elternobject, also das, welches auflistet.
   * @param curId Die ID, Position des Objects in des Liste.
   */
  public void install(zuiAtomObject curParent, int curId) {
    setParentObject(curParent);
    id = curId;
  }
  
  /**
   * Gibt die ID wieder.
   * @return Die ID.
   */
  public int id() {
    return id;
  }
  
  public vector getParentMouse() {
    vector mouse = new vector(0,0);
    try {
      if(parentObject.parentObject == null) {
          mouse = ((windowmanager) getParentObject()).getMouse();
        } else if(parentObject != null) {
          mouse = ((zuiObject) getParentObject()).getMouse();
      }
    } catch (Exception e) {
      mouse = new vector(0,0);
    }
    return new vector(mouse);
  }
  
  /**
   * Gibt die Mauspositionierung relative an, dh Rotation und Skalierung m�ssen
   * nicht in der Rechnung auftauchen.
   * @return Die Position der Maus.
   */
  public vector getMouse() {
    if(mouse == prev_mouse) { // wenn die vllt noch nicht geupdatete mouse gleich der vorherigen maus ist, wurde die mouse in diesem frame noch nicht abgefragt.
      mouse = getParentMouse();
      mouse.Sub(position.get());
      mouse = scale.unScale(mouse);
      mouse.Rot(-rotation.get());
      return new vector( mouse );
    }
    return new vector( mouse );
  }
  
  /**
   * Gibt wieder, ob schonmal �ber einem anderen Object die Maustaste gedr�ckt wurde.
   * @return False, wenn die Maustaste �ber (bisher) keinem Object gedr�ckt wird.
   */
  public boolean getLock() {
    return ((windowmanager) getManager()).locked;
  }
  
  /**
   * Benutzen Objecte, um den anderen Objecten zu sagen, dass sie die Maustastenaktion f�r sich beanspruchen.
   * @param newlock ..
   */
  public void setLock(boolean newlock) {
    ((windowmanager) getManager()).locked = newlock;
  }
  
  /**
   * Gibt wieder, ob das Object vom Benutzer auf nicht sichtbar gesetzt wurde.
   * @return True wenn das Object nicht sichtbar ist und auch nicht werden kann.
   */
  public boolean isHidden() {
    return visibility.isHidden();
  }
  
  /**
   * Gibt wieder, ob das Object sichtbar ist.
   * @return True wenn es sichtbar ist.
   */
  public boolean isVisible() {
    return visibility.is();
  }
  
  /*Place Holder*/
  public void keyPressed(char key) {
    properties.pre_keyPressed(0,key);
    //behavior.keyPressed(key);
    properties.post_keyPressed(0,key);
  }
  public void keyReleased(char key) {
    properties.pre_keyReleased(0,key);
    //behavior.keyReleased(key);
    properties.post_keyReleased(0,key);
  }
  public void keyTyped(char key) {
    properties.pre_keyTyped(0,key);
    //behavior.keyTyped(key);
    properties.post_keyTyped(0,key);
  }
  public void mouseClicked() {
    properties.pre_mouseClicked(0);
    //behavior.mouseClicked();
    properties.post_mouseClicked(0);
  }
  public void mouseDragged() {
    properties.pre_mouseDragged(0);
    //behavior.mouseDragged();
    properties.post_mouseDragged(0);
  }
  public void mouseMoved() {
    properties.pre_mouseMoved(0);
    //behavior.mouseMoved();
    properties.post_mouseMoved(0);
  }
  public void mousePressed() {
    properties.pre_mousePressed(0);
    //behavior.mousePressed();
    properties.post_mousePressed(0);
  }
  public void mouseReleased() {
    properties.pre_mouseReleased(0);
    //behavior.mouseReleased();
    properties.post_mouseReleased(0);
  }
  public void draw() {
    properties.pre_draw(0);
    //behavior.draw();
    properties.post_draw(0);
  }
  /**
   * Rechnet aus, ob die aktuelle Mausposition sich gerade �ber dem Object sich befindet.
   * @return True wenn das der Fall ist.
   */
  public boolean over() { //place holder
    dimension.over = false;
    return dimension.over;
  }
  
  /**
   * Gibt wieder, ob sich die Mausposition sich �ber einem Umkreis um die Objectposition befindet.
   * @param fdiameter Der Durchmesser des Umkreises.
   * @param mode Gleiche Variable wie 'mode'. Gibt an wo sich die Objectposition befindet.
   * @return True, wenn sich die Maus in Umkreis befindet.
   */
  public boolean overCircle(float fdiameter, int mode) {
    return overCircle(position,fdiameter,mode);
  }
  
  /**
   * Gibt wieder, ob sich die Mausposition sich �ber einem Umkreis um die Position befindet.
   * @param pos Gibt die Position an, um die der Umkreis geht.
   * @param fdiameter Der Durchmesser des Umkreises.
   * @param mode Gleiche Variable wie 'mode'. Gibt an wo sich die Position befindet.
   * @return True, wenn sich die Maus in Umkreis befindet.
   */
  public boolean overCircle(PositionController pos, float fdiameter, int mode) {
    vector vpos = new vector(0,0);
    if(pos != position) vpos = pos.get();
    if(mode == PConstants.CORNER) vpos.Add( new vector(fdiameter/2,fdiameter/2) );
    vector distance = vector.VecSub(vpos,getMouse());
    return (distance.getValue() < fdiameter/2 );
  }
  
  /**
   * Gibt wieder, ob sich die Mausposition in einem Rechteck um die Objectposition befindet.
   * @param fwidth Weite des Rechteckes.
   * @param fheight H�he des Rechteckes.
   * @param mode Gleiche Variable wie 'mode'. Gibt an wo sich die Objectposition befindet.
   * @return True wenn die Mausposition sich im Rechteck befindet.
   */
  public boolean overRect(float fwidth, float fheight, int mode) {
    return overRect(position,fwidth,fheight,mode);
  }
  
  /**
   * Gibt wieder, ob sich die Mausposition in einem Rechteck um die Position befindet.
   * @param pos Gibt die Position an, um die das Rechteck geht.
   * @param fwidth Weite des Rechteckes.
   * @param fheight H�he des Rechteckes.
   * @param mode Gleiche Variable wie 'mode'. Gibt an wo sich die Position befindet.
   * @return True wenn die Mausposition sich im Rechteck befindet.
   */
  public boolean overRect(PositionController pos, float fwidth, float fheight, int mode) {
    vector vpos = new vector(0,0);
    if(pos != position) vpos = pos.get();
    vector mouse = getMouse();
    float xl,xh,yl,yh = 0;
    if(mode == PConstants.CORNER) {
        xl = vpos.x;
        xh = vpos.x+fwidth;
        yl = vpos.y;
        yh = vpos.y+fheight;
      } else {
        xl = vpos.x-fwidth/2;
        xh = vpos.x+fwidth/2;
        yl = vpos.y-fheight/2;
        yh = vpos.y+fheight/2;
    }
    return util.inRange(mouse.x, xl, xh) && util.inRange(mouse.y, yl, yh);
  }
  /**
   * Setzt den Mode entweder auf <b>CENTER</b> oder <b>CORNER</b>.
   * @param newm Der neue mode: eine der beiden Processingkonstanten.
   */
  public void setMode(int newm) {
    if(newm == PConstants.CENTER) {
      mode = PConstants.CENTER;
    }
    if(newm == PConstants.CORNER) {
      mode = PConstants.CORNER;
    }
  }
  
  /**
   * Setzt die Sichtbarkeit des Objects.<br>
   * Die sichtbarkeit wird dann in 'hidden' gespeichert.
   * @param vis Die neue Sichtbarkeit.
   */
  public void setVisibility(boolean vis) {
    visibility.set(vis);
  }
  
  /**
   * Wird immer vor dem Zeichnen ausgef�hrt.<br>
   * ist f�r Modifikationscode gedacht, wie zb irgenwelche einfachen Animationen.
   */
  public void update() {
    properties.pre_update(0);
    prev_mouse = mouse;
    //visibility.doTask();
    //behavior.update();
    properties.post_update(0);
  }
  
  private void zuiObject_init(String sname,vector start_pos) {
    Name = new NameController(sname);
    zuiObject_init(start_pos);
  }
  
  /**
   * Wenn in der Create-Function auf das PApplet oder das Elternobject zugegriffen werden soll geht das nicht.<br>
   * Das kann dann hier vorgenommen werden.<br><br>
   * Diese Function wird meist vom Elternobject kurz nach der Initialisierung beim hinzuf�gen ausgef�hrt.
   */
  public void setup() {
    /* !! Order is important !! */
    properties.setup(this);
    Name.setup(this);
    if(graphic == null) resetGraphic();
    visibility.setup(this);
    dimension.setup(this);
    position.setup(this);
    scale.setup(this);
    rotation.setup(this);
    behavior.setup(this);
  }
  
  /**
   * Beinhaltet alle Befehle, die <i>vor</i> dem eigentlich Zeichnen ausgef�hrt werden sollen.
   */
  public void predraw() {
    properties.pre_predraw(0);
    graphic.strokeWeight(1);
    graphic.pushMatrix();
    //position.doTask();
    //scale.doTask();
    //rotation.doTask();
    //behavior.predraw();
    properties.post_predraw(0);
  }
  
  /**
   * Beinhaltet alle Befehle, die <i>nach</i> dem eigentlich Zeichnen ausgef�hrt werden sollen.
   */
  public void postdraw() {
    properties.pre_postdraw(0);
    //behavior.postdraw();
    graphic.popMatrix();
    properties.post_postdraw(0);
  }
  
  private void zuiObject_init(vector start_pos) {
    id = -1;
    opaq = 255;
    mouse = new vector(0,0);
    prev_mouse = mouse; 
    
    properties = new PropertyController();
    if(Name == null) Name = new NameController();
    visibility = new VisibilityController();
    dimension  = new dimensionController();
    position   = new PositionController(start_pos);
    rotation   = new RotationController();
    behavior   = new BehaviorController();
    scale      = new ScaleController();
  }
  
  /**
   * Setzt die Graphik des Objects.
   * @param newGraphic Die neue Graphik.
   */
  public void setGraphic(PGraphics newGraphic) {
    graphic = newGraphic;
  }
  
  /**
   * Setzt die Graphik auf die vom PApplet wieder zur�ck.
   */
  public void resetGraphic() {
    try {
      graphic = ((zuiObject) getParentObject()).graphic;
    }catch(Exception e) {
      try {
        graphic = ((windowmanager) getParentObject()).graphic;
      }catch(Exception e2) {
        graphic = getPApplet().g;
      }
    }
  }
  
  /**
   * Setzt die allgemeine Transparenz des Objects.
   * @param newopaq
   */
  public void setOpaq(float newopaq) {
    opaq = newopaq;
  }
  
  /**
   * Gibt die allgemeine Transparenz des Objects wieder.
   * @return Die Transparenz.
   */
  public float getOpaq() {
    return opaq;
  }
  
  /**
   * Gibt die niedrigste allgemeine Transparenz von diesem und allen Elternobjecten wieder.
   * @return
   */
  public float getOpaqAbsolute() {
    float o = 255;
    try {
      o = ((zuiObject) getParentObject()).getOpaqAbsolute();
    } catch (Exception e) {
      o = 255;
    }
    if(opaq < o) o = opaq;
    return o;
  }
  
}
