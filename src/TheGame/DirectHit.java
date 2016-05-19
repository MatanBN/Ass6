package TheGame;

import Items.Block;
import Items.RainyBackground;
import Items.Sprite;
import Items.Target;
import Listeners.BlockRemover;
import Listeners.ScoreTrackingListener;
import Movement.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matan on 5/18/2016.
 */
public class DirectHit implements LevelInformation {

    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> myVels = new ArrayList<Velocity>();
        myVels.add(new Velocity(0, -2));
        return myVels;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {

        return new Target();
    }

    @Override
    public List<Block> blocks() {
        Block block = new Block(375, 195, 10, 10, Color.red);
        List<Block> myBlocks = new ArrayList<Block>();
        myBlocks.add(block);
        return myBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
