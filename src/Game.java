import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The Game class contains a a SpriteCollection which will be all the sprites in
 * the game, a GameEnvironment which will include all of the Collidable objects,
 * and a GUI which will be our game windows.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class Game {
    private SpriteCollection sprites; // All of the sprites in the game.
    private GameEnvironment environment; // The game environment.
    private GUI gui; // The gui windows of the game.

    /**
     * Constructor to create the Game.
     */
    public Game() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
    }

    /**
     * addCollidable method adds a Collidable object to the game.
     * @param c the Collidable object to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite method adds a Sprite object to the game.
     * @param s the Sprite object to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * initialize method draws the borders, ball, paddle and blocks on a new
     * gui.
     */
    public void initialize() {
        // Create the gui with 700 width and 450 height.
        Rectangle borders = new Rectangle(700, 450);
        this.gui = new GUI("Game", borders.getWidth(), borders.getHeight());
        KeyboardSensor keyboard = gui.getKeyboardSensor();

        // Create the ball.
        Ball ball = new Ball(50, 30, 5, Color.BLUE, environment);
        Ball ball2 = new Ball(30, 70, 5, Color.BLUE, environment);
        ball.setVelocity(2, -2);
        ball2.setVelocity(2, 2);
        ball.addToGame(this);
        ball2.addToGame(this);

        // Create the paddle.
        Rectangle paddleRec = new Rectangle(198, borders.getHeight() - 51, 70, 30);
        Paddle paddle = new Paddle(keyboard, paddleRec, borders, Color.GREEN);
        paddle.addToGame(this);

        // Create the borders.
        createBlock(0, 0, 20, 450, Color.gray, 0);
        createBlock(0, 0, 700, 20, Color.gray, 0);
        createBlock(0, 430, 700, 20, Color.gray, 0);
        createBlock(680, 0, 20, 450, Color.gray, 0);

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
                createBlock(borders.getWidth() - 60 - (j * 40), rowYCoordinate, 40, 20, chooseRowColor(i), hits);
            }
        }
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

        }
    }
}
