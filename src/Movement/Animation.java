package Movement;

import biuoop.DrawSurface;

/**
 * Created by user on 17/05/2016.
 */

public interface Animation {
    void doOneFrame(DrawSurface d);
    boolean shouldStop();
}
