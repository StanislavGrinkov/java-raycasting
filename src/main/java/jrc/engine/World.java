package jrc.engine;

import jrc.engine.map.GameMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class World {
    
    private final GameMap map;
    private final Player player;
    
    public void drawTo(Framebuffer image) {
        // transform player coords to map
        // and then to image
        // draw map
        // draw player
        map.drawTo(image);
        player.drawTo(image, map.getCellSize());
    }
}
