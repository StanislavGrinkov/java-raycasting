package jrc.engine;

import jrc.engine.type.Vector2D;

public class FramebufferTest {

    public void Framebuffer_drawLine_test() {
        
        var image = new Framebuffer(1024, 512);        
        var startP = new Vector2D(100, 100);
        image.drawRect(50, 50, 100, Color.gray(127));
        image.drawLine(startP, startP.transpose(0, 0), Color.BLACK);
        image.drawLine(startP, startP.transpose(-50, 0), Color.GREEN);
        image.drawLine(startP, startP.transpose(50, 0), Color.GREEN);
        image.drawLine(startP, startP.transpose(0, 50), Color.RED);
        image.drawLine(startP, startP.transpose(0, -50), Color.RED);
        
        image.drawLine(startP, startP.transpose(50, 50), Color.from(255, 255, 32));
        
        image.drawLine(startP, startP.transpose(50, 25), Color.from(255, 255, 32));
        image.drawLine(startP, startP.transpose(50, -25), Color.from(255, 255, 32));
        image.drawLine(startP, startP.transpose(-50, 25), Color.from(255, 255, 32));
        image.drawLine(startP, startP.transpose(-50, -25), Color.from(255, 255, 32));
        image.drawLine(startP, startP.transpose(25, 50), Color.from(255, 255, 32));
        image.drawLine(startP, startP.transpose(-25, 50), Color.from(255, 255, 32));
        image.drawLine(startP, startP.transpose(25, -50), Color.from(255, 255, 32));
        image.drawLine(startP, startP.transpose(-25, -50), Color.from(255, 255, 32));

        image.drawLine(startP, startP.transpose(-50, -50), Color.from(32, 255, 255));        
        image.drawLine(startP, startP.transpose(-50, 50), Color.BLUE);
        image.drawLine(startP, startP.transpose(50, -50), Color.from(255, 32, 255));
    }
}
