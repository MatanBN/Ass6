package Movement;

import Items.Sprite;
import biuoop.DrawSurface;

/**
 * Created by Matan on 5/19/2016.
 */
public class LevelIndicator implements Sprite {
    private String name;

    public LevelIndicator(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(600, 10, "Level Name: " + name, 10);
    }

    @Override
    public void timePassed() {

    }
}
