package Listeners;

import Items.Ball;
import Items.BaseBlock;
import Items.Counter;
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

    public void hitEvent(BaseBlock beingHit, Ball hitter) {
        Ball ball = new Ball(50, 30, 5, Color.BLUE, game.getEnvironment());
        ball.setVelocity(2, -2);
        ball.addToGame(game);
        beingHit.removeHitListener(this);
        remainingBalls.increase(1);

    }
}
