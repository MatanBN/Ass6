package Movement;

import biuoop.*;

import java.awt.*;

/**
 * Created by user on 17/05/2016.
 */

public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle(0,0,d.getWidth(),d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth()/4-150, d.getHeight() / 2-100, "Dear Player,", 32);
        d.drawText(d.getWidth()/4-150, d.getHeight() / 2-50, "It seems that you have paused the game.", 32);
        d.drawText(d.getWidth()/4-150, d.getHeight() / 2, "If you would like to continue pleas press space :)", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }
    public boolean shouldStop() { return this.stop; }

}
