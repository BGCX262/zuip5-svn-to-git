package zuilib.utils;

import processing.core.PApplet;
import zuilib.core.*;

public class zuiObjectControler extends propertyObject {
  
  public int length = 0;
  public zuiObject[] objects = new zuiObject[0];
  public Object[] seq;
  public int[] objects_z = new int[0];      // z-index for each object
  public int[] objects_index = new int[0];  // index i for each object in a z level
  public int[] objects_sorted = new int[0]; // array with sorted objects
  
  public zuiObjectControler(zuiAtomObject curParent) {
    setup(curParent);
  }
  
  public zuiObjectControler() {
    return;
  }
  
  public void setup(zuiAtomObject curParent) {
    super.setup(curParent);
    seq = new Object[parentObject.getZUI().MAX_Z_INDEX];
    for( int z = 0 ; z < parentObject.getZUI().MAX_Z_INDEX ; z += 1 ) {
      seq[z] = new int[0];
    }
  }
  
  public int addObject(zuiObject newo) {
    return addObject(newo,newo.getClass().getSimpleName());
  }
  
  public int addObject(zuiObject newo,String debugname) {
    int[] a = (int[]) seq[0];
    if(parentObject.getZUI().DEBUG >= 4) PApplet.println("* Add "+debugname+" "+newo.Name.get()+" ("+newo.getClass().getName()+")");
    newo.install(parentObject,length);
    objects = (zuiObject[]) PApplet.append(objects,newo);
    objects_index = (int[]) PApplet.append(objects_index,a.length);
    objects_z = (int[]) PApplet.append(objects_z,0);
    seq[0] = (int[]) PApplet.append(a,length);
    newo.setup();
    sort();
    length += 1;
    return length-1;
  }
  
  public zuiObject getObjectSorted(int pos) {
    if( pos < length ) {
      return objects[ objects_sorted[ pos ] ];
    } else {
      PApplet.println("[WARNING]: getObjectSorted:  Requested object at sorted pos. "+pos+" returned null.");
      return null;
    }
  }
  
  public int getObjectSortedIndex(int pos) {
    if( pos < length ) {
      return objects_sorted[ pos ];
    } else {
      PApplet.println("[WARNING]: getObjectSortedIndex:  Requested object at sorted pos. "+pos+" returned -1.");
      return -1;
    }
  }
  
  private void sort() {
    objects_sorted = new int[0];
    objects_sorted = PApplet.expand(objects_sorted, objects.length);
    int i = 0;
    int[] a = new int[0];
    for( int z = 0 ; z < parentObject.getZUI().MAX_Z_INDEX ; z +=1 ) {
      a = (int[]) seq[z];
      for( int c = 0 ; c < a.length ; c +=1 ) {
        objects_sorted[i] = a[c];
        i += 1;
      }
    }
  }
  
  public zuiObject getObject(int i) {
    if( i < length ) {
        return objects[i];
      } else {
        PApplet.println("[WARNING]: getObject:  Requested object no. "+i+" returned null.");
        return null;
    }
  }
 
  public zuiObject getObject(String sname) {
    for( int i = 0 ; i < length ; i += 1 ) {
      if(objects[i].Name.equals(sname)) {
        return objects[i];
      }
    }
    PApplet.println("[WARNING]: getObject:  Requested object "+sname+" returned null.");
    return null;
  }
  
  public zuiObject get(int i) {
    return getObject(i);
  }
  
  public zuiObject get(String sname) {
    return getObject(sname);
  }
  
  public zuiObject getSorted(int pos) {
    return getObjectSorted(pos);
  }
  
  public int getSortedIndex(int pos) {
    return getObjectSortedIndex(pos);
  }
  
  public boolean setObject(int i,zuiObject newo) {
    if( i < length ) {
        newo.install(parentObject,i);
        objects[i] = newo;
        newo.setup();
        return true;
      } else {
        if(parentObject.getZUI().DEBUG >= 1) PApplet.println("[WARNING]: setObject:  Setting object "+newo.Name.get()+" on position "+i+" failed.");
        return false;
    }
  }
  
  public boolean setDown(int n) {
    if(n<length) {
      int z = objects_z[n];
      int i = objects_index[n];
      int[] a = (int[]) seq[z];
      int[] dummy = new int[0];
      dummy = PApplet.append(dummy,a[i]);
      seq[z] = (int[]) PApplet.concat( PApplet.subset(a,0,i) , PApplet.subset(a,i+1,a.length-i-1) );
      seq[z] = (int[]) PApplet.concat( dummy,(int[]) seq[z] );
      a = (int[]) seq[z];
      for( i = 0 ; i < a.length ; i +=1 ) {
        objects_index[a[i]] = i;
      }
      sort();
      return true;
    }
    if(parentObject.getZUI().DEBUG >= 1) {
      PApplet.println("[WARNING]: setDown:  Setting object no. "+n+" down failed.");
      PApplet.println("                     Can't find requested object.");
    }
    return false;
  }
  
  public boolean setDown(String sname) {
    for( int i = 0 ; i < length ; i += 1 ) {
      if(objects[i].Name.equals(sname)) {
        return setDown(i);
      }
    }
    if(parentObject.getZUI().DEBUG >= 1) {
      PApplet.println("[WARNING]: setDown:  Setting object "+sname+" down failed.");
      PApplet.println("                     Can't find requested object.");
    }
    return false;
  }
  
  public boolean setOnBottom(int i) {
    if(setZIndex(i,0)) {return setDown(i);}
    return false;
  }
  
  public boolean setOnBottom(String sname) {
    for( int i = 0 ; i < length ; i += 1 ) {
      if(objects[i].Name.equals(sname)) {
        return setOnBottom(i);
      }
    }
    if(parentObject.getZUI().DEBUG >= 1) {
      PApplet.println("[WARNING]: setOnBottom:  Setting object "+sname+" on bottom failed.");
      PApplet.println("                         Can't find requested object.");
    }
    return false;
  }

  public boolean setOnTop(int i) {
    return setZIndex(i,parentObject.getZUI().MAX_Z_INDEX-1);
  }
  
  public boolean setOnTop(String sname) {
    for( int i = 0 ; i < length ; i += 1 ) {
      if(objects[i].Name.equals(sname)) {
        return setOnTop(i);
      }
    }
    if(parentObject.getZUI().DEBUG >= 1) {
      PApplet.println("[WARNING]: setOnTop:  Setting object "+sname+" on top failed.");
      PApplet.println("                      Can't find requested object.");
    }
    return false;
  }
  
  public boolean setUp(int n) {
    if(n<length) {
      int z = objects_z[n];
      int i = objects_index[n];
      int[] a = (int[]) seq[z];
      int[] dummy = (int[]) PApplet.append(a,a[i]);
      seq[z] = (int[]) PApplet.concat( PApplet.subset(dummy,0,i) , PApplet.subset(dummy,i+1,a.length-i) );
      a = (int[]) seq[z];
      for( i = 0 ; i < a.length ; i +=1 ) {
        objects_index[a[i]] = i;
      }
      sort();
      return true;
    }
    if(parentObject.getZUI().DEBUG >= 1) {
      PApplet.println("[WARNING]: setUp:  Setting object no. "+n+" up failed.");
      PApplet.println("                   Can't find requested object.");
    }
    return false;
  }
  
  public boolean setUp(String sname) {
    for( int i = 0 ; i < length ; i += 1 ) {
      if(objects[i].Name.equals(sname)) {
        return setUp(i);
      }
    }
    if(parentObject.getZUI().DEBUG >= 1) {
      PApplet.println("[WARNING]: setUp:  Setting object "+sname+" up failed.");
      PApplet.println("                   Can't find requested object.");
    }
    return false;
  }
  
  public boolean setZIndex(int n, int newz) {
    if(newz < parentObject.getZUI().MAX_Z_INDEX) {
      if (setUp(n)) {
        int z = objects_z[n];
        seq[z] = (int[]) PApplet.shorten((int[]) seq[z]);
        objects_z[n] = newz;
        int[] a = (int[]) seq[newz];
        objects_index[n] = a.length;
        seq[newz] = (int[]) PApplet.append(a,n);
        return setDown(n);
      }
    }
    if(parentObject.getZUI().DEBUG >= 1) {
      PApplet.println("[WARNING]: setZIndex:  Setting z-index of object no. "+n+" failed.");
      PApplet.println("                       Given z-index "+newz+" is bigger than max. z-index "+parentObject.getZUI().MAX_Z_INDEX+".");
    }
    return false;
  }
  
  public boolean setZIndex(String sname, int newz) {
    for( int i = 0 ; i < length ; i += 1 ) {
      if(objects[i].Name.equals(sname)) {
        return setZIndex(i,newz);
      }
    }
    if(parentObject.getZUI().DEBUG >= 1) {
      PApplet.println("[WARNING]: setZIndex:  Setting z-index of object "+sname+" failed.");
      PApplet.println("                   Can't find requested object.");
    }
    return false;
  }
  
}
