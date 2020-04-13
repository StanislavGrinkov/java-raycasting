package jrc.engine;

import java.util.Arrays;
import java.util.Objects;

import jrc.engine.exception.OutOfRangeException;

public class Framebuffer implements BufferInfo {
    private final Color[][] data;
    private final int width;
    private final int height;
    
    public static BufferInfo make(int width, int height) {
        return new Framebuffer(width, height);
    }
    
    private Framebuffer(int width, int height) {
        if (width < 1 || width > Short.MAX_VALUE) {
            throw new OutOfRangeException("width", width, 1, Short.MAX_VALUE);
        }
        
        if (height < 1 || height > Short.MAX_VALUE) {
            throw new OutOfRangeException("height", height, 1, Short.MAX_VALUE);
        }
        
        this.width = width;
        this.height = height;
        data = new Color[height][width];
        Arrays.stream(data).forEach(row -> {
            Arrays.setAll(row, c -> new Color());            
        });
    }
    
    @Override
    public Color get(int x, int y) {
        validate(x, y);
        return data[y][x];
    }
    
    @Override
    public void set(int x, int y, Color c) {
        validate(x, y);
        Objects.requireNonNull(c);
        data[y][x] = c;
    }
    
    @Override
    public int getWidth() {
        return width;
    }
    
    @Override
    public int getHeight() {
        return height;
    }

    private void validate(int x, int y) {
        if ((x < 0) || (x >= width)) {
            throw new OutOfRangeException("x", x, width);
        }
        
        if ((y < 0) || (y >= height)) {
            throw new OutOfRangeException("y", y, height);
        }
    }
}
