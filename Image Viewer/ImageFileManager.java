import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageFileManager {
    public static OFImage loadImage(File file) throws Exception {
        BufferedImage image = ImageIO.read(file);
        return new OFImage(image);
    }
}
