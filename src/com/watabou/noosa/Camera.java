package com.watabou.noosa;

public class Camera {
    public static Camera main = new Camera();
    
    public void shake(float power, float duration) {
        // Simple implementation for camera shake
        System.out.println("Camera shake: power=" + power + " duration=" + duration);
    }
}
