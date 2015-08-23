package zuilib.extra;

import processing.core.PApplet;
import net.nexttext.Book;
import zuilib.manager.simplefontmanager;
import zuilib.utils.zuiFont;

public class NextTextFontManager extends simplefontmanager {
  
  public Book book;

  public NextTextFontManager(String sname, boolean benable) {
    super(sname, benable);
  }
  
  public void setup() {
    super.setup();
    book = new Book(getPApplet());
    this.link("draw");
  }
  
  public Book getBook() {
    return book;
  }
  
  public void update() {
    if(enable) book.step();
  }
  
  public void pre() {
    if(enable) update();
  }

  public void draw() {return;}
  public void predraw() {return;}
  public void postdraw() {return;}
  
  
  public int addFont(String newf) {
    if(getZUI().DEBUG >= 5) PApplet.println("* Add NextText Font "+newf);
    return addFont(new NextTextFont(this,newf));
  }
  
  public int addFont(NextTextFont newfont) {
    return super.addFont(newfont);
  }
  
  public zuiFont getFont(String sname) {
    zuiFont result = super.getFont(sname);
    if(result != null) return result;
    return getFont( addFont(sname) );
  }

  public boolean setFont(int i,String newf) {
    return super.setFont(i, new NextTextFont(this,newf) );
  }
  
  public boolean set(int i, String newf) {
    return setFont(i,newf);
  }

}
