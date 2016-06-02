package animations;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;


/**
 * Created by Matan on 6/2/2016.
 */
public class MenuAnimation<T> implements Menu {
    ArrayList<Selection> menuSelections;

    public MenuAnimation() {
        menuSelections = new ArrayList<Selection>();
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.blue);
        d.fillRectangle(0, 0, d.getWidth() - 20, d.getHeight());
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public void addSelection(String key, String message, Object returnVal) {
        menuSelections.add(new Selection(key, message, (T) returnVal));
    }

    @Override
    public T getStatus() {
        return null;
    }

    private class Selection {
        private String key;
        private String message;
        private T returnVal;

        public Selection(String key, String message, T returnVal) {
            this.key = key;
            this.message = message;
            this.returnVal = returnVal;
        }

        public String getKey() {
            return this.key;
        }

        public String getMessage() {
            return this.message;
        }

        public T getReturnVal() {
            return this.returnVal;
        }
    }
}
