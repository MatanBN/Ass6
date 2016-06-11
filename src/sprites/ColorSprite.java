package sprites;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;


/**
 * Created by Matan on 6/6/2016.
 */
public class ColorSprite extends Background implements Sprite {
    private Color c;

    public ColorSprite(Color c) {
        this.c = c;
    }

    public ColorSprite(Rectangle frame, Color c) {
        super(frame);
        this.c = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(c);
        Rectangle r = getFrame();
        d.fillRectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public Background AddRectangle(Rectangle r) {
        return new ColorSprite(r, c);
    }
}
