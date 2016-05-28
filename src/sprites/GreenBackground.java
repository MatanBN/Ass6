package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The GreenBackground class is a class for the background of third level.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class GreenBackground implements Sprite {

    /**
     * drawOn method draws the this background to the screen.
     *
     * @param d the DrawSurface to draw the background on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0, 153, 0));
        d.fillRectangle(20, 20, 780, 580);
        d.setColor(new Color(32, 32, 32));
        d.fillRectangle(50, 450, 100, 150);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle((j * 20) + 55, (i * 25) + 455, 10, 20);
            }
        }
        d.setColor(new Color(64, 64, 64));
        d.fillRectangle(84, 400, 30, 50);
        d.setColor(new Color(96, 96, 90));
        d.fillRectangle(94, 200, 10, 200);
        d.setColor(new Color(255, 178, 102));
        d.fillCircle(99, 200, 10);
        d.setColor(new Color(255, 51, 51));
        d.fillCircle(99, 200, 7);
        d.setColor(new Color(255, 255, 51));
        d.fillCircle(99, 200, 3);
    }

    /**
     * Currently doesn't do anything.
     */
    public void timePassed() {

    }
}
