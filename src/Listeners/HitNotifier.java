package Listeners;

/**
 * Created by user on 16/05/2016.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}