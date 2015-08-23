import dynamicLoad.FieldLoad;
import dynamicLoad.FunctionLoad;
import dynamicLoad.pappletLaunch;
import processing.core.PApplet;


public class dynamicLoadP5 extends PApplet {
  private static final long serialVersionUID = 7332350191370386223L;
  
  pappletLaunch pl;

  public void setup() {
    //noLoop();
    println(this.getClass());
    FunctionLoad fl = new FunctionLoad(2,"func_test",this);
    fl.invoke();
    pl = new pappletLaunch(this,"Bounce");
    println("-------->  tada !!!!!!!");
  }
  
  public void draw() {
    pl.display();
  }
  
  public void func_test() {
    println();
    println("Function test resolved.");
    FieldLoad fl_width = new FieldLoad(2,"width",this);
    println("Loaded width: "+fl_width.get());
  }

}
