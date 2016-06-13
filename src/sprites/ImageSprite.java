package sprites;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Image;

/**
 * ImageSprite class is a background for block/level with an image.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public class ImageSprite extends Background implements Sprite {
    private Image image; // The image of the background.

    /**
     * ImageSprite constructor.
     *
     * @param image the image to draw.
     */
    public ImageSprite(Image image) {
        this.image = image;
    }

    /**
     * ImageSprite constructor.
     *
     * @param frame the frame for the image.
     * @param image the image to draw.
     */
    public ImageSprite(Rectangle frame, Image image) {
        super(frame);
        this.image = image;
    }

    /**
     * drawOn method draws the image according to the frame upper left coordinates.
     *
     * @param d the DrawSurface to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Rectangle r = getFrame();
        d.drawImage(r.getX(), r.getY(), image);
    }

    /**
     * currently doesn't do anything.
     *
     * @param dt the time interval.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * AddRectangle method creates a new ImageSprite with a new frame and the same color.
     *
     * @param r the new frame of the background.
     * @return a new ImageSprite with a a new frame and the same color.
     */
    @Override
    public Background AddRectangle(Rectangle r) {
        return new ImageSprite(r, this.image);
    }
}
