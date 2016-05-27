package Listeners;

import Geometry.Point;
import Sprites.Ball;
import Sprites.Block;
import GameAttributes.Counter;
import GameAttributes.Velocity;
import Animations.GameLevel;


/**
 * BallAdder is a class that implements a HitListener  in order to create new balls and when
 * there is a hit with a block of this type.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class BallAdder implements HitListener {
    private GameLevel gameLevel; //The current gamelevel
    private Counter remainingBalls; //A counter of the remaining balls

    /**
     * The constructor gets the gamelevel and a counter of the removed balls.
     * @param gameLevel is the current gamelevel of the game.
     * @param removedBalls a counter that holds the removed balls number.
     */
    public BallAdder(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * hitEvent notifies that there is a hit with a "BallAdder" block and creates a ball and removes
     * the hitListener from the its list.
     * @param beingHit is the specific block that is being hit.
     * @param hitter is the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.createBall(new Point(50, 90), 5, new Velocity(2, -2));
        beingHit.removeHitListener(this);

    }
}
