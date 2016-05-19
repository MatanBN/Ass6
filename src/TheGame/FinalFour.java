package TheGame;

import Items.Block;
import Items.RainyBackground;
import Items.Sprite;
import Movement.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matan on 5/18/2016.
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> myVels = new ArrayList<Velocity>();
        for (int i = 0; i < 3; ++i) {
            myVels.add(new Velocity(i + 1, i + 1));
        }
        return myVels;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new RainyBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> myBlocks = new ArrayList<Block>();
        int numberOfBlockPerRow = 760 / 40;
        int hits = 1;
        for (int i = 0; i < 7; ++i) {
            if (i == 6) {
                hits = 2;
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
