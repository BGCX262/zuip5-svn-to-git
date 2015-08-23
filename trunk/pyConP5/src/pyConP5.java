import betraylib.Judas;
import betraylib.objectTree;
import processing.core.PApplet;
import pyConnect.pyClient;


public class pyConP5 extends PApplet {
  private static final long serialVersionUID = 5085008399240905635L;
  
  public pyClient ppi;
  public Judas jude;

  public void setup() {
    jude = new Judas(this);
    ppi = new pyClient(this, "http://127.0.0.1:8765");
    //ppi.connect("test","");
    background(8765);
    size(200,55);
    noLoop();
    objectTree tree = jude.getAll(false);
    println("--------------------------------------------------------------");
    readTree(tree,"| ");
    println("--------------------------------------------------------------");
  }
  
  public void readTree(objectTree leave,String space) {
    println(space+leave.name+" ("+leave.classname+") ["+leave.length+"]");
    for(int i = 0 ; i < leave.length ; i += 1) {
      readTree(leave.list[i],space+"   ");
    }
  }

  public void mousePressed() {
    int sum = mouseX+mouseY;
    print(sum);
    print(" = ");
    println( ppi.execute("test","Mouse: X = "+mouseX+"  Y = "+mouseY) );
    if(sum < 21) {
      if(sum < 5) {
        print("Stop Server = ");
        println( ppi.stop() );
        return;
      }
      print("Ping Server = ");
      println( ppi.ping("test") );
    }
  }

}
