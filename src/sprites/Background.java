package sprites;

import geometry.Rectangle;

/**
 * Created by Matan on 6/11/2016.
 */
public abstract class Background implements Sprite {
    private Rectangle frame;

    public Background() {

    }

    public Background(Rectangle frame) {
        this.frame = frame;
    }

    public Rectangle getFrame() {
        return frame;
    }

    public abstract Background AddRectangle(Rectangle r);
}
