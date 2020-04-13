package jrc.engine;


public class Color {
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color WHITE = new Color(255, 255, 255);
    
    public static Color gray(int intensity) {
        return new Color(intensity, intensity, intensity);
    }

    public static Color from(int r, int g, int b) {
        return new Color(r, g, b);
    }
    
    public static Color from(double r, double g, double b) {
        return new Color(
                  (int)(r * 255.999)
                , (int)(g * 255.999)
                , (int)(b * 255.999));
    }
    
    private final int red;
    private final int green;
    private final int blue;
    
    public Color() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }
    
    public Color(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
    }
    
    public int getRed() {
        return red;
    }
    
    public int getBlue() {
        return blue;
    }

    public int getGreen() {
        return green;
    }

    public int getAlpha() {
        return 0;
    }
}
