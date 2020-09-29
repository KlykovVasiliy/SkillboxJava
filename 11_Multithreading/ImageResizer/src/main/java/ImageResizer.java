import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread {
    private final File file;
    private final int newWidth;
    private final String dstFolder;
    private final long start;

    public ImageResizer(File file, int newWidth, String dstFolder, long start) {
        this.file = file;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            BufferedImage image = ImageIO.read(file);
            int newHeight = (int) Math.round(
                    image.getHeight() / (image.getWidth() / (double) newWidth));

            BufferedImage newImage = resize(image, newWidth, newHeight);
            File newFile = new File(dstFolder + "/" + file.getName());
            ImageIO.write(newImage, "jpg", newFile);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Завершение после старта (мс) " + (System.currentTimeMillis() - start));
    }

    private static BufferedImage resize(BufferedImage src, int targetWidth, int targetHeight) {
        BufferedImage image = Scalr.resize(src, targetWidth, targetHeight);
        image.flush();
        return image;
    }
}