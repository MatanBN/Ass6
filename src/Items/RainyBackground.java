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
        d.fillRectangle(0, 0, width - 20, height - 20);
        d.setColor(Color.darkGray);
        d.fillCircle(590, 400, 30);
        d.setColor(Color.darkGray.brighter());
        d.fillCircle(580, 450, 30);
        d.setColor(Color.darkGray.brighter());

        d.fillCircle(570, 455, 25);
        d.setColor(Color.darkGray.brighter());

        d.fillCircle(560, 460, 25);
        d.setColor(Color.darkGray.brighter());

        d.fillCircle(550, 440, 20);
        d.setColor(Color.darkGray.brighter());
        d.fillCircle(540, 450, 15);

        d.setColor(Color.darkGray.brighter());

        d.fillCircle(535, 450, 20);

        d.fillCircle(540, 400, 20);
        d.setColor(Color.lightGray);
        d.fillCircle(545, 410, 10);
        d.fillCircle(550, 440, 10);

    }

    @Override
    public void timePassed() {

    }
}
