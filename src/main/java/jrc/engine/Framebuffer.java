package jrc.engine;

public class Framebuffer extends DataBuffer<Color> {
    
    public Framebuffer(int width, int height) {
        super(width, height, Color::new);
    }
    
    @Override
    public void set(int x, int y, Color value) {
        if (value != Color.NONE)
            super.set(x, y, value);
    }
    
    @Override
    public void set(int linear, Color value) {
        if (value != Color.NONE)
            super.set(linear, value);
    }
}
