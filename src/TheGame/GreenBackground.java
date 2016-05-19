package TheGame;

import Items.Sprite;
import biuoop.DrawSurface;
import java.awt.*;

/**
 * Created by user on 19/05/2016.
 */
public class GreenBackground implements Sprite{

    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0,153,0));
        d.fillRectangle(20, 20, 780, 580);
        d.setColor(new Color(32, 32, 32));
        d.fillRectangle(50, 450, 100, 150);
        d.setColor(Color.WHITE);
        for (int i=0; i<6; i++)
        {
            for (int j=0; j<5; j++){
                d.fillRectangle((j*20) + 55,(i*25)+455 , 10, 20);
            }
        }
        d.setColor(new Color(64, 64, 64));
        d.fillRectangle(84, 400, 30, 50);
        d.setColor(new Color(96,96,96));
        d.fillRectangle(94, 200, 10,200);
        d.setColor(new Color(255,178,102));
        d.fillCircle(99,200,10);
        d.setColor(new Color(255,51,51));
        d.fillCircle(99,200,7);
        d.setColor(new Color(255,255,51));
        d.fillCircle(99,200,3);
    }

    public void timePassed() {

    }
}
