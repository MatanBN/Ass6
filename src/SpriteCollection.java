/**
 * Created by user on 04/04/2016.
 */
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

public class SpriteCollection {
    private ArrayList sprites;

    public SpriteCollection() {
        sprites = new ArrayList ();
    }

    public void addSprite(Sprite s) {
        sprites.add(s);
    }

        // call timePassed() on all sprites.

    public void notifyAllTimePassed() {
        for ( Object s : sprites) {
            ((Sprite)s).timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Object s : sprites) {
            ((Sprite)s).drawOn(d);
        }
    }

}
