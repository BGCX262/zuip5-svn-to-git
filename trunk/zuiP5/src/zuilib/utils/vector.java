package zuilib.utils;

import processing.core.PApplet;

public class vector {
  public static float Vec2Ang(vector v1, vector v2) {
    return VecAng(v1) - VecAng(v2);
  }
  
  public static vector VecAdd(vector v1, vector v2) {
    return new vector( v1.x+v2.x , v1.y+v2.y );
  }
  
  public static float VecAng(vector v1) {
    return PApplet.atan2(v1.y,v1.x);
  }
  
  public static float VecAng2(vector v1) {
    float ang = PApplet.atan2(v1.y,v1.x);
    if(ang < 0) {ang += PApplet.PI;}
    return ang;
  }
  
  public static float VecAng2(vector v1, vector v2) {
    return VecAng2(v1) - VecAng2(v2);
  }
  
  public static vector VecCopy(vector v1) {
    return new vector(v1.x,v1.y);
  }

  public static vector VecMul(vector v1, float r) {
    return new vector( v1.x*r , v1.y*r );
  }
  
  public static vector VecNorm(vector v1) {
    return VecMul(v1, 1 / VecVal(v1) );
  }

  public static vector VecRot(vector v1, float angle) {
    vector v = VecCopy(v1);
    if(angle != 0) {
      float r = VecVal(v);
      float a = VecAng(v);
      v.x = r*PApplet.cos(a+angle);
      v.y = r*PApplet.sin(a+angle);
    }
    return v;
  }
  
  public static float VecScalar(vector v1, vector v2) {
    return v1.x*v2.x + v1.y+v2.y;
  }

  public static vector VecSub(vector v1, vector v2) {
    return new vector( v1.x-v2.x , v1.y-v2.y );
  }
  
  public static float VecVal(vector v1) {
    return v1.getValue();
  }

  public float x,y;
  
  public vector(float ix, float iy) {
    x = ix;
    y = iy;
  }

  public vector(vector v) {
    x = v.x;
    y = v.y;
  }
  
  public float getValue() {
    return PApplet.sqrt(x*x+y*y);
  }

  public void set(float ix, float iy) {
    x = ix;
    y = iy;
  }
  
  public void Set(vector v) {
    x = v.x;
    y = v.y;
  }

  public float Vec2Ang(vector v) {
    return Ang() - v.Ang();
  }
  
  public float Vec2Ang2(vector v) {
    return Ang2() - v.Ang2();
  }

  public void Add(vector v) {
    x += v.x;
    y += v.y;
  }

  public float Ang() {
    return PApplet.atan2(y,x);
  }
  
  public float Ang2() {
    float ang = PApplet.atan2(y,x);
    if(ang < 0) {ang += PApplet.PI;}
    return ang;
  }

  public vector Copy() {
    return this;
  }
  
  public void Mul(float r) {
    x *= r;
    y *= r;
  }

  public void Norm() {
    Mul( 1 / VecVal(this) );
  }
  
  public void Rot(float angle) {
    if(angle != 0) {
      float r = VecVal(this);
      float a = VecAng(this);
      x = r*PApplet.cos(a+angle);
      y = r*PApplet.sin(a+angle);
    }
  }

  public float Scalar(vector v) {
    return x*v.x + y+v.y;
  }
  
  public void Sub(vector v) {
    x -= v.x;
    y -= v.y;
  }

}
