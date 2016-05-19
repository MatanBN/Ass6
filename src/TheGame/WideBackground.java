package TheGame;

import Items.Sprite;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * Created by user on 18/05/2016.
 */
public class WideBackground implements Sprite {

    public void drawOn (DrawSurface d){
        d.setColor(new Color (255, 206, 156));
        for (int i=0; i<47; i++)
        {
            d.drawLine(150-i, 150+i, 500-(i*10), 250);
        }
        d.setColor(new Color (255, 206, 156));
        d.drawCircle(150, 150, 50);
        d.fillCircle(150, 150, 50);
        d.setColor(Color.ORANGE);
        d.drawCircle(150, 150, 40);
        d.fillCircle(150, 150, 40);
        d.setColor(Color.YELLOW);
        d.drawCircle(150, 150, 30);
        d.fillCircle(150, 150, 30);

        d.setColor(Color.black);
        d.drawText(600,10, "Level Name: Wide Easy", 10);
    }

    public void timePassed() {

    }


}
