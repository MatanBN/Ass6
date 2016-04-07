/**
 * Created by user on 04/04/2016.
 */
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * SpriteCollection is a list of the sprites in the game.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */

public class SpriteCollection {
    private ArrayList sprites;

    /**
     * The constructor creates a new list for the sprites
     */
    public SpriteCollection() {
        sprites = new ArrayList ();
    }

    /**
     * addSprite adds a given sprite to the list.
     * @param s is a new sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * notifyAllTimePassed calls the timePassed method on all sprites.
     */
    public void notifyAllTimePassed() {
        for ( Object s : sprites) {
            ((Sprite)s).timePassed();
        }
    }

    // call drawOn(d) on all sprites.

    /**
     * drawAllOn calls rawOn(d) on all sprites.
     * @param d is the surface to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Object s : sprites) {
            ((Sprite)s).drawOn(d);
        }
    }

}
