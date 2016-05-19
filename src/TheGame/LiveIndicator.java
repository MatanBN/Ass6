package TheGame;

import Items.Sprite;
import biuoop.DrawSurface;

/**
 * Created by Matan on 5/19/2016.
 */
public class LiveIndicator implements Sprite {
    private int lives;

    public LiveIndicator(int lives) {
        this.lives = lives;
    }

    public void decrease() {
        --lives;
    }

    public int getValue() {
        return lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(100, 10, "Lives: " + lives, 10);
    }

    @Override
    public void timePassed() {

    }
}
