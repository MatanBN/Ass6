package Movement;

import Items.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * Created by user on 18/05/2016.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) secods, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int counter;
    private SpriteCollection screen;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds=numOfSeconds;
        counter=countFrom;
        screen=gameScreen;
    }
    public void doOneFrame(DrawSurface d) {
            String s = Integer.toString(counter);
            d.drawText(d.getWidth() / 2, 10, s, 32);
    }
    public boolean shouldStop() {
        return true;
    }
}
