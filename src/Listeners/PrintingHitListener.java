package Listeners;

import Items.Ball;
import Items.Block;
import Items.HitListener;

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}
