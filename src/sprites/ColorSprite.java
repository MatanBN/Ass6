package sprites;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;


/**
 * ColorSprite class is a background for block/level with color.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public class ColorSprite extends Background implements Sprite {
    private Color c; // The color of the background.

    /**
     * ColorSprite constructor.
     *
     * @param c the color of the ColorSprite.
     */
    public ColorSprite(Color c) {
        this.c = c;
    }

    /**
     * ColorSprite constructor.
     *
     * @param frame the frame to be draw on.
     * @param c     the color of the ColorSprite.
     */
    public ColorSprite(Rectangle frame, Color c) {
        super(frame);
        this.c = c;
    }

    /**
     * drawOn method draws the ColorSprite according to the frame and color.
     *
     * @param d the DrawSurface to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(c);
        Rectangle r = getFrame();
        d.fillRectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * Currently doesn't do anything.
     *
     * @param dt the time interval.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * AddRectangle method creates a new ColorSprite with a new frame and the same color.
     *
     * @param r the new frame of the background.
     * @return a new ColorSprite with a a new frame and the same color.
     */
    @Override
    public Background AddRectangle(Rectangle r) {
        return new ColorSprite(r, c);
    }
}
