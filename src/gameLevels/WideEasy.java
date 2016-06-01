package gamelevels;

import game.Velocity;
import sprites.Block;
import sprites.Sprite;
import sprites.WideBackground;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The WideEasy class is the second level of the game.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class WideEasy implements LevelInformation {

    /**
     * numberOfBalls method returns the number of balls of the level.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 11;
    }

    /**
     * initialBallVelocities method returns all of the balls velocities of this level.
     *
     * @return a list of ball velocities..
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            v.add(new Velocity(0, 3));
        }
        return v;
    }

    /**
     * paddleSpeed method returns the speed of the paddle in this level.
     *
     * @return the speed of the paddle.
     */
    public int paddleSpeed() {
        return 4;
    }

    /**
     * paddleWidth method returns the width of the paddle in this level.
     *
     * @return the width of the paddle.
     */
    public int paddleWidth() {
        return 550;
    }

    /**
     * levelName method returns the name of this level.
     *
     * @return the name this level.
     */
    public String levelName() {
        return "WideEasy";
    }

    /**
     * getBackground method returns a Sprite of the background of this level.
     *
     * @return a Sprite of this background's level.
     */
    public Sprite getBackground() {
        return new WideBackground();
    }

    /**
     * blocks method returns a list of blocks of this level.
     *
     * @return a list of blocks of this level.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList();
        for (int i = 0; i < 19; i++) {
            blocks.add(new Block((i * 40) + 20, 250, 40, 20, chooseColor(i)));
        }
        return blocks;
    }

    /**
     * numberOfBlocksToRemove method returns the number of blocks in this level.
     *
     * @return the number of blocks in this level.
     */
    public int numberOfBlocksToRemove() {
        return 19;
    }

    /**
     * chooseRowColor method gets the number of row and returns that row color.
     *
     * @param num the num to choose the color to.
     * @return the color for that row.
     */
    private Color chooseColor(int num) {
        Color color = Color.black;
        if (num < 3) {
            color = Color.blue;
        } else if (num < 6) {
            color = Color.yellow;
        } else if (num < 9) {
            color = Color.red;
        } else if (num < 13) {
            color = Color.green;
        } else if (num < 16) {
            color = Color.cyan;
        } else if (num < 19) {
            color = Color.orange;
        }
        return color;
    }
}
