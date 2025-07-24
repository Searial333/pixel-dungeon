package com.watabou.noosa.audio;

public class Sample {
    public static final Sample INSTANCE = new Sample();
    
    public void play(String sound, float volume, float pitch, float pan) {
        // Simple implementation
        System.out.println("Playing sound: " + sound);
    }
    
    public void play(String sound) {
        play(sound, 1.0f, 1.0f, 0.0f);
    }
}
