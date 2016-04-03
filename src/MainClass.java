/**
 * Created by Matan on 03/04/2016.
 */
import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.*;

public class MainClass {

    public static void main(String[] args) {
        GUI gui = new GUI("Pollock Painting", 400, 300);

        GameEnvironment game = new GameEnvironment();
        game.addCollidable(new Block(new Rectangle(400,300)));

        game.addCollidable(new Block(new Rectangle(30,40)));
        game.addCollidable(new Block(new Rectangle(40,40,100,100)));
        game.addCollidable(new Block(new Rectangle(100,100)));

        Ball b = new Ball(100,100,5, Color.BLUE,game);

    }
}
