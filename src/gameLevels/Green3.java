package gamelevels;

import sprites.Block;
import sprites.GreenBackground;
import sprites.Sprite;
import game.Velocity;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Green3 class is the third level of the game.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class Green3 implements LevelInformation {

    /**
     * numberOfBalls method returns the number of balls of the level.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 2;
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
        return 100;
    }

    /**
     * levelName method returns the name of this level.
     *
     * @return the name this level.
     */
    public String levelName() {
        return "Green 3";
    }

    /**
     * getBackground method returns a Sprite of the background of this level.
     *
     * @return a Sprite of this background's level.
     */
    public Sprite getBackground() {
        return new GreenBackground();
    }

    /**
     * blocks method returns a list of blocks of this level.
     *
     * @return a list of blocks of this level.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList();
        for (int i = 0; i <= 5; i++) {
            int numberOfBlockPerRow = 12 - i;
            int rowYCoordinate = 80 + i * 20;
            for (int j = 0; j <= numberOfBlockPerRow; j++) {
                blocks.add(new Block(740 - (j * 40), rowYCoordinate, 40, 20, chooseRowColor(i)));
            }
        }

        return blocks;
    }

    /**
     * numberOfBlocksToRemove method returns the number of blocks in this level.
     *
     * @return the number of blocks in this level.
     */
    public int numberOfBlocksToRemove() {
        return 63;
    }

    /**
     * chooseRowColor method gets the number of row and returns that row color.
     *
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
                color = Color.yellow;
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
}
