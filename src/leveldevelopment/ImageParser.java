package leveldevelopment;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 * Created by Matan on 6/9/2016.
 */
public class ImageParser {

    public Image imageFromString(String s) {
        String myImage = s.substring(0, s.length() - 1);

        File file = new File(myImage);
        Image image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Unable to read image");
        }
        return image;
    }
}
