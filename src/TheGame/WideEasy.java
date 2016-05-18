package TheGame;

import Items.Block;
import Items.Sprite;
import Movement.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by user on 18/05/2016.
 */
public class WideEasy implements LevelInformation {

    public int numberOfBalls() {
        return 11;
    }

    public List<Velocity> initialBallVelocities() {
        List <Velocity> v = new ArrayList() ;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            v.add(new Velocity (0,3));
        }
        return v;
    }

    public int paddleSpeed() {
        return 4;
    }

    public int paddleWidth() {
        return 550;
    }

    public String levelName() {
        return "WideEasy";
    }

    public Sprite getBackground() {
        return new WideBackground();
    }

    public List<Block> blocks() {
        List <Block> blocks = new ArrayList();
        for (int i=0; i<19; i++) {
            Block newBlock = new Block ((i*40)+20, 350, 40, 20, Color.orange);
            blocks.add(newBlock);
        }
        return blocks;
    }

    public int numberOfBlocksToRemove() {
        return 19;
    }
}
