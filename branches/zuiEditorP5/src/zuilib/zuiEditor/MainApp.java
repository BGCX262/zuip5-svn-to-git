package zuilib.zuiEditor;

import processing.core.PApplet;
import zuilib.core.ZUI;
import zuilib.core.zuiObject;
import zuilib.manager.*;

public class MainApp extends appStructure {
  
  public simpleWindowManager WindowManager;
  public simpleColorManager ColorManager;
  
  public appMenu menu;
  public appClipboard clipboard;
  public appEditor editor;
  public appProperties properties;
  
  public MainApp(ZUI zui) {
    super(zui);
    PApplet papplet = ui.getPApplet();
    editor = new appEditor(this,-papplet.width/2,-papplet.height/2,papplet.width,papplet.height);
    float w = papplet.width * 0.22f;
    clipboard = new appClipboard(this,papplet.width/2-w,-papplet.height/2,w,papplet.height);
    w = papplet.width * 0.8f;
    menu = new appMenu(this,-w/2,-papplet.height/2,w,16);
    w = papplet.width * 0.22f;
    properties = new appProperties(this,-papplet.width/2,-papplet.height/2,w,papplet.height);
  }
  
  public void setup() {
    super.setup();
    ui.FONT_FILE = FONTFILE;
    ui.FONT_SIZE = FONTSIZE;
    if(ui.DEBUG >= 1) PApplet.println("] Setup zuiEditor Main Application:");
    ui.addManager( new DrawManager(DRAWNAME,true) );
    ui.addManager( new simpleMouseManager(MOUSENAME,true) );
    ui.addManager( new KeyboardManager(KEYBOARDNAME,true) );
    ui.addManager( new simplePFontManager(PFONTNAME,true) );
    WindowManager = new simpleWindowManager(WINDOWNAME,true);
    WindowManager.addTo(ui);
    ColorManager = new simpleColorManager(COLORNAME,true);
    ColorManager.addTo(ui);
    
    editor.setup();
    clipboard.setup();
    properties.setup();
    menu.setup();
    
    editor.newProject("Project1"); //TODO remove this entry
    
    PApplet.println( zuiObject.class.isAssignableFrom(menu.filebutton.getClass() ));
  }

}
