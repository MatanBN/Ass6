package Items;

import Geometry.Rectangle;
import Listeners.HitListener;
import Listeners.HitNotifier;
import Movement.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matan on 5/17/2016.
 */
public class GameBaseBlock extends BaseBlock implements HitNotifier {
    private int hitsNumber; // The number of hits of the block.
    private java.util.List<HitListener> hitListeners;

    public GameBaseBlock(Rectangle r) {
        super(r);
        this.hitListeners = new ArrayList();

    }

    public GameBaseBlock(int width, int height, Color c) {
        super(width, height, c);
        this.hitListeners = new ArrayList();

    }

    public GameBaseBlock(int x, int y, int width, int height, Color c) {
        super(x, y, width, height, c);
        this.hitListeners = new ArrayList();

    }

    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * hit return the new velocity after the hit based on force the object
     * inflicted on us.
     *
     * @param collisionPoint  is the collision point of an object with the block.
     * @param currentVelocity is the current velocity of the object that will collide with the block.
     * @return the new velocity after the hit.
     */
    public Velocity hit(Ball hitter, Geometry.Point collisionPoint, Velocity currentVelocity) {
        if (this.hitsNumber > 0) {
            --this.hitsNumber;
        }
        Velocity v = super.hit(hitter, collisionPoint, currentVelocity);
        this.notifyHit(hitter);
        return v;

    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }

    }

    /**
     * getHitPoints returns block's number of hits.
     *
     * @return the block's number of hits.
     */
    public int getHitPoints() {
        return hitsNumber;
    }

    /**
     * setHitsNumber sets the block's number of hits.
     *
     * @param hits the adjusted number of hits.
     */
    public void setHitsNumber(int hits) {
        hitsNumber = hits;
    }
}
