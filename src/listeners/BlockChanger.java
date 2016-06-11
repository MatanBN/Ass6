package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * Created by Matan on 6/10/2016.
 */
public class BlockChanger implements HitListener {

    /**
     * hitEvent notifies that there is a hit with a "BlockRemover" block and removes this block.
     *
     * @param beingHit is the specific block that is being hit.
     * @param hitter   is the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeHitListener(this);
        } else {
            beingHit.setFiller();
        }
    }
}
