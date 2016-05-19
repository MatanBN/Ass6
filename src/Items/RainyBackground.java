package Items;

import Geometry.*;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matan on 5/18/2016.
 */
public class RainyBackground implements Sprite {
    List <Point> snow;

    @Override
    public void drawOn(DrawSurface d) {
        int height = d.getHeight();
        int width = d.getWidth();
        snow= new ArrayList<>();

        for(int i=0; i<12; i++){
            snow.add(new Point(600-(i*6), 450));
        }
        for(int i=0; i<12; i++){
            snow.add(new Point(300-(i*6), 300));
        }

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
        d.setColor(Color.black);
        d.drawText(600,10, "Level Name: Final Four", 10);

        d.setColor(Color.WHITE);
        for(int i=0; i<snow.size();i++){
            d.fillCircle((int)snow.get(i).getX(),(int)snow.get(i).getY(),4);
        }

    }

    @Override
    public void timePassed() {
        for (int i=0; i<snow.size();i++){
            snow.get(i).setY(snow.get(i).getY()+10);
        }
    }
}
