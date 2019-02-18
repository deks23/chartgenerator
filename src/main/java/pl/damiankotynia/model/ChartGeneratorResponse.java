package pl.damiankotynia.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class  ChartGeneratorResponse  extends Response implements Serializable {
    private transient BufferedImage image;


    public ChartGeneratorResponse(ResponseType responseType, BufferedImage image) {
        super(responseType);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(image, "png", out); // png is lossless
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        image = (ImageIO.read(in));
    }
}
