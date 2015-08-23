package zuilib.components;

import java.util.HashMap;

import processing.core.PApplet;
import zuilib.core.component;


public class RadiobuttonControler extends interactivecomponent {
  
  public checkbox[] radiobuttons;
  public boolean enable;
  public int index;
  public int curradiobutton;
  private HashMap<String, Integer> rbnames = new HashMap<String, Integer>();
  
  public RadiobuttonControler(String sname, boolean benable) {
    super(sname,0,0);
    index = 0;
    curradiobutton = -1;
    enable = benable;
    radiobuttons = new checkbox[0];
  }
  
  public void setup() {
    super.setup();
    addFunction("onChange");
  }
  
  public void update() {
    super.update();
    boolean changed = false;
    if(enable) {
      if(curradiobutton > -1) {
        if(!radiobuttons[curradiobutton].isChecked()) {
          curradiobutton = -1;
        }
      }
      for( int i = 0 ; i < index ; i += 1 ) {
        if(i != curradiobutton) {
          if(radiobuttons[i].isChecked()) {
            if(curradiobutton > -1) {
              radiobuttons[curradiobutton].setCheck(false);
            }
            curradiobutton = i;
            changed = true;
          }
        }
      }
      if(changed) {useFunction("onChange");}
    }
  }
  
  public void setEnable(boolean benable) {
    enable = benable;
  }
  
  public int addCheckbox(checkbox newcb) { //please dont give it 'new' checkboxes; create first the checkboxes in a window and then give this created checkbox this method
    radiobuttons = (checkbox[]) PApplet.append(radiobuttons,newcb);
    rbnames.put(newcb.Name.get(), index);
    index += 1;
    return index-1;
  }
  
  public int addCheckbox(component newc) {
    return addCheckbox( (checkbox) newc );
  }
  
  public boolean setCheckbox(int i,checkbox newcb) {
    if( i < index ) {
        radiobuttons[i] = newcb;
        return true;
      } else {
        return false;
    }
  }  
  
  public boolean setCheckbox(int i, component newc) {
    return setCheckbox(i, (checkbox) newc );
  }
  
  public boolean setChecked(int i) {
    if(i < index) {
        if(curradiobutton > -1) {
          radiobuttons[curradiobutton].setCheck(false);
        }
        curradiobutton = i;
        radiobuttons[curradiobutton].setCheck(true);
        useFunction("onChange");
        return true;
      } else {
        return false;
    }
  } 
  
  public boolean setChecked(String sname) {
    Integer i = rbnames.get(sname);
    if(i != null) setChecked(i);
    return false;
  }
  
  public void unCheckAll() {
    if(curradiobutton > -1) {
      radiobuttons[curradiobutton].setCheck(false);
    }
    curradiobutton = -1;
  }
  
}
