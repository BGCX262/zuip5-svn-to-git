package zuilib.extra;

import processing.core.PApplet;
import zuilib.extra.NextTextFontManager;
import zuilib.utils.zuiPFont;

public class MixedFontManager extends NextTextFontManager {
  
  public boolean isPFont;

  public MixedFontManager(String sname, boolean benable) {
    super(sname, benable);
    isPFont = true;
  }
  
  public int addPFont(String newf) {
    if(getZUI().DEBUG >= 5) PApplet.println("* Add PFont "+newf);
    return addFont( new zuiPFont(this,newf) );
  }
  
  public int addNextTextFont(String newf) {
    return super.addFont(newf);
  }
  
  public int addFont(String newf) {
    if(isPFont) return addPFont(newf);
    return addNextTextFont(newf);
  }

  public boolean setFont(int i,String newf) {
    if(isPFont) return setPFont(i,newf);
    return setNextTextFont(i,newf);
  }
  
  public boolean set(int i, String newf) {
    return setFont(i,newf);
  }
  
  public boolean setNextTextFont(int i,String newf) {
    return setFont(i, new NextTextFont(this,newf) );
  }
  
  public boolean setPFont(int i,String newf) {
    return setFont(i, new zuiPFont(this,newf) );
  }

}
