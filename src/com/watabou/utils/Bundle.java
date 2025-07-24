package com.watabou.utils;

import java.io.*;
import java.util.*;

public class Bundle {
    private Map<String, Object> data = new HashMap<>();
    private static Map<Class<?>, String> aliases = new HashMap<>();
    
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
    
    public void put(String key, Collection<?> value) {
        data.put(key, value);
    }
    
    public void put(String key, int[] value) {
        data.put(key, value);
    }
    
    public void put(String key, boolean[] value) {
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
    
    public Object get(String key) {
        return data.get(key);
    }
    
    public Bundle getBundle(String key) {
        Object value = data.get(key);
        return value instanceof Bundle ? (Bundle) value : new Bundle();
    }
    
    public int[] getIntArray(String key) {
        Object value = data.get(key);
        return value instanceof int[] ? (int[]) value : new int[0];
    }
    
    public boolean[] getBooleanArray(String key) {
        Object value = data.get(key);
        return value instanceof boolean[] ? (boolean[]) value : new boolean[0];
    }
    
    @SuppressWarnings("unchecked")
    public Collection<Bundlable> getCollection(String key) {
        Object value = data.get(key);
        if (value instanceof Collection) {
            return (Collection<Bundlable>) value;
        }
        return new ArrayList<>();
    }
    
    public boolean contains(String key) {
        return data.containsKey(key);
    }
    
    public boolean isNull() {
        return data.isEmpty();
    }
    
    // Static methods for file I/O
    public static Bundle read(InputStream input) throws IOException {
        // Simplified implementation - in real game would use proper serialization
        Bundle bundle = new Bundle();
        // For now, return empty bundle - file I/O would be implemented properly
        return bundle;
    }
    
    public static void write(Bundle bundle, OutputStream output) throws IOException {
        // Simplified implementation - in real game would use proper serialization
        // For now, do nothing - file I/O would be implemented properly
    }
    
    public static void addAlias(Class<?> clazz, String alias) {
        aliases.put(clazz, alias);
    }
}
