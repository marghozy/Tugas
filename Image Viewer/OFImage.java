import java.awt.Color;
import java.awt.image.BufferedImage;

public class OFImage extends BufferedImage {
    public OFImage(BufferedImage image) {
        super(image.getWidth(), image.getHeight(), image.getType());
        this.getGraphics().drawImage(image, 0, 0, null);
    }

    public void darker() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                int rgb = getRGB(x, y);
                Color color = new Color(rgb);

                int red = (int) (color.getRed() * 0.8);
                int green = (int) (color.getGreen() * 0.8);
                int blue = (int) (color.getBlue() * 0.8);

                Color darkerColor = new Color(red, green, blue);

                setRGB(x, y, darkerColor.getRGB());
            }
        }
    }

    public void lighter() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                int rgb = getRGB(x, y);
                Color color = new Color(rgb);

                int red = Math.min(255, (int) (color.getRed() * 1.2));
                int green = Math.min(255, (int) (color.getGreen() * 1.2));
                int blue = Math.min(255, (int) (color.getBlue() * 1.2));

                Color lighterColor = new Color(red, green, blue);

                setRGB(x, y, lighterColor.getRGB());
            }
        }
    }
}
