package animations;

import biuoop.DrawSurface;

/**
 * Created by Matan on 6/2/2016.
 */
public interface Menu<T> extends Animation {
    void addSelection(String key, String message, T returnVal);
    T getStatus();
}
