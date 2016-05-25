package Listeners;

import Items.Ball;
import Items.Block;

/**
 * PrintingHitListener print t
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}
