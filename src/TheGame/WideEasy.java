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
            blocks.add(new Block ((i*40)+20, 250, 40, 20, chooseColor(i)));
        }
        return blocks;
    }

    public int numberOfBlocksToRemove() {
        return 19;
    }

    private Color chooseColor(int num) {
        Color color = Color.black;
        if (num<3){
            color = Color.blue;
        }
        else if (num<6){
            color = Color.yellow;
        }
        else if (num<9){
            color = Color.red;
        }
        else if (num<13){
            color = Color.green;
        }
        else if (num<16){
            color = Color.cyan;
        }
        else if (num<19)
        {
            color = Color.orange;
        }
        return color;
    }
}
