package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * BlockChanger class is in charge of changing the filler of the block.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public class BlockChanger implements HitListener {

    /**
     * hitEvent notifies that there is a hit with a "BlockChanger" block and changes the fill (if needed) of the block.
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
