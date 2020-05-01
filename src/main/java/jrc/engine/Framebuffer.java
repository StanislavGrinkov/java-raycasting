package jrc.engine;

import java.util.Objects;
import java.util.stream.IntStream;

import jrc.engine.type.Vector2D;

public class Framebuffer extends DataBuffer<Color> {
    
    public Framebuffer(int width, int height) {
        super(width, height, Color::new);
    }
    
    public void set(Vector2D point, Color value) {
        if (value != Color.NONE)
            super.set((int)point.getX(), (int)point.getY(), value);
    }
    
    @Override
    public void set(int byIndex, Color value) {
        if (value != Color.NONE)
            super.set(byIndex, value);
    }
    
    public void drawPoint(int centerX, int centerY, int size, Color fillColor) {
        drawRect(centerX - size / 2, centerY - size / 2, size, fillColor);
    }
    
    public void drawRect(int startX, int startY, int size, Color fillColor) {
        int endX = Math.min(startX + size, getWidth());
        int endY = Math.min(startY + size, getHeight());
        
        IntStream.rangeClosed(startY, endY).forEach(y -> {
              IntStream.rangeClosed(startX, endX).forEach(x -> {
                  set(x, y, fillColor);
              });
          });
    }
    
    public void drawLine(Vector2D start, Vector2D end, Color color) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        if (Objects.equals(start, end)) {
            set(start, color);
            return;            
        }

        var steps = Vector2D.diff(end, start);
        double stepCount = Math.max(Math.abs(steps.getX()), Math.abs(steps.getY()));
        var sign = new Vector2D((int)Math.signum(steps.getX()), (int)Math.signum(steps.getY()));
        
        double deltaX = Math.abs(steps.getX() / stepCount);
        double deltaY = Math.abs(steps.getY() / stepCount);
        double accumX = 0;
        double accumY = 0;
        var point = new Vector2D(start);
        for (var step = 0; step <= stepCount; ++step) {
            set((int)point.getX(), (int)point.getY(), color);
            accumX += deltaX;
            accumY += deltaY;
            
            if (accumX >= 1) {
                --accumX;
                point = point.transpose(1*sign.getX(), 0);
            }
            
            if (accumY >= 1) {
                --accumY;
                point = point.transpose(0, 1*sign.getY());
            }
            
                   
        }
    }
    
    public void fillRedGreen() {
      IntStream.range(0, getWidth()).forEach(x -> {
          double x_f = (x + 1) / (double) getWidth();
          IntStream.range(0, getHeight()).forEach(y -> {                    
                  double y_f = (y + 1) / (double) getHeight();
                  var c = Color.from(x_f, y_f, 0.2);
                  set(x, y, c);
              });
      });
    }
}
