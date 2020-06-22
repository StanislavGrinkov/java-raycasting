package jrc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import jrc.engine.Color;
import jrc.engine.Framebuffer;
import jrc.engine.map.GameMap;
import jrc.engine.Player;
import jrc.engine.World;
import jrc.engine.writer.TargaWriter;

public class App {
    public static void main( String[] args )
    {
        var image = new Framebuffer(1024, 512);
       
        var map = initMap();
        var p = new Player(3.4f, 2.8f, 35);
        
        map.drawTo(image);
        p.drawTo(image, map.getCellSize());
        //p.setX(0);
        //p.setY(0);
        //p.drawTo(image, map.getCellSize());
        
//        for (var a = 0; a < 360; a += 15) {
//            p.setAngle(a);
//            p.drawTo(image, map.getCellSize());
//            dump(image, "output-"+a);
//        }

        dump(image, "output-final");
    }

    private static void dump(Framebuffer image, String filename) {
        try (var stream = Files.newOutputStream(Paths.get(".", "target", filename+".tga"));
                var writer = new TargaWriter(stream);) {            
                writer.write(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private static GameMap initMap() {
        var mapData =
                  "####################"
                + "#            #     #"
                + "#             #    #"
                + "#     ###      #   #"
                + "# 2     #    ###   #"
                + "#       #    ###   #"
                + "#     ###      #   #"
                + "#             #    #"
                + "#            #     #"
                + "####################";
        return new GameMap(20, 10, mapData);
    }
}
