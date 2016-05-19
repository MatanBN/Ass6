package Movement;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * Created by user on 17/05/2016.
 */

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    public AnimationRunner() {
        framesPerSecond = 60;
        this.gui = new GUI("Arknoid", 800, 600);
    }

    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();

        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    public GUI getGui() {
        return this.gui;
    }

}
