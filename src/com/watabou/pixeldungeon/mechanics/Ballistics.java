package com.watabou.pixeldungeon.mechanics;

import com.watabou.pixeldungeon.levels.Level;
import com.watabou.utils.PointF;

public class Ballistics {
    
    public static int[] trace = new int[Level.LENGTH];
    public static int distance;
    
    public static void cast(int from, int to, boolean magic, boolean hitChars) {
        PointF start = new PointF((from % Level.WIDTH) + 0.5f, (from / Level.WIDTH) + 0.5f);
        PointF end = new PointF((to % Level.WIDTH) + 0.5f, (to / Level.WIDTH) + 0.5f);
        
        // Simple line casting algorithm
        float dx = end.x - start.x;
        float dy = end.y - start.y;
        
        float length = (float)Math.sqrt(dx * dx + dy * dy);
        if (length == 0) {
            trace[0] = from;
            distance = 0;
            return;
        }
        
        dx /= length;
        dy /= length;
        
        distance = 0;
        float x = start.x;
        float y = start.y;
        
        while (distance < Level.LENGTH - 1) {
            int cell = (int)x + (int)y * Level.WIDTH;
            trace[distance] = cell;
            
            if (cell == to) {
                break;
            }
            
            if (!Level.passable[cell] && distance > 0) {
                break;
            }
            
            x += dx;
            y += dy;
            distance++;
        }
    }
}
