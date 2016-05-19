package TheGame;

import Items.Counter;
import Items.Sprite;
import biuoop.DrawSurface;

/**
 * Created by Matan on 5/19/2016.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    public ScoreIndicator() {
        score = new Counter();
    }

    public void increase(int num) {
        score.increase(num);
    }

    public Counter getScore() {
        return this.score;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(350, 10, "Score: " + score.getValue(), 10);
    }

    @Override
    public void timePassed() {

    }
}
