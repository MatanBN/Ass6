package sprites;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Image;

/**
 * Created by Matan on 6/6/2016.
 */
public class ImageSprite extends Background implements Sprite {
    private Image image;

    public ImageSprite(Image image) {
        this.image = image;
    }

    public ImageSprite(Rectangle frame, Image image) {
        super(frame);
        this.image = image;
    }

    @Override
    public void drawOn(DrawSurface d) {
        Rectangle r = getFrame();
        d.drawImage(r.getX(), r.getY(), image);
    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public Background AddRectangle(Rectangle r) {
        return new ImageSprite(r, this.image);
    }
}
