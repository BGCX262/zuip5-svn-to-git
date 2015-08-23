package zuilib.extra;

import java.awt.Font;

import net.nexttext.Book;
import net.nexttext.TextPage;

import processing.core.PApplet;
import processing.core.PGraphics;

import zuilib.utils.zuiFont;

public class NextTextFont extends zuiFont {
  
  public Font font;

  public NextTextFont(NextTextFontManager curParent, String sname) {
    super(curParent, sname);
    font = curParent.book.loadFont(Name);
  }
  
  public float textWidth(String text, float size) {
    Book book = ((NextTextFontManager) getParentObject()).book;
    TextPage page = (TextPage) book.getPage(text);
    if(page == null) {
      return 0f;
    }
    return page.getTextRoot().getBounds().width;
  }
  
  public void display(PGraphics graphic, float x, float y, String text, float size) {
    Book book = ((NextTextFontManager) getParentObject()).book;
    TextPage page = (TextPage) book.getPage(text);
    if(page == null) {
      book.addPage(text);
      page = (TextPage) book.getPage(text);
      book.addText(text,PApplet.round(x),PApplet.round(y),text);
    }
    book.textFont(font, size);
    book.drawPage(text);
  }

}
