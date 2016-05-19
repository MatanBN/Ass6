package TheGame;

import Geometry.Point;
import Geometry.Rectangle;
import Items.*;
import Listeners.*;
import Movement.*;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * The TheGame.GameLevel class contains a a Items.SpriteCollection which will be all the sprites in
 * the game, a TheGame.GameEnvironment which will include all of the Movement.Collidable objects,
 * and a GUI which will be our game windows.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class GameLevel implements Animation {
    private SpriteCollection sprites; // All of the sprites in the game.
    private GameEnvironment environment; // The game environment.
    private Counter blockCounter;
    private Counter ballCounter;
    private Paddle paddle;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation myLevel;

    /**
     * Constructor to create the TheGame.GameLevel.
     */
    public GameLevel(LevelInformation level, KeyboardSensor key, AnimationRunner runner) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockCounter = new Counter();
        ballCounter = new Counter();
        myLevel = level;
        this.keyboard = key;
        this.runner = runner;
    }

    /**
     * addCollidable method adds a Movement.Collidable object to the game.
     *
     * @param c the Movement.Collidable object to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite method adds a Items.Sprite object to the game.
     *
     * @param s the Items.Sprite object to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * remove Collidable method removes a Collidable object from the game.
     *
     * @param c the Collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removeSprite method removes a Sprite object from the game.
     *
     * @param s the Sprite object to add.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * initialize method draws the borders, ball, paddle and blocks on a new
     * gui.
     */
    public void initialize(Counter score) {
        Rectangle borders = new Rectangle(800, 600);

        addSprite(myLevel.getBackground());

        // Create the paddle.
        Rectangle paddleRec = new Rectangle(360, borders.getHeight() - 51, myLevel.paddleWidth(), 10);
        paddle = new Paddle(keyboard, paddleRec, borders, myLevel.paddleSpeed(), Color.GREEN);
        paddle.addToGame(this);

        // Create the death border.
        Block deathBorder = new Block(0, borders.getMaxY(), borders.getMaxX(), 20,
                Color.WHITE);
        deathBorder.addToGame(this);
        deathBorder.addHitListener(new BallRemover(this, ballCounter));

        //Create the score indicator
        Block playInfo = new Block(0, 0, borders.getMaxX(), 20, Color.white);
        playInfo.addToGame(this);

        // Create the top border.
        createBorder(0, playInfo.getRectangle().getMaxY(), borders.getMaxX(), 20, Color.gray, 0);

        // Create the left border.
        createBorder(0, playInfo.getRectangle().getMaxY() + 20, 20, borders.getMaxY(), Color.gray, 0);

        // Create the right border.
        createBorder(borders.getMaxX() - 20, playInfo.getRectangle().getMaxY() + 20, 20, borders.getMaxY(), Color.gray, 0);

        List<Block> myBlocks = myLevel.blocks();
        for (Block b : myBlocks) {
            b.addHitListener(new BlockRemover(this, blockCounter));
            b.addHitListener(new ScoreTrackingListener(score));
            b.addToGame(this);
            blockCounter.increase(1);
        }
    }


    private void createBorder(int x, int y, int width, int height, Color c, int hits) {
        Block block = new Block(x, y, width, height, c);
        block.setHitsNumber(hits);
        block.addToGame(this);

    }

    public void createBall(Point p, int radius, Velocity v) {
        Ball ball = new Ball(p, radius, Color.WHITE, v, environment);
        ball.addToGame(this);
        ballCounter.increase(1);
    }

    private void createBalls() {
        // Create the balls.
        List<Velocity> myVelocities = myLevel.initialBallVelocities();
        int howMany = myLevel.numberOfBalls();
        int xDistance = 10;
        int yDistance = 10;
        for (int i = 0; i < howMany; ++i) {
            if (i == 0) {
                createBall(new Point(375, 400), 3, myVelocities.get(i));
            } else if (i % 2 == 0) {
                createBall(new Point(375 + xDistance, 400 + yDistance), 3, myVelocities.get(i));
                yDistance += 20;
                xDistance += xDistance;
            } else {
                createBall(new Point(375 - xDistance, 400 + yDistance), 3, myVelocities.get(i));

            }
        }
    }

    /**
     * run method runs the animation loop.
     */
    public void playOneTurn() {
        this.createBalls(); // create the balls
        this.runner.run(new CountdownAnimation(2, 3, sprites)); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    public boolean shouldStop() {
        return !this.running;
    }

    public void doOneFrame(DrawSurface d) {
        // the logic from the previous playOneTurn method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.sprites.drawAllOn(d);
        d.setColor(Color.black);

        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        if (blockCounter.getValue() == 0 || ballCounter.getValue() == 0) {
            this.running = false;
        }
    }

    public Counter getBlockCounter() {
        return blockCounter;
    }

    /*
    public void run() {
        playOneTurn();
        paddle.relocatePaddle(360);
    }
    */

}