package jrc.engine;

public interface BufferInfo {

    int getWidth();
    int getHeight();
    
    Color get(int x, int y);
    void set(int x, int y, Color c);
}
