package jrc.engine;

public class GameMap extends DataBuffer<Integer> {

    public GameMap(int width, int height, String mapData) {
        super(width, height, () -> 0 );
        
        final char player = '2';
        final char wall = '1';
        
        for (var index = 0; index < mapData.length(); ++index) {
            var c = mapData.charAt(index);
            var value = 0;
            switch (c) {
            case wall: value = 1; break;
            case player: value = 2; break;
            default:
                value = 0;
            }
            set(index, value);
        }
    }
}
