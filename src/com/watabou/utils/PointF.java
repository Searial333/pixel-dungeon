package com.watabou.utils;

public class PointF {
    public float x;
    public float y;
    
    public PointF() {
        this(0, 0);
    }
    
    public PointF(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public PointF(PointF p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    public PointF set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }
    
    public float length() {
        return (float)Math.sqrt(x * x + y * y);
    }
    
    public PointF normalize() {
        float length = length();
        if (length != 0) {
            x /= length;
            y /= length;
        }
        return this;
    }
}
