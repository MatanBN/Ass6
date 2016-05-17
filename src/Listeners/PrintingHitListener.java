package Listeners;

import Items.Ball;
import Items.BaseBlock;

public class PrintingHitListener implements HitListener {
    public void hitEvent(BaseBlock beingHit, Ball hitter) {
        System.out.println("A BaseBlock with " + beingHit.getHitPoints() + " points was hit.");
    }
}
