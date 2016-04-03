/**
 * Created by Matan on 03/04/2016.
 */
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.*;

public class MainClass {

    public static void main(String[] args) {
        GUI gui = new GUI("Pollock Painting", 400, 300);

        GameEnvironment game = new GameEnvironment();
        game.addCollidable(new Block(new Rectangle(400,300,Color.white)));
        game.addCollidable(new Block(100,10, 30, 10));
        game.addCollidable(new Block(140, 10, 30, 10));
        game.addCollidable(new Block(180, 10, 49, 10));

        Ball b = new Ball(100, 100, 5, Color.BLUE, game);
        b.setVelocity(2,-2);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            // Move and draw the ball;
            b.drawOn(d);
            b.moveOneStep();
            game.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.

        }
    }
}
