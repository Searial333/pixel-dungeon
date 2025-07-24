package com.watabou.noosa;

import java.io.*;

public class Game {
    public static Game instance = new Game();
    public static final int MODE_PRIVATE = 0;
    public static String version = "1.0.0";
    
    public static void switchScene(Class<?> sceneClass) {
        // Simple implementation for switching scenes
        System.out.println("Switching to scene: " + sceneClass.getSimpleName());
    }
    
    public OutputStream openFileOutput(String fileName, int mode) throws IOException {
        // Simplified file output - in real implementation would handle Android Context
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        return new FileOutputStream(file);
    }
    
    public InputStream openFileInput(String fileName) throws IOException {
        // Simplified file input - in real implementation would handle Android Context  
        return new FileInputStream(new File(fileName));
    }
    
    public boolean deleteFile(String fileName) {
        // Simplified file deletion
        return new File(fileName).delete();
    }
}
