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
        Rectangle borders = new Rectangle(700, 450);
        this.gui = new GUI("Pollock Painting", borders.getWidth(), borders.getHeight());
        this.sleeper = new Sleeper();
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle paddleRec = new Rectangle(198, borders.getHeight()-51, 70, 30);
        Paddle paddle= new Paddle (keyboard, paddleRec, borders, Color.GREEN);
        paddle.getCollisionRectangle().sethitsNumber(-1);
        paddle.addToGame(this);
        Ball ball = new Ball(50, 30, 5, Color.BLUE, environment);
        ball.setVelocity(2,-2);
        Block block1 = new Block (0,0, 20, 450, Color.gray);
        block1.getCollisionRectangle().sethitsNumber(0);
        block1.addToGame(this);
        Block block2= new Block(0,0, 700, 20, Color.gray);
        block2.getCollisionRectangle().sethitsNumber(0);
        block2.addToGame(this);
        Block block3= new Block(0,430, 700, 20, Color.gray);
        block3.getCollisionRectangle().sethitsNumber(0);
        block3.addToGame(this);
        Block block4 = new Block(680,0, 20, 450, Color.gray);
        block4.getCollisionRectangle().sethitsNumber(0);
        block4.addToGame(this);
        ball.addToGame(this);
        for (int i=0; i<=5; i++){
            for (int j=0; j<=12-i; j++) {
                Block block = new Block(borders.getWidth() - 60 - (j*40), 50+i*20, 40, 20, );
                if (i==0){
                    block.getCollisionRectangle().sethitsNumber(2);
                } else {
                    block.getCollisionRectangle().sethitsNumber(1);
                }
                block.addToGame(this);
            }
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
