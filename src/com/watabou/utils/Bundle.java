package com.watabou.utils;

import java.util.HashMap;
import java.util.Map;

public class Bundle {
    private Map<String, Object> data = new HashMap<>();
    
    public void put(String key, int value) {
        data.put(key, value);
    }
    
    public void put(String key, float value) {
        data.put(key, value);
    }
    
    public void put(String key, boolean value) {
        data.put(key, value);
    }
    
    public void put(String key, String value) {
        data.put(key, value);
    }
    
    public void put(String key, Object value) {
        data.put(key, value);
    }
    
    public int getInt(String key) {
        Object value = data.get(key);
        return value instanceof Integer ? (Integer) value : 0;
    }
    
    public float getFloat(String key) {
        Object value = data.get(key);
        return value instanceof Float ? (Float) value : 0f;
    }
    
    public boolean getBoolean(String key) {
        Object value = data.get(key);
        return value instanceof Boolean ? (Boolean) value : false;
    }
    
    public String getString(String key) {
        Object value = data.get(key);
        return value instanceof String ? (String) value : "";
    }
    
    public boolean contains(String key) {
        return data.containsKey(key);
    }
}
