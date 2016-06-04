package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;


/**
 * Created by Matan on 6/2/2016.
 */
public class MenuAnimation<T> implements Menu {
    private ArrayList<Selection> menuSelections;
    private KeyboardSensor ks;
    private boolean stopper;
    private String key;

    public MenuAnimation(KeyboardSensor ks) {
        menuSelections = new ArrayList<Selection>();
        this.ks = ks;
        this.stopper = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.blue);
        d.fillRectangle(0, 0, d.getWidth() - 20, d.getHeight());
        d.setColor(Color.YELLOW);
        d.drawText(200, 100, "please choose:", 32);
        for (int i=1; i<=menuSelections.size(); i++) {
            d.drawText(200, 100 + (i * 50), menuSelections.get(i-1).getMessage() + "- "
                    + menuSelections.get(i-1).getKey(), 20);
        }
        for (int i=0; i<menuSelections.size();i++){
           if (ks.isPressed(menuSelections.get(i).getKey())){
               key = menuSelections.get(i).getKey();
               this.stopper=true;
           }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stopper;
    }

    @Override
    public void addSelection(String key, String message, Object returnVal) {
        menuSelections.add(new Selection(key, message, (T) returnVal));
    }

    @Override
    public T getStatus() {
        switch (key){
            case "h":
                return menuSelections.get(0).returnVal;
            case "s":
                return menuSelections.get(1).returnVal;
            default:
                return menuSelections.get(2).returnVal;
        }
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
