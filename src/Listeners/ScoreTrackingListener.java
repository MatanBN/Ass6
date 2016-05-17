package Listeners;

import Items.Ball;
import Items.Block;
import Items.Counter;

/**
 * Created by user on 17/05/2016.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getHitPoints() == 0){
            this.currentScore.increase(10);
        }
    }
}
