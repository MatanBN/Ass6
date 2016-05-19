package TheGame;

import Items.Block;
import Items.Sprite;
import Listeners.BallAdder;
import Listeners.BlockRemover;
import Listeners.ScoreTrackingListener;
import Movement.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 18/05/2016.
 */
public class Green3 implements LevelInformation {


    public int numberOfBalls() {
        return 2;
    }

    public List<Velocity> initialBallVelocities() {
        List <Velocity> v = new ArrayList() ;
        for (int i=0; i<this.numberOfBalls(); i++){
            v.add(new Velocity (0,3));
        }
        return v;
    }

    public int paddleSpeed() {
        return 5;
    }

    public int paddleWidth() {
        return 50;
    }

    public String levelName() {
        return "Green 3";
    }

    public Sprite getBackground() {
        return new GreenBackground();
    }

    public List<Block> blocks() {
        List <Block> blocks = new ArrayList();
        for (int i = 0; i <= 5; i++) {
            int numberOfBlockPerRow = 12 - i;
            int rowYCoordinate = 80 + i * 20;
            for (int j = 0; j <= numberOfBlockPerRow; j++) {
               blocks.add(new Block (740 - (j * 40), rowYCoordinate, 40, 20, chooseRowColor(i)));
            }
        }

        return blocks;
    }

    public int numberOfBlocksToRemove() {
        return 63;
    }

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
