package Animations;

import biuoop.DrawSurface;

/**
 * Animation holds 2 statements of any Animation.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public interface Animation {

    /**
     * doOneFrame method draws the game to the screen.
     * @param d the drawSurface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop method returns the value of running.
     * @return the value of the running variable.
     */
    boolean shouldStop();
}
