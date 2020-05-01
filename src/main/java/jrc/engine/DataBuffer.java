package jrc.engine;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jrc.engine.exception.OutOfRangeException;
import lombok.Getter;

public abstract class DataBuffer<T> {
    
    private final List<T> data;
    
    @Getter
    private final int width;
    
    @Getter
    private final int height;
    
    public DataBuffer(int width, int height, Supplier<T> initElementFn) {
        if (width < 1 || width > Short.MAX_VALUE) {
            throw new OutOfRangeException("width", width, 1, Short.MAX_VALUE);
        }
        
        if (height < 1 || height > Short.MAX_VALUE) {
            throw new OutOfRangeException("height", height, 1, Short.MAX_VALUE);
        }
        
        this.width = width;
        this.height = height;
       
        data = Stream.generate(initElementFn)
           .limit(width * height)
           .collect(Collectors.toList());
    }

    public final T get(int x, int y) {
        validate(x, y);
        return get(y * width + x);
    }
   
    public T get(int byIndex) {
        return data.get(byIndex);
    }
    
    public final void set(int x, int y, T value) {
        if (validateNoThrow(x, y)) {
            set(y * width + x, value);
        }
    }
    
    public void set(int byIndex, T value) {
        data.set(byIndex, value);
    }
    
    private void validate(int x, int y) {
        if ((x < 0) || (x >= getWidth())) {
            throw new OutOfRangeException("x", x, getWidth());
        }
        
        if ((y < 0) || (y >= getHeight())) {
            throw new OutOfRangeException("y", y, getHeight());
        }
    }
    
    private boolean validateNoThrow(int x, int y) {
        if ((x < 0) || (x >= getWidth())) {
            return false;
        }
        if ((y < 0) || (y >= getHeight())) {
            return false;
        }
        return true;
    }
}
