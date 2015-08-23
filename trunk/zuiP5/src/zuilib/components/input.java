package zuilib.components;

import processing.core.PApplet;
import processing.core.PConstants;
import zuilib.properties.ColorController;
import zuilib.properties.RectDimension;
import zuilib.utils.vector;



public class input extends button {
  
  public RectDimension dimension;
  public ColorController ActiveColor;
  private ColorController normal_color;
  public String[] Content;
  public Label[] Text;
  public vector cursor;
  public boolean active;
  public boolean readonly;
  public String cursortext;
  private int frame;
  private int max_frames;
  private int index;
  private float fontheight;
  private String fontfile;
  private float longest_text;
  private boolean cursor_hidden;
  
  public input(String sname, float fx, float fy,float ffontheight) {
    super(sname, fx, fy);
    fontfile = "";
    input_init(sname,fx,fy,ffontheight);
  }
  
  public input(String sname, float fx, float fy, String curFontFile, float ffontheight) {
    super(sname, fx, fy);
    fontfile = curFontFile;
    input_init(sname,fx,fy,ffontheight);
  }
  
  private void input_init(String sname, float fx, float fy,float ffontheight) {
    mode = PConstants.CORNER;
    active = readonly = false;
    frame = index = 0;
    longest_text = 0;
    max_frames = 22;
    Content = new String[0];
    Text = new Label[0];
    cursor = new vector(0,0);
    fontheight = ffontheight;
    cursor_hidden = true;
    cursortext = "|";
    dimension = new RectDimension();
    ActiveColor = new ColorController();
  }
  
  public void setup() {
    super.setup();
    dimension.setup(this);
    ActiveColor.setup(this);
    addFunction("onEnter");
    normal_color = Color;
  }
  
  public int addLine(String newtext) {
    Content = (String[]) PApplet.append(Content, newtext);
    Label newone = new Label(Name.get()+"_Line_"+index,2,index*(fontheight+3)+2,newtext,fontfile,fontheight);
    newone.setParentObject(this);
    Text = (Label[]) PApplet.append(Text, newone );
    newone.setup();
    newone.Color = Color;
    newone.rotation.setEnable(false);
    newone.scale.setEnable(false);
    if(newone.dimension.width > longest_text) {longest_text = newone.dimension.width;}
    if(index == cursor.y) {
      cursor.x = newtext.length();
    }
    index += 1;
    dimension.set(longest_text+4,index*(fontheight+3)+4);
    return index-1;
  }
  
  private void checkCursorPos() {
    if(PApplet.round(cursor.x) >= Content[PApplet.round(cursor.y)].length() ) {
      cursor.x = Content[PApplet.round(cursor.y)].length();
    }
  }
  
  public boolean setText(int pos, String newtext) {
    if(pos < index) {
      Content[pos] = newtext;
      Text[pos].setText(newtext);
      if(PApplet.round(cursor.y) == pos) {checkCursorPos();}
    }
    return false;
  }
  
  public boolean over() {
    dimension.over = overRect(dimension.width, dimension.height, mode);
    return dimension.over;
  }
  
  public void draw() {
    super.draw();
    Color.doBackground();
  }
  
  public void postdraw() {
    Color.doForeground();
    for(int i = 0 ; i < Text.length ; i += 1) {
      Text[i].display();
    }
    super.postdraw();
  }
  
  public void setBlink(int iframe) {
    frame = 0;
    max_frames = PApplet.abs(iframe);
  }
  
  public void setReadonly(boolean bool) {
    readonly = bool;
    if(readonly && active) {active = false;}
  }
  
  public boolean isReadonly() {
    return readonly;
  }
  
  public void mousePressed() {
    super.mousePressed();
    active = (getLock() && pressed && !readonly);
    if(active) {
      normal_color = Color;
      Color = ActiveColor;
    } else {
      Color = normal_color;
      Text[(int) cursor.y].setText(Content[(int) cursor.y]);
    }
  }
  
  public void update() {
    super.update();
    for(int i = 0 ; i < Text.length ; i += 1) {
      Text[i].update();
    }
    if(active) {
      if(!cursor_hidden) {
        String str1 = Content[(int) cursor.y].substring(0,(int) cursor.x);
        String str2 = Content[(int) cursor.y].substring((int) cursor.x);
        String strg = str1 + cursortext + str2;
        Text[(int) cursor.y].setText(strg);
      }
      
      frame = (frame+1)%max_frames;
      if(frame == 0) {
        cursor_hidden = !cursor_hidden;
        if(cursor_hidden) {
            String str1 = Content[(int) cursor.y].substring(0,(int) cursor.x);
            String str2 = Content[(int) cursor.y].substring((int) cursor.x);
            String strg = str1 + " " + str2;
            Text[(int) cursor.y].setText(strg);
          } else {
            Text[(int) cursor.y].setText(Content[(int) cursor.y]);
        }
      }
    }
  }
  
  public void keyTyped(char key) {
    if(active) {
      String str1 = Content[(int) cursor.y].substring(0,(int) cursor.x);
      String str2 = Content[(int) cursor.y].substring((int) cursor.x);
      Content[(int) cursor.y] = str1 + key + str2;
      cursor.x += 1;
      Text[(int) cursor.y].setText(Content[(int) cursor.y]);
    }
  }
  
  public void keyPressed(char key) {
    if(active) {
      switch(key) {
        case PConstants.ENTER:
            useFunction("onEnter");
          break;
        case PConstants.LEFT:
            cursor.x = PApplet.constrain(cursor.x-1,0,Content[(int) cursor.y].length());
          break;
        case PConstants.RIGHT:
            cursor.x = PApplet.constrain(cursor.x+1,0,Content[(int) cursor.y].length());
          break;
        case PConstants.UP:
            cursor.y = PApplet.constrain(cursor.y-1,0,index-1);
            checkCursorPos();
          break;
        case PConstants.DOWN:
            cursor.y = PApplet.constrain(cursor.y+1,0,index-1);
            checkCursorPos();
          break;
        case PConstants.BACKSPACE:
            if(cursor.x > 0) {
              cursor.x -= 1;
              String str1 = Content[(int) cursor.y].substring(0,(int) cursor.x);
              String str2 = Content[(int) cursor.y].substring((int) (cursor.x+1));
              Content[(int) cursor.y] = str1 + str2;
              Text[(int) cursor.y].setText(Content[(int) cursor.y]);
            }
          break;
        case PConstants.DELETE:
            if(cursor.x < Content[(int) cursor.y].length()) {
              String str1 = Content[(int) cursor.y].substring(0,(int) cursor.x);
              String str2 = Content[(int) cursor.y].substring((int) cursor.x+1);
              Content[(int) cursor.y] = str1 + str2;
              Text[(int) cursor.y].setText(Content[(int) cursor.y]);
            }
          break;
      }
        
    }
  }

}

/*
  private void update() {
    if(!showenContent.equals(content) || showenCursor_pos != cursor_pos) {
      showenContent = content;
      showenCursor_pos = cursor_pos;
      
      showenText = content;
      showenCP = cursor_pos;
      text.setText(showenText);
      int len = 0;
      while(text.width > size) {
        len = showenText.length();
        if(showenCP >= len/2) {
            showenText = showenText.substring(1);
            showenCP -= 1;
          } else {
            showenText = showenText.substring(0,len-1);
        }
        text.setText(showenText);
      }
    }
    
    if(active) {
      if(showenCP == 0) {
          text.setText("");
          text2.setText(showenText);
        } else {
          text.setText(showenText.substring(0,showenCP));
          text2.setText(showenText.substring(showenCP));
      }
    } else { text.setText(showenText);}
*/