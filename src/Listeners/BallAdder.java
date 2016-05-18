package Listeners;

import Geometry.Point;
import Items.Ball;
import Items.Block;
import Items.Counter;
import Movement.Velocity;
import TheGame.GameLevel;


/**
 * Created by Matan on 5/16/2016.
 */
public class BallAdder implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    public BallAdder(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.createBall(new Point(50, 90), 5, new Velocity(2, -2));
        beingHit.removeHitListener(this);

    }
}
