package dynamicLoad;

/**
 * Motherclass of FunctionLoad and FieldLoad.
 * <br>Hirated some similar variables and methods.
 * @author arne.alder
 * @category reflection
 */
public class loaderObject {
  
  /**
   * Name of the loaded Object as String.
   */
  public String Name;
  /**
   * The Owner of the loaded Object.
   */
  public Object Parent;
  /**
   * Save the debug level.
   * <p>List of Level:
   * <li>0 - No message will be printed except errors.</li>
   * <li>1 - Some informations and warnings will be printed.</li>
   * <li>2 - All messages will be printed.</li>
   * </p>
   */
  public int DEBUG;
  /**
   * Representing the loading status.<br>
   * True if Object could be loaded.
   */
  public boolean Accessible;
  
  /**
   * Creates a new Instance from loaderObject.<br>
   * Setting debug level to 0.
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   */
  public loaderObject(String sname, Object oparent) {
    loaderObject_init(0,sname,oparent);
  }
  
  /**
   * Creates a new Instance from loaderObject.<br>
   * @param debug_level Specify printed messages.
   * @param sname Name of the requested Object.
   * @param oparent Parent object and owner of the requested Object.
   * @see DEBUG
   */
  public loaderObject(int debug_level,String sname, Object oparent) {
    loaderObject_init(debug_level,sname,oparent);
  }
  
  private void loaderObject_init(int debug_level, String sname, Object oparent) {
    Name = sname;
    Parent = oparent;
    DEBUG = debug_level;
    Accessible = false;
  }

}
