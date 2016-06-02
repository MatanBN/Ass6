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
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation){
        this.sensor = sensor;
        this.key=key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
