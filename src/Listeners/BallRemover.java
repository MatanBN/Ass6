package Listeners;

import Items.Ball;
import Items.Block;
import Items.Counter;
import TheGame.Game;

/**
 * Created by user on 16/05/2016.
 */

// a BallRemover is in charge of removing balls from the game, as well as keeping count
// of the number of balls that remain.
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block deathRegion, Ball beingHit) {
        beingHit.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
