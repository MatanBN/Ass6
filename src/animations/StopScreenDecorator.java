package animations;

import biuoop.KeyboardSensor;

/**
 * Created by Matan on 6/2/2016.
 */
public class StopScreenDecorator extends KeyPressStoppableAnimation {
    public StopScreenDecorator(KeyboardSensor sensor, String key, Animation animation) {
        super(sensor, key, animation);
    }

}
