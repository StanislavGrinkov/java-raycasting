package jrc.engine;

import jrc.engine.type.Vector2D;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Player {
    private float x;
    private float y;
    private float angle;
    private int fov = 60;
    
    public Player(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    
    public void drawTo(Framebuffer image, int cellSize) {
       var x = Math.round(getX() * cellSize);
       var y = Math.round(getY() * cellSize);
       var pos = Vector2D.of(x,y);
       var viewDir = Vector2D.of(100, 0).rotate(angle).transpose(pos);
       image.drawLine(pos, viewDir, Color.GREEN);
      
        //player
        image.drawPoint(
                x,
                y,
                4,
                Color.RED);
    }
}
