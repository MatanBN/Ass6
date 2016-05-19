package Items;

import Geometry.*;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * Created by Matan on 5/18/2016.
 */
public class RainyBackground implements Sprite {


    @Override
    public void drawOn(DrawSurface d) {
        int height = d.getHeight();
        int width = d.getWidth();



        d.setColor(new Color(0, 100, 255));
        d.fillRectangle(0, 0, width - 20, height);

        d.setColor(Color.white);
        for (int i=0; i<12; i++){
            d.drawLine(600-(i*6), 450, 590-(i*6), 600);
        }
        d.setColor(Color.lightGray);
        d.fillCircle(590, 450, 18);
        d.fillCircle(550, 442, 20);
        d.fillCircle(575, 435, 18);
        d.fillCircle(565, 458, 15);

        d.setColor(Color.white);
        for (int i=0; i<12; i++){
            d.drawLine(200-(i*6), 300, 140-(i*6), 600);
        }

        d.setColor(Color.lightGray);
        d.fillCircle(190, 300, 18);
        d.fillCircle(150, 292, 20);
        d.fillCircle(175, 285, 18);
        d.fillCircle(165, 308, 15);


    }

    @Override
    public void timePassed() {

    }
}
