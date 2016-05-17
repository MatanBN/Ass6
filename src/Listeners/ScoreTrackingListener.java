package Listeners;

import Items.Ball;
import Items.Block;
import Items.Counter;
import Geometry.Rectangle;
import biuoop.DrawSurface;

/**
 * Created by user on 17/05/2016.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private Rectangle rectangle;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0){
            this.currentScore.increase(10);
        }
        else {
            this.currentScore.increase(5);
        }
    }

    public void drawOn(DrawSurface d){
        rectangle.drawOn(d);
    }
}
