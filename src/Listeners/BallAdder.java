package Listeners;

import Items.Ball;
import Items.Block;
import Items.Counter;
import Movement.Velocity;
import TheGame.Game;

import java.awt.*;

/**
 * Created by Matan on 5/16/2016.
 */
public class BallAdder implements HitListener {
    private Game game;
    private Counter remainingBalls;

    public BallAdder(Game game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        game.createBall(50, 90, 5, Color.BLUE, new Velocity(2, -2));
        beingHit.removeHitListener(this);

    }
}
