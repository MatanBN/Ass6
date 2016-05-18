package Listeners;

import Items.Ball;
import Items.Block;
import Items.Counter;
import TheGame.GameLevel;

/**
 * Created by user on 16/05/2016.
 */

// a BallRemover is in charge of removing balls from the gameLevel, as well as keeping count
// of the number of balls that remain.
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the gameLevel. Remember to remove this listener from the block
    // that is being removed from the gameLevel.
    public void hitEvent(Block deathRegion, Ball beingHit) {
        beingHit.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}
