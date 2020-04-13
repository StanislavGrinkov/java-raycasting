package jrc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import jrc.engine.Color;
import jrc.engine.Framebuffer;
import jrc.engine.writer.TargaWriter;

public class App {
    public static void main( String[] args )
    {
        var image = Framebuffer.make(32, 32);
        
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

        try (var stream = Files.newOutputStream(Paths.get(".", "target", "output.tga"));
            var writer = new TargaWriter(stream);) {            
            writer.write(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
