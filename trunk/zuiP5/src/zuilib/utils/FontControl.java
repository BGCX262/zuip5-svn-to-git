package zuilib.utils;

import zuilib.manager.fontmanager;


public class FontControl {
  
  public String Name;
  public zuiFont Font;
  public fontmanager Parent;
  
  public FontControl(fontmanager curparent,zuiFont newfont) {
    Name = newfont.Name;
    Parent = curparent;
    Font = newfont;
  }
  
  public zuiFont get() {
    return Font;
  }
  
}
