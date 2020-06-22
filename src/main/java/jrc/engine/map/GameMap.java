package jrc.engine.map;

import java.util.Map;
import java.util.stream.IntStream;

import jrc.engine.Color;
import jrc.engine.DataBuffer;
import jrc.engine.Framebuffer;
import jrc.engine.type.Vector2D;
import lombok.Getter;
import lombok.Setter;

public class GameMap extends DataBuffer<CellType> {
    
    @Getter @Setter
    private int cellSize = 30;
    // cell size
    // ceiling height

    // CellType = is it wall/door/grass etc, ( enum ? )
    // CellShared = shared static props like color, texture, etc for all cells of one type
    // MapCell = Type is link to CellType, + individual cell properties.
    
    private static Map<Character, CellType> dataToCellType = Map.of(
            '#', CellType.WALL,
            '2', CellType.SPAWN_POINT);
    
    private static Map<CellType, CellSharedData> valueToColor = Map.ofEntries(
            Map.entry(CellType.WALL, CellSharedData.builder().color(Color.WHITE).build()),
            Map.entry(CellType.SPAWN_POINT, CellSharedData.builder().color(Color.from(0, 255, 255)).build()));

    public GameMap(int width, int height, String mapData) {
        super(width, height, () -> CellType.EMPTY );
        for (var index = 0; index < mapData.length(); ++index) {
            var c = mapData.charAt(index);
            var value = dataToCellType.getOrDefault(c, CellType.EMPTY);
            set(index, value);
        }
    }

    public void drawTo(Framebuffer image) {
        IntStream.range(0, getHeight()).forEach(y -> {
            IntStream.range(0, getWidth()).forEach(x -> {
                var value = get(x, y);
                var c = valueToColor.getOrDefault(value, CellSharedData.EMPTY);
                image.drawRect(new Vector2D(x * cellSize + 1, y * cellSize+1), cellSize-2, c.getColor());
            });
        });
    }
}
