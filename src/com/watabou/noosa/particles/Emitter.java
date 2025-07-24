package com.watabou.noosa.particles;

import com.watabou.noosa.Visual;

public class Emitter extends Visual {
    
    public Object burst(Object factory, int count) {
        System.out.println("Emitter burst: " + count + " particles");
        return null;
    }
    
    public void pos(Visual visual) {
        // Position the emitter at the visual's location
    }
    
    public static abstract class Factory {
        public abstract void emit(Emitter emitter, int index, float x, float y);
    }
}
