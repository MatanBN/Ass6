/**
 * Created by user on 04/04/2016.
 */
import biuoop.DrawSurface;
public interface Sprite {
    // draw the sprite to the screen
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed
    void timePassed();
}
