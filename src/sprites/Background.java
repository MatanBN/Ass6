package sprites;

import geometry.Rectangle;

/**
 * Background class is an abstract class for the background of the level/block.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public abstract class Background implements Sprite {
    private Rectangle frame; // The frame for the background.

    /**
     * Empty constructor.
     */
    public Background() {

    }

    /**
     * Constructor for the background class.
     *
     * @param frame the frame for the background to be drawn on.
     */
    public Background(Rectangle frame) {
        this.frame = frame;
    }

    /**
     * getFrame returns the frame of the background.
     *
     * @return the frame of the background.
     */
    public Rectangle getFrame() {
        return frame;
    }

    /**
     * AddRectangle method returns a new Background with a frame.
     *
     * @param r the new frame of the background.
     * @return the new background with a frame.
     */
    public abstract Background AddRectangle(Rectangle r);
}
