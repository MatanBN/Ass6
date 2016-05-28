package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The WideBackground class is a class for the background of second level.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class WideBackground implements Sprite {
    /**
     * drawOn method draws the background for the second level.
     *
     * @param d the DrawSurface to draw the background on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(255, 206, 156));
        for (int i = 0; i < 47; i++) {
            d.drawLine(150 - i, 150 + i, 500 - (i * 10), 250);
        }
        d.setColor(new Color(255, 206, 156));
        d.drawCircle(150, 150, 50);
        d.fillCircle(150, 150, 50);
        d.setColor(Color.ORANGE);
        d.drawCircle(150, 150, 40);
        d.fillCircle(150, 150, 40);
        d.setColor(Color.YELLOW);
        d.drawCircle(150, 150, 30);
        d.fillCircle(150, 150, 30);

    }

    /**
     * Currently dosen't do anything.
     */
    public void timePassed() {

    }


}
