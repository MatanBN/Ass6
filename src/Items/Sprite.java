package Items;

import biuoop.DrawSurface;

/**
 * The Items.Sprite holds 2 statements of any Items.Sprite object.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public interface Sprite {
    /**
     * drawOn method draws the Items.Sprite to the screen.
     * @param d the DrawSurface to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}
