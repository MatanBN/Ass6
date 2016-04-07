import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.*;

/**
 * Created by user on 04/04/2016.
 */

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    public Game () {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
    }

    public void addCollidable(Collidable c){
        environment.addCollidable(c);

    }
    public void addSprite(Sprite s){
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        Rectangle borders = new Rectangle(400, 300);
        this.gui = new GUI("Pollock Painting", borders.getWidth(), borders.getHeight());
        this.sleeper = new Sleeper();
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle paddleRec = new Rectangle(198, borders.getHeight()-33, 70, 30);
        Paddle paddle= new Paddle (keyboard, paddleRec, borders, Color.GREEN);
        paddle.addToGame(this);
        Ball ball = new Ball(50, 10, 5, Color.BLUE, environment);
        ball.setVelocity(2,-2);
        Block block1 = new Block (0,0, 1, 300);
        block1.addToGame(this);
        Block block2= new Block(0,0, 400, 1);
        block2.addToGame(this);
        Block block3= new Block(0,298, 400, 1);
        block3.addToGame(this);
        Block block4 = new Block(398,0, 1, 300);
        block4.addToGame(this);
        ball.addToGame(this);
        for (int i=0; i<=4; i++){
            Block block = new Block(i*100,100, 50, 30);
            block.addToGame(this);
        }

    }

    // Run the game -- start the animation loop.
    public void run(){
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

}
