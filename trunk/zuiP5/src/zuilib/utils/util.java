package zuilib.utils;

import processing.core.PApplet;
import processing.core.PConstants;

public class util {

  public static boolean inAngleRange(float value, float abs_ang, float range) {
    float dummy = abs_ang + range;
    float dummyL = PApplet.min(dummy,abs_ang);
    float dummyH = PApplet.max(dummy,abs_ang);
    if(dummyL < 0 && dummyH == 0) {dummyH = PConstants.TWO_PI;}
    while(dummyL < 0) { dummyL += PConstants.TWO_PI;}
    while(dummyH < 0) { dummyH += PConstants.TWO_PI;}
    while(value  < 0) { value  += PConstants.TWO_PI;}
    float low  = PApplet.min(dummyL,dummyH);
    float high = PApplet.max(dummyL,dummyH);
    if(low < range && high > PConstants.TWO_PI-range) {
      dummy = high;
      high = low + PConstants.TWO_PI;
      low = dummy;
    }
    if(high > PConstants.TWO_PI && value < low) { value += PConstants.TWO_PI;}
  
    //if(ZUI_DEBUG) {print(PApplet.degrees(value)+" in ["+PApplet.degrees(low)+","+PApplet.degrees(high)+"] == ");}
    return inRange(value,low,high);
  }

  public static boolean inRange(float value,float low, float high) {
    return (value >= low && value <= high); 
  }

  public static boolean inRangeS(float value,float f1, float f2) {
    return inRange(value,PApplet.min(f1,f2),PApplet.max(f1,f2));
  }

  public static float shortFloat(float num, int zeronr) {
    int i = PApplet.parseInt( num*PApplet.pow(10,zeronr) );
    return i/PApplet.pow(10,zeronr);
  }

}
