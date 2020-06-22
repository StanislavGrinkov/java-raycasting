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
    
    /**
     * Player coordinates are expressed in GameMap coordinate system.
     * 
     * @param x
     * @param y
     * @param angle viewAngle in degrees
     */
    public Player(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
   
    public void drawTo(Framebuffer image, int cellSize) {
        var pos = Vector2D.of(getX(),getY());
        var viewRay = Vector2D.of(10, 0).rotate(angle);
        var leftLine = viewRay.rotate(-fov / 2).transpose(pos);
        var rightLine = viewRay.rotate(fov / 2).transpose(pos);
        var viewEndPoint = viewRay.transpose(pos);

        image.drawLine(pos.scale(cellSize), viewEndPoint.scale(cellSize), Color.GREEN);        
        image.drawLine(pos.scale(cellSize), leftLine.scale(cellSize), Color.BLUE);
        image.drawLine(pos.scale(cellSize), rightLine.scale(cellSize), Color.RED);
        image.drawLine(leftLine.scale(cellSize), rightLine.scale(cellSize), Color.RED);
        image.set(pos.scale(cellSize), Color.YELLOW);
        
        if (x == 0) {
            //special case for vertical rays?
        }
        
        int steps = 0;
        var hitPoint = new Vector2D(pos);
        var slope = (viewRay.getY() / viewRay.getX());
        while (steps != 20) {
            var diffX = (int)(hitPoint.getX() + 1) - hitPoint.getX();
            var diffY = (int)(hitPoint.getY() + 1) - hitPoint.getY();
            
            var dx = (diffX < diffY) ? diffX : diffY / slope; 
            var dy = (diffX > diffY) ? diffY : diffX * slope;
            
            hitPoint = hitPoint.translate(dx, dy);
            image.set(hitPoint.scale(cellSize), Color.YELLOW);
            ++steps;
        }
    }
}
