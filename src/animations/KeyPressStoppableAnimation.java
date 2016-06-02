package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Created by user on 02/06/2016.
 */

public abstract class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation){
        this.sensor = sensor;
        this.key=key;
        this.animation = animation;
        isAlreadyPressed = true;

    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        animation.doOneFrame(d, dt);
    }

    @Override
    public boolean shouldStop() {
        return animation.shouldStop();
    }
}
