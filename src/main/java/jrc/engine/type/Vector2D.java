package jrc.engine.type;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter 
@AllArgsConstructor
@EqualsAndHashCode
public class Vector2D {
    private final double x;
    private final double y;
    
    public Vector2D transpose(double x, double y) {
        return new Vector2D(this.x + x, this.y + y);
    }

    public static Vector2D diff(Vector2D p1, Vector2D p2) {
        return new Vector2D(p1.x - p2.x, p1.y - p2.y);
    }

    public Vector2D(Vector2D that) {
        this.x = that.getX();
        this.y = that.getY();
    }

    public static Vector2D of(int x, int y) {
        return new Vector2D(x, y);
    }

    public Vector2D transpose(Vector2D by) {
        return transpose(by.x, by.y);
    }

    public Vector2D rotate(float angle) {
        var rad = Math.toRadians(angle);
        var ca = Math.cos(rad);
        var sa = Math.sin(rad);
        return new Vector2D(ca * x - sa * y, sa * x + ca * y);
    }
}