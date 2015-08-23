

import processing.core.*;
import zuilib.components.*;
import zuilib.core.*;
import zuilib.extra.MixedFontManager;
import zuilib.manager.*;
import zuilib.properties.HoverPosition;
import zuilib.utils.listDynamicArray1D;
import zuilib.windows.*;

public class zuiP5 extends PApplet {
  private static final long serialVersionUID = 6265634758596206387L;
  
  //private RectWindow recwin;
  
  public ZUI ui;

  public void draw() {
    background(3);
    /*
    vector v = recwin.getMouse();
    vector o = ((windowmanager) recwin.getManagerByType("window")).position.get();
    pushMatrix();
    //rotate(recwin.rotation.get());
    //v.Sub(o);
    stroke(255,22,22);
    line(o.x,o.y,v.x,v.y);
    popMatrix();*/
  }

  public void setup() {
    size(320,240);
    smooth();
    frameRate(24);

    ui = new ZUI(this,5);
    println(ui.toString());
    ui.FONT_FILE = "../../Verdana-48.vlw";
    ui.addManager( new DrawManager("simpleDraw",true) );
    ui.addManager( new KeyboardManager("simpleKeyboard",true) );
    ui.addManager( new extendedMouseManager("simpleMouse",true) );
    simpleWindowManager winman = new simpleWindowManager("simpleWindow",true);
    ui.addManager( winman );
    MixedFontManager fontman = new MixedFontManager("simpleFont",true);
    ui.addManager( fontman );
    
     
    RectWindow rwin = new RectWindow("testRect", 100, 55);
    rwin.addTo(winman);
    rwin.dimension.setDim(200,100);
    rwin.area.set(1, 1, 10, 99);
    rwin.Color.set(color(160),color(200),color(80),color(255,50,50),color(200),color(80));
    rwin.setOpaq(100);
    rwin.rotation.setEnable(false);
    
    rwin.addComponent( new CircleShape("CircleShape1",50,50,30,color(200,0,0,200),color(100,0,0,100)) );
    component com = new zuilib.components.Timer("Timer1",1,false);
    rwin.addComponent(com);
    ((zuilib.components.Timer) com).setFunction("onTimer", "onTimer1");
    //win.addComponent( new zuilib.components.Label("Label",77,45,"Hello World!",10) );
    
    LineInput li = new LineInput("Label",77,45,"Hello World!",100,10);
    li.addTo(rwin);
    li.setBlink(4);
    li.cursortext = "l";
    li.Color.set(color(200),color(2,150),color(2,150),color(200),color(24,150),color(22,150));
    li.ActiveColor.set(color(255),color(0,200),color(200,100),color(200),color(12,200),color(255,100));
    //winman.rotation.set(-HALF_PI);
    //winman.scale.set(1.8f);
    
    RectHandle_Circle rhr = new RectHandle_Circle("rhr1",50,10,100,10,1);
    rwin.addComponent(rhr);
    rhr.Color.set(color(200),color(2,150),color(2,150),color(200),color(24,150),color(22,150));
    
    CircleHandle_Circle chc = new CircleHandle_Circle("chc1",50,50,45,10,1);
    int i = rwin.addComponent(chc);
    rwin.setOnTop(i);
    chc.setFunction("onChange", "rotateWindow2");
    chc.Color.set(color(200),color(2,150),color(2,150),color(200),color(24,150),color(22,150));
    
    RectScrollbar rsb = new RectScrollbar("rsb1",50,30,100,10,1);
    rwin.addComponent(rsb);
    rsb.Color.set(color(160),color(200),color(80),color(255,50,50),color(200),color(80));
    
    //'#########################
    
    EyeWindow ewin = new EyeWindow("eye",120,-80);
    winman.addWindow(ewin);
    ewin.dimension.setDim(40);
    ewin.Color.set(color(160),color(200),color(80),color(255,50,50),color(200),color(80));
    ewin.setOpaq(60);
    
    //'#########################
    
    rwin = new RectWindow("paneltest", -100, 55);
    rwin.addTo(winman);
    rwin.dimension.setDim(200,100);
    rwin.area.set(1, 1, 10, 99);
    rwin.Color.set(color(160),color(200),color(80),color(255,50,50),color(200),color(80));
    rwin.setOpaq(100);
    //rwin.rotation.setEnable(false);
    
    zuilib.components.ScrollPanel panel = new zuilib.components.ScrollPanel("ScrollPanel1",27,12,163,41,5);
    panel.addTo(rwin);
    panel.panelGraphic.smooth();
    panel.hscrollbar.position.setY(-5);
    panel.vscrollbar.position.setX(-5);
    panel.components.dimension.set(200, 100);
    panel.addComponent( new CircleShape("CircleShape1",10,10,30,color(200,0,0,200),color(100,0,0,100)) );
    panel.addComponent( new RectScrollbar("rsb1",50,30,100,10,1) );
    ((RectScrollbar) panel.components.get("rsb1")).Color.set(color(160),color(200),color(80),color(255,50,50),color(200),color(80));
    
    li = new LineInput("Label",7,5,"Hello World!",100,10);
    li.addTo(panel.components);
    li.setBlink(4);
    li.cursortext = "l";
    li.Color.set(color(200),color(2,150),color(2,150),color(200),color(24,150),color(22,150));
    li.ActiveColor.set(color(255),color(0,200),color(200,100),color(200),color(12,200),color(255,100));
    
    RectButton rbut = new RectButton("RectB1",22,7);
    rbut.addTo(rwin);
    rbut.dimension.set(5,5);
    rbut.setFunction("mouseClicked", "resetScroll");
    
    //ui.FONT_FILE = "../../GeometricBlack.ttf";
    //fontman.isPFont = false;
    zuilib.components.Label label = new zuilib.components.Label("Label1",20,60,"Hello World!",15);
    label.addTo(rwin);
    NumberEdit numed = new NumberEdit("numedTest",15,80);
    numed.addTo(rwin);
    
    //'#########################
    
    rwin = new RectWindow("listtest", -100, -50);
    //recwin = rwin;
    rwin.addTo(winman);
    rwin.dimension.setDim(200,100);
    rwin.area.set(1, 1, 10, 99);
    rwin.Color.set(color(160),color(200),color(80),color(255,50,50),color(200),color(80));
    rwin.setOpaq(100);
    
    zuilib.components.List list = new zuilib.components.List("testlist",10,0,180,100, new listDynamicArray1D() );
    list.addTo(rwin);
    list.Color.set(color(160),color(200),color(80),color(255,50,50),color(200),color(80));
    list.addComponent( new RectShape("RedEntry",0,0,100,10,color(255,50,50),color(200,0,0)) );
    list.addComponent( new RectShape("GreenEntry",20,0,100,30,color(50,255,50),color(0,200,0)) );
    rbut = new RectButton("RectB1List",10,0);
    list.addComponent( rbut );
    rbut.dimension.setDim(150,7);
    rbut.setFunction("mouseClicked", "toggleGreenEntry");
    rbut.Color.set(color(200),color(2,150),color(2,150),color(200),color(24,150),color(22,150));
    list.addComponent( new RectShape("BlueEntry",40,0,100,10,color(50,50,255),color(0,0,200)) );
    list.get("GreenEntry").visibility.set(false);
    RectHoverButton rhb1 = new RectHoverButton("hovertest",70,0);
    list.addComponent(rhb1);
    rhb1.dimension.set(20,20);
    rhb1.Color.set(color(200),color(2,150),color(2,150),color(200),color(24,150),color(22,150));
    rhb1.hover.countdown = 50;
    rhb1.setFunction("mouseClicked", "chanceHover");
    RectHoverButton rhb2 = new RectHoverButton("hovertest2",0,0);
    rhb1.hover.setObject( rhb2 );
    rhb2.dimension.set(10,10);
    rhb2.Color.set(color(200),color(2,150),color(2,150),color(200),color(24,150),color(22,150));
    rhb2.hover.setObject( new CircleShape("CircleShape2",0,0,30,color(200,0,0,200),color(100,0,0,100)) );
    rhb2.hover.countdown = 50;
    rhb2.setFunction("mouseClicked", "chanceHover");
  }
  
  public void chanceHover(RectHoverButton self) {
    HoverPosition op = self.hover.objectPosition;
    switch(op) {
    case TOPLEFT: op = HoverPosition.TOP; break;
    case TOP: op = HoverPosition.TOPRIGHT; break;
    case TOPRIGHT: op = HoverPosition.RIGHT; break;
    case LEFT: op = HoverPosition.MIDDLE; self.hover.addObjectDimensionToOffset = !self.hover.addObjectDimensionToOffset; break;
    case MIDDLE: op = HoverPosition.TOPLEFT; break;
    case RIGHT: op = HoverPosition.BOTTOMRIGHT; break;
    case BOTTOMLEFT: op = HoverPosition.LEFT; break;
    case BOTTOM: op = HoverPosition.BOTTOMLEFT; break;
    case BOTTOMRIGHT: op = HoverPosition.BOTTOM; break;
    }
    self.hover.objectPosition = op;
  }
  
  public void rotateWindow2(circlehandle self) {
    //print(self.getValue()+" --- ");
    RectWindow rwin = (RectWindow) ((windowmanager) ui.getManager("simpleWindow")).get("paneltest");
    rwin.rotation.set( self.getValue()*2*PI );
    //println(rwin.rotation.get());
  }
  
  public void resetScroll() {
    zuilib.components.ScrollPanel panel = (ScrollPanel) ((windowmanager) ui.getManager("simpleWindow")).get("paneltest").get("ScrollPanel1");
    panel.hscrollbar.setValue(0);
    panel.vscrollbar.setValue(0);
  }
  
  public void onTimer1() {
    simpleWindowManager winman = (simpleWindowManager) ui.get("simpleWindow");
    winman.rotation.set(winman.rotation.get() + 0.01f);
    
  }
  
  public void toggleGreenEntry() {
    component com = ((zuilib.components.List) ((windowmanager) ui.getManager("simpleWindow")).get("listtest").get("testlist")).get("GreenEntry");
    com.visibility.set( !com.isVisible() );
  }

}
