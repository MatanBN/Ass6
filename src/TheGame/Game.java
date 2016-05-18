package TheGame;

import Geometry.Rectangle;
import Items.*;
import Listeners.*;
import Movement.*;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The TheGame.Game class contains a a Items.SpriteCollection which will be all the sprites in
 * the game, a TheGame.GameEnvironment which will include all of the Movement.Collidable objects,
 * and a GUI which will be our game windows.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class Game implements Animation {
    private SpriteCollection sprites; // All of the sprites in the game.
    private GameEnvironment environment; // The game environment.
    private GUI gui; // The gui windows of the game.
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Paddle paddle;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private Counter liveIndicator;

    /**
     * Constructor to create the TheGame.Game.
     */
    public Game() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockCounter = new Counter();
        ballCounter = new Counter();
        score = new Counter();
        liveIndicator = new Counter();
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
        this.gui = new GUI("Game", borders.getWidth(), borders.getHeight());
        this.runner = new AnimationRunner(gui);
        this.keyboard = gui.getKeyboardSensor();



        // Create the paddle.
        Rectangle paddleRec = new Rectangle(198, borders.getHeight() - 51, 70, 30);
        paddle = new Paddle(keyboard, paddleRec, borders, Color.GREEN);
        paddle.addToGame(this);




        //Create the death border
        Block deathBorder = new Block(0, borders.getMaxY(), borders.getMaxX(), 20,
                Color.WHITE);
        deathBorder.addToGame(this);
        deathBorder.addHitListener(new BallRemover(this, ballCounter));

        //Create the score indicator
        Block playInfo = new Block(0, 0, borders.getMaxX(), 20, Color.white);
        playInfo.addToGame(this);

        //Create the live indicator
        liveIndicator.increase(4);

        // Create the top border.
        createBorder(0, playInfo.getRectangle().getMaxY(), borders.getMaxX(), 20, Color.gray, 0);

        // Create the left border.
        createBorder(0, playInfo.getRectangle().getMaxY() + 20, 20, borders.getMaxY(), Color.gray, 0);

        // Create the right border.
        createBorder(borders.getMaxX() - 20, playInfo.getRectangle().getMaxY() + 20, 20, borders.getMaxY(), Color.gray, 0);

        // The double for loop adds 5 rows of blocks.
        for (int i = 0; i <= 5; i++) {
            int numberOfBlockPerRow = 12 - i;
            int rowYCoordinate = 80 + i * 20;
            for (int j = 0; j <= numberOfBlockPerRow; j++) {
                int hits;
                if (i == 0) {
                    hits = 2;
                } else {
                    hits = 1;
                }

                if ((i + j) % 15 == 0) {
                    Block specialBlock = new Block(borders.getWidth() - 60 - (j * 40),
                            rowYCoordinate, 40, 20, chooseRowColor(i));
                    specialBlock.setHitsNumber(hits);
                    specialBlock.addToGame(this);
                    specialBlock.addHitListener(new BlockRemover(this, blockCounter));
                    specialBlock.addHitListener(new ScoreTrackingListener(score));

                    specialBlock.addHitListener(new BallAdder(this, ballCounter));
                    blockCounter.increase(1);
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

    public void createBall(int x, int y, int radius, Color color, Velocity v) {
        Ball ball = new Ball(x, y, radius, Color.BLUE, environment);

        ball.setVelocity(v);
        ball.addToGame(this);
        ballCounter.increase(1);


    }

    private void createBalls() {
        // Create the balls.
        Velocity v = new Velocity(2, -2);
        for (int i = 1; i < 3; ++i) {
            createBall(50 * i, 70 * i, 5, Color.blue, v);
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
        blockCounter.increase(1);
        block.addHitListener(new BlockRemover(this, blockCounter));
        block.addHitListener(new ScoreTrackingListener(score));
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

    public void run() {
        do {
            playOneTurn();
            paddle.relocatePaddle(300);
            liveIndicator.decrease(1);
        } while (liveIndicator.getValue() != 0 && blockCounter.getValue() != 0);
        gui.close();
    }

    /**
     * run method runs the animation loop.
     */
    public void playOneTurn() {
        this.createBalls(); // create the balls
        this.runner.run(new CountdownAnimation(2,3,sprites)); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    public boolean shouldStop() { return !this.running; }

    public void doOneFrame(DrawSurface d) {
        // the logic from the previous playOneTurn method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.sprites.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(350, 10, "Score: " + score.getValue(), 10);
        d.drawText(100, 10, "Lives: " + liveIndicator.getValue(), 10);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        if (blockCounter.getValue()==0 || ballCounter.getValue()==0){
            this.running=false;
        }
    }


}