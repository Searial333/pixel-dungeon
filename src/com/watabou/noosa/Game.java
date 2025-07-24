package com.watabou.noosa;

public class Game {
    public static void switchScene(Class<?> sceneClass) {
        // Simple implementation for switching scenes
        System.out.println("Switching to scene: " + sceneClass.getSimpleName());
    }
}
