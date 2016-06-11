package leveldevelopment;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * ImageParser class creates a new Image object according to a string given.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public class ImageParser {

    /**
     * imageFromString creates an image file from the string given.
     *
     * @param s the name of the image to create.
     * @return an image file that was created.
     */
    public Image imageFromString(String s) {
        String myImage = s.substring(0, s.length() - 1);
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("resources\\" + myImage);
        Image image = null;
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Unable to read image");
        }
        return image;
    }
}
