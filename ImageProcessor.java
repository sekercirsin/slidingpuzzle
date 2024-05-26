import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessor {
    public static void divideImage(String fileName) {
        try {

            BufferedImage originalImage = ImageIO.read(new File(fileName));
            
            
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();

            
            int squareSize = Math.min(originalWidth, originalHeight);

            
            BufferedImage squareImage = new BufferedImage(840, 840, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = squareImage.createGraphics();

            
            g2d.drawImage(originalImage, 0, 0, 840, 840, null);
            g2d.dispose();



            
            int rows = 3;
            int cols = 3;

            int partWidth = 840 / cols;
            int partHeight = 840 / rows;

            
            int i = 0;
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    int startX = x * partWidth;
                    int startY = y * partHeight;

                    BufferedImage part = squareImage.getSubimage(startX, startY, partWidth, partHeight);

                    File output = new File(i + ".jpg");
                    ImageIO.write(part, "jpg", output);
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
