package jrc.engine;

import java.util.List;

import jrc.engine.map.GameMap;
import jrc.engine.type.Vector2D;

public class Raycaster {
    static class RayCastResult {
        private float dist;
        private Vector2D hitPoint;
    }
    
    public static List<RayCastResult> solve(GameMap gameMap, Player player, int rayCount) {
        return List.of();
    }
    
    public static RayCastResult solve(GameMap gameMap, Vector2D position, Vector2D viewRay) {
        return null;
    }
}
