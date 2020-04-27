package jrc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.IntStream;

import jrc.engine.Color;
import jrc.engine.Framebuffer;
import jrc.engine.GameMap;
import jrc.engine.writer.TargaWriter;

public class App {
    public static void main( String[] args )
    {
        var image = new Framebuffer(512, 256);    
        
        IntStream.range(0, image.getWidth())
        .forEach(x -> {
            double x_f = (x + 1) / (double) image.getWidth();
            IntStream.range(0, image.getHeight())
                .forEach(y -> {                    
                    double y_f = (y + 1) / (double) image.getHeight();
                    var c = Color.from(x_f, y_f, 0.2);
                    image.set(x, y, c);
                });
        });
        
        var map = initMap();
        
        drawMapToImage(map, image);

        try (var stream = Files.newOutputStream(Paths.get(".", "target", "output.tga"));
            var writer = new TargaWriter(stream);) {            
            writer.write(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static Map<Integer, Color> intToColor = Map.ofEntries(
            Map.entry(1, Color.WHITE),
            Map.entry(2, Color.from(0, 255, 255)));
    
    private static void drawMapToImage(GameMap map, Framebuffer image) {
        int size = 20;
        IntStream.range(0, map.getHeight()).forEach(y -> {
            IntStream.range(0, map.getWidth()).forEach(x -> {
                var value = map.get(x, y);
                var c = intToColor.getOrDefault(value, Color.NONE);
                drawRect(x * size, y * size, size, c, image);
            });
        });
    }

    private static GameMap initMap() {
        var mapData =
                  "1111111111"
                + "1        1"
                + "1 1111 1 1"
                + "1  1   1 1"
                + "1    1 1 1"
                + "1 2   1  1"
                + "1     1  1"
                + "1   1 1  1"
                + "1 1 1    1"
                + "1111111111";
        return new GameMap(10, 10, mapData);
    }

    public static void drawRect(int startX, int startY, int size, Color c, Framebuffer image) {
        int endX = Math.min(startX + size, image.getWidth());
        int endY = Math.min(startY + size, image.getHeight());
        
        IntStream.range(startY, endY).forEach(y -> {
              IntStream.range(startX, endX).forEach(x -> {
                  image.set(x, y, c);
              });
          });
    }
}
