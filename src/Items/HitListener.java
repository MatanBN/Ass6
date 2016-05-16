package Items;

/**
 * Created by user on 16/05/2016.
 */

public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    void hitEvent(Block beingHit,Ball hitter);
}
