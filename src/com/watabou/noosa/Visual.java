package com.watabou.noosa;

public class Visual {
    public Group parent;
    
    public Emitter emitter() {
        return new Emitter();
    }
    
    public static class Emitter {
        public Object burst(Object factory, int count) {
            System.out.println("Emitter burst: " + count + " particles");
            return null;
        }
    }
}
