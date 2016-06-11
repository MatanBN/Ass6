package gamelevels;

import game.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.ArrayList;
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
    private ArrayList<Boolean> checks;

    public Level() {
        checks = new ArrayList<Boolean>();
        for (int i = 0; i < 7; ++i) {
            checks.add(false);
        }
    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        checks.add(true);
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
        checks.set(0, true);

        this.numberOfBalls = levelsVelocities.size();
        this.levelsVelocities = levelsVelocities;
    }

    public void setPaddSpeed(int paddSpeed) {
        checks.set(1, true);
        this.paddSpeed = paddSpeed;
    }

    public void setPaddWidth(int paddWidth) {
        checks.set(2, true);
        this.paddWidth = paddWidth;
    }

    public void setLevelName(String levelName) {
        checks.set(3, true);
        this.levelName = levelName;
    }

    public void setLevelBlocks(List<Block> levelBlocks) {
        checks.set(4, true);
        this.levelBlocks = levelBlocks;
    }

    public void setNumOfBlocksToRemove(int numOfBlocksToRemove) {
        checks.set(5, true);
        this.numOfBlocksToRemove = numOfBlocksToRemove;
    }

    public void setBackground(Sprite background) {
        checks.set(6, true);
        this.background = background;
    }

    public boolean checkLevel() {
        for (boolean b : checks) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
