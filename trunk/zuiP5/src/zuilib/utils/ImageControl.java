package zuilib.utils;

import processing.core.PImage;
import zuilib.manager.imagemanager;


public class ImageControl {
  
  public String Name;
  public PImage Image;
  public imagemanager Parent;
  
  public ImageControl(String sname,imagemanager curparent) {
    Name = sname;
    Parent = curparent;
    Image = Parent.getPApplet().loadImage(sname);
  }
  
  public PImage get() {
    return Image;
  }
  
}