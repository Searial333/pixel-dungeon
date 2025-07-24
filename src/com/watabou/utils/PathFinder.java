package com.watabou.utils;

public class PathFinder {
    private static int mapWidth;
    private static int mapHeight;
    private static int mapLength;
    
    public static void setMapSize(int width, int height) {
        mapWidth = width;
        mapHeight = height;
        mapLength = width * height;
    }
    
    public static int getStep(int from, int to, boolean[] passable) {
        // Simplified pathfinding - move toward target
        if (from == to) return -1;
        
        int fromX = from % mapWidth;
        int fromY = from / mapWidth;
        int toX = to % mapWidth;
        int toY = to / mapWidth;
        
        // Try to move closer to target
        int dx = Integer.compare(toX, fromX);
        int dy = Integer.compare(toY, fromY);
        
        int target = from + dx + dy * mapWidth;
        
        if (target >= 0 && target < mapLength && passable[target]) {
            return target;
        }
        
        // If direct path blocked, try alternatives
        if (dx != 0 && passable[from + dx]) {
            return from + dx;
        }
        if (dy != 0 && passable[from + dy * mapWidth]) {
            return from + dy * mapWidth;
        }
        
        return -1;
    }
    
    public static int getStepBack(int cur, int from, boolean[] passable) {
        // Move away from 'from' position
        int curX = cur % mapWidth;
        int curY = cur / mapWidth;
        int fromX = from % mapWidth;
        int fromY = from / mapWidth;
        
        int dx = Integer.compare(curX, fromX);
        int dy = Integer.compare(curY, fromY);
        
        int target = cur + dx + dy * mapWidth;
        
        if (target >= 0 && target < mapLength && passable[target]) {
            return target;
        }
        
        return -1;
    }
}
