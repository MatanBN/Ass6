package TheGame;

import Items.Block;
import Items.RainyBackground;
import Items.Sprite;
import Movement.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The DirectHit class is a level information class that contains information on the first level DirectHit level.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class FinalFour implements LevelInformation {
    /**
     * numberOfBalls method returns the number of balls of the level.
     *
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**
     * initialBallVelocities method returns all of the balls velocities of this level.
     * @return a list of ball velocities..
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> myVels = new ArrayList<Velocity>();
        for (int i = 0; i < 3; ++i) {
            myVels.add(new Velocity(i + 1, i + 1));
        }
        return myVels;
    }

    /**
     * paddleSpeed method returns the speed of the paddle in this level.
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * paddleWidth method returns the width of the paddle in this level.
     * @return the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return 80;
    }

    /**
     * levelName method returns the name of this level.
     * @return the name this level.
     */
    @Override
    public String levelName() {
        return "Final Four";
    }

    /**
     * getBackground method returns a Sprite of the background of this level.
     * @return a Sprite of this background's level.
     */
    @Override
    public Sprite getBackground() {
        return new RainyBackground();
    }

    /**
     * blocks method returns a list of blocks of this level.
     * @return a list of blocks of this level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> myBlocks = new ArrayList<Block>();
        int numberOfBlockPerRow = 760 / 40;
        int hits = 2;
        for (int i = 0; i < 7; ++i) {
            if (i != 0) {
                hits = 1;
            }
            int rowYCoordinate = 80 + i * 20;
            for (int j = 0; j < numberOfBlockPerRow; ++j) {
                Block b = new Block(800 - 60 - (j * 40), rowYCoordinate, 40, 20, chooseRowColor(i));
                b.setHitsNumber(hits);
                myBlocks.add(b);
            }
        }
        return myBlocks;
    }

    /**
     * numberOfBlocksToRemove method returns the number of blocks in this level.
     * @return the number of blocks in this level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return (800 / 40) * 7;
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
            case 6:
                color = Color.lightGray;
                break;
            default:
                color = null;
                break;
        }
        return color;
    }
}
