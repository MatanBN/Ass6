package TheGame;

import Geometry.Rectangle;
import Items.*;
import Listeners.*;
import Movement.Collidable;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The TheGame.Game class contains a a Items.SpriteCollection which will be all the sprites in
 * the game, a TheGame.GameEnvironment which will include all of the Movement.Collidable objects,
 * and a GUI which will be our game windows.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class Game {
    private SpriteCollection sprites; // All of the sprites in the game.
    private GameEnvironment environment; // The game environment.
    private GUI gui; // The gui windows of the game.
    private Counter counter;
    private Counter ballCounter;
    private Counter score;

    /**
     * Constructor to create the TheGame.Game.
     */
    public Game() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        counter = new Counter();
        ballCounter = new Counter();
        score = new Counter();
    }

    /**
     * addCollidable method adds a Movement.Collidable object to the game.
     * @param c the Movement.Collidable object to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite method adds a Items.Sprite object to the game.
     * @param s the Items.Sprite object to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * remove Collidable method removes a Collidable object from the game.
     * @param c the Collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
        if (environment.empty()){
            score.increase(100);
        }
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
    public void initialize() {
        // Create the gui with 700 width and 450 height.
        Rectangle borders = new Rectangle(800, 600);
        this.gui = new GUI("TheGame.Game", borders.getWidth(), borders.getHeight());
        KeyboardSensor keyboard = gui.getKeyboardSensor();

        // Create the ball.
        Ball ball = new Ball(50, 30, 5, Color.BLUE, environment);
        Ball ball2 = new Ball(30, 70, 5, Color.BLUE, environment);
        Ball ball3 = new Ball(100, 70, 5, Color.BLUE, environment);

        ball.setVelocity(2, -2);
        ball2.setVelocity(2, 2);
        ball3.setVelocity(2, 3);

        ball.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
        ballCounter.increase(3);


        // Create the paddle.
        Rectangle paddleRec = new Rectangle(198, borders.getHeight() - 51, 70, 30);
        Paddle paddle = new Paddle(keyboard, paddleRec, borders, Color.GREEN);
        paddle.addToGame(this);


        // Create the borders.
        createBorder(0, 0, 20, borders.getMaxX(), Color.gray, 0);

        createBorder(0, 0, borders.getMaxX(), 20, Color.gray, 0);

        createBorder(borders.getMaxX() - 20, 20, 20, borders.getMaxY(), Color.gray, 0);

        //Create the death border
        Block deathBorder = new Block(0, borders.getMaxY(), borders.getMaxX(), 20,
                Color.WHITE);
        deathBorder.addToGame(this);
        deathBorder.addHitListener(new BallRemover(this, ballCounter));

        //Create the score indicator
        Block ScoreIndicator = new Block (0,0,borders.getMaxX(),10,Color.white);
        ScoreIndicator.addToGame(this);
        ScoreIndicator.addHitListener(new ScoreTrackingListener(score));

        // The double for loop adds 5 rows of blocks.
        for (int i = 0; i <= 5; i++) {
            int numberOfBlockPerRow = 12 - i;
            int rowYCoordinate = 50 + i * 20;
            for (int j = 0; j <= numberOfBlockPerRow; j++) {
                int hits;
                if (i == 0) {
                    hits = 2;
                } else {
                    hits = 1;
                }

                if (i + j % 12 == 0) {
                    Block specialBlock = new Block(borders.getWidth() - 60 - (j * 40),
                            rowYCoordinate, 40, 20, chooseRowColor(i));
                    specialBlock.setHitsNumber(hits);
                    specialBlock.addToGame(this);
                    specialBlock.addHitListener(new BlockRemover(this, counter));

                    specialBlock.addHitListener(new BallAdder(this, ballCounter));
                    counter.increase(1);
                }
                else {
                    createBlock(borders.getWidth() - 60 - (j * 40), rowYCoordinate, 40, 20, chooseRowColor(i), hits);
                }

            }
        }

    }


    private void createBorder(int x, int y, int width, int height, Color c, int hits) {
        Block block = new Block(x, y, width, height, c);
        block.setHitsNumber(hits);
        block.addToGame(this);
    }


    /**
     * createBlock method creates a new block and adds it to the game.
     * @param x the x coordinate of the block.
     * @param y the y coordinate of the block.
     * @param width the width of the block.
     * @param height the height of the block.
     * @param c the color of the block.
     * @param hits the number of hits of the block.
     */
    private void createBlock(int x, int y, int width, int height, Color c, int hits) {
        Block block = new Block(x, y, width, height, c);
        block.setHitsNumber(hits);
        block.addToGame(this);
        counter.increase(1);
        block.addHitListener(new BlockRemover(this,counter));
    }

    /**
     * chooseRowColor method gets the number of row and returns that row color.
     * @param row the row to choose the color to.
     * @return the color for that row.
     */
    private Color chooseRowColor(int row) {
        Color color;
        switch (row) {
            case 0:
                color = Color.blue;
                break;
            case 1:
                color = Color.black;
                break;
            case 2:
                color = Color.red;
                break;
            case 3:
                color = Color.green;
                break;
            case 4:
                color = Color.cyan;
                break;
            case 5:
                color = Color.orange;
                break;
            default:
                color = null;
                break;
        }
        return color;
    }

    /**
     * run method runs the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
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
            if (counter.getValue() == 0 || ballCounter.getValue() == 0) {
                gui.close();
            }

        }
    }

    public GameEnvironment getEnvironment() {
        return environment;
    }
}