package jrc.engine.writer;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.IntStream;

import jrc.engine.DataBuffer;
import jrc.engine.Color;

public class TargaWriter implements Closeable {
    
    private final OutputStream stream;
    
    public TargaWriter(OutputStream stream) {
        this.stream = stream;
    }
    
    public void write(DataBuffer<? extends Color> image) {
        try {
            writeHeader(image, stream);
            writeData(image, stream);
            writeFooter(stream);
            stream.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void writeData(DataBuffer<? extends Color> image, OutputStream stream) {
        IntStream.range(0, image.getHeight())
            .forEach(y -> writeRow(image, y, stream));
    }

    private void writeHeader(DataBuffer<? extends Color> image, OutputStream stream) throws IOException {
        // https://en.wikipedia.org/wiki/Truevision_TGA
        byte[] header = new byte[18];
        header[2] = 2; // [image type] uncompressed true-color image
        header[12] = (byte) (image.getWidth() & 255);
        header[13] = (byte) (image.getWidth() >> 8);
        header[14] = (byte) (image.getHeight() & 255);
        header[15] = (byte) (image.getHeight() >> 8);
        header[16] = 24; // bpp
        header[17] = 0b00100000; // 0b00yxaaaa;  y=(t->b), x=(l->r) render from top and left
        stream.write(header);
    }
    
    private void writeFooter(OutputStream stream) throws IOException {
        stream.write(new byte[4]); // developer area
        stream.write(new byte[4]); // extension area
        byte[] footer = new byte[] {'T','R','U','E','V','I','S','I','O','N','-','X','F','I','L','E','.','\0'};
        stream.write(footer);
    }

    private void writeRow(DataBuffer<? extends Color> image, int y, OutputStream stream) {
        byte[] row = new byte[3 * image.getWidth()];       
        IntStream.range(0, image.getWidth())
            .forEach(x -> {
                var c = image.get(x, y);
                row[x * 3 + 0] = (byte)c.getBlue();
                row[x * 3 + 1] = (byte)c.getGreen();
                row[x * 3 + 2] = (byte)c.getRed();
            });
        try {
            stream.write(row);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}
