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
    private boolean stop;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation){
        this.sensor = sensor;
        this.key=key;
        this.animation = animation;
        isAlreadyPressed = true;
        stop = false;

    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
        else{
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
