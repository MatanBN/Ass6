package gamelevels;

import game.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * Created by Matan on 6/6/2016.
 */
public class Level implements LevelInformation {
    public int numberOfBalls;
    private List<Velocity> levelsVelocities;
    private int paddSpeed;
    private int paddWidth;
    private String levelName;
    private Sprite background;
    private List<Block> levelBlocks;
    private int numOfBlocksToRemove;


    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return levelsVelocities;
    }

    @Override
    public int paddleSpeed() {
        return paddSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        return levelBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numOfBlocksToRemove;
    }

    public void setLevelsVelocities(List<Velocity> levelsVelocities) {
        this.numberOfBalls = levelsVelocities.size();
        this.levelsVelocities = levelsVelocities;
    }

    public void setPaddSpeed(int paddSpeed) {
        this.paddSpeed = paddSpeed;
    }

    public void setPaddWidth(int paddWidth) {
        this.paddWidth = paddWidth;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public void setLevelBlocks(List<Block> levelBlocks) {
        this.levelBlocks = levelBlocks;
    }

    public void setNumOfBlocksToRemove(int numOfBlocksToRemove) {
        this.numOfBlocksToRemove = numOfBlocksToRemove;
    }

    public void setBackground(Sprite background) {
        this.background = background;
    }
}
