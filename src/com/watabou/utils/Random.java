package com.watabou.utils;

import java.util.List;

public class Random {
    private static final java.util.Random r = new java.util.Random();
    
    public static int IntRange(int min, int max) {
        return min + r.nextInt(max - min + 1);
    }
    
    public static int Int(int max) {
        return r.nextInt(max);
    }
    
    public static int Int(int min, int max) {
        return IntRange(min, max - 1);
    }
    
    public static float Float() {
        return r.nextFloat();
    }
    
    public static float Float(float min, float max) {
        return min + r.nextFloat() * (max - min);
    }
    
    public static <T> T element(List<T> list) {
        if (list.isEmpty()) return null;
        return list.get(r.nextInt(list.size()));
    }
    
    public static <T> T element(T[] array) {
        if (array.length == 0) return null;
        return array[r.nextInt(array.length)];
    }
}
