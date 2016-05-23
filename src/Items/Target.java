package Items;

import biuoop.DrawSurface;

import java.awt.*;

/**
 * Created by Matan on 5/18/2016.
 */
public class Target implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        int height = d.getHeight();
        int width = d.getWidth();
        d.setColor(Color.black);
        d.fillRectangle(0, 0, width - 20, height);
        d.setColor(Color.blue);
        d.drawCircle(width / 2 - 20, 200, 200);
        d.drawCircle(width / 2 - 20, 200, 100);
        d.drawCircle(width / 2 - 20, 200, 50);
        d.drawLine(width / 2 - 260, 200, width / 2 - 40, 200);
        d.drawLine(width / 2, 200, width - 180, 200);
        d.drawLine(width / 2 - 20, 20, width / 2 - 20, 180);
        d.drawLine(width / 2 - 20, 220, width / 2 - 20, 380);
    }

    @Override
    public void timePassed() {

    }
}
