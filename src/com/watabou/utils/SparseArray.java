package com.watabou.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;

public class SparseArray<T> {
    private Map<Integer, T> data = new HashMap<>();
    
    public T get(int key) {
        return data.get(key);
    }
    
    public void put(int key, T value) {
        data.put(key, value);
    }
    
    public T remove(int key) {
        return data.remove(key);
    }
    
    public int size() {
        return data.size();
    }
    
    public T valueAt(int index) {
        // This is a simplified implementation
        // In reality, you'd need to maintain ordering
        ArrayList<T> values = new ArrayList<>(data.values());
        if (index >= 0 && index < values.size()) {
            return values.get(index);
        }
        return null;
    }
    
    public Collection<T> values() {
        return data.values();
    }
    
    public void clear() {
        data.clear();
    }
}
