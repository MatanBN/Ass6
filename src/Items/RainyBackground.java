package Items;

import biuoop.DrawSurface;

import java.awt.*;

/**
 * Created by Matan on 5/18/2016.
 */
public class RainyBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        int height = d.getHeight();
        int width = d.getWidth();
        Color c = new Color(0, 100, 255);
        d.setColor(c);
        c = Color.lightGray;
        d.fillRectangle(0, 0, width - 20, height - 20);
        d.setColor(c);
        d.fillCircle(590, 450, 30);
        d.setColor(c.brighter());
        d.fillCircle(580, 450, 30);
        d.setColor(c.brighter());

        d.fillCircle(570, 455, 25);
        d.setColor(c.brighter());

        d.fillCircle(560, 460, 25);
        d.setColor(c.brighter());

        d.fillCircle(550, 440, 20);
        d.setColor(c.brighter());
        d.fillCircle(540, 450, 15);

        d.setColor(c.brighter());

        d.fillCircle(535, 450, 20);

        d.fillCircle(540, 450, 20);

    }

    @Override
    public void timePassed() {

    }
}
