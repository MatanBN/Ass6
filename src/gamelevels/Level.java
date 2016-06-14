package gamelevels;

import game.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Level class is a level information which will be red from a file.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class Level implements LevelInformation {
    private int numberOfBalls; // The number of balls of the level.
    private List<Velocity> levelsVelocities; // A list which will include every ball's velocity.
    private int paddSpeed; // The speed of the paddle.
    private int paddWidth; // The width of the paddle.
    private String levelName; // The level name.
    private Sprite background; // The background of the level.
    private List<Block> levelBlocks; // A list of the blocks of the level.
    private int numOfBlocksToRemove; // The number of blocks to remove to pass to the next level.
    private ArrayList<Boolean> checks; // An array list to check that every

    /**
     * Constructor for the Level.
     */
    public Level() {
        checks = new ArrayList<Boolean>();
    }


    /**
     * numberOfBalls method returns the number of balls of the level.
     *
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    /**
     * initialBallVelocities method returns all of the balls velocities of this level.
     *
     * @return a list of ball velocities..
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return levelsVelocities;
    }

    /**
     * paddleSpeed method returns the speed of the paddle in this level.
     *
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return paddSpeed;
    }

    /**
     * paddleWidth method returns the width of the paddle in this level.
     *
     * @return the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return paddWidth;
    }

    /**
     * levelName method returns the name of this level.
     *
     * @return the name this level.
     */
    @Override
    public String levelName() {
        return levelName;
    }

    /**
     * getBackground method returns a Sprite of the background of this level.
     *
     * @return a Sprite of this background's level.
     */
    @Override
    public Sprite getBackground() {
        return background;
    }

    /**
     * blocks method returns a list of blocks of this level.
     *
     * @return a list of blocks of this level.
     */
    @Override
    public List<Block> blocks() {
        return levelBlocks;
    }

    /**
     * numberOfBlocksToRemove method returns the number of blocks in this level.
     *
     * @return the number of blocks in this level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return numOfBlocksToRemove;
    }

    /**
     * setLevelVelocities method sets the list of ball velocities according to the list given.
     *
     * @param velocities is a list of balls velocities to set this level balls velocities.
     */
    public void setLevelsVelocities(List<Velocity> velocities) {
        checks.set(0, true);

        this.numberOfBalls = velocities.size();
        this.levelsVelocities = velocities;
    }

    /**
     * setPaddle method sets the paddle speed according to the speed given.
     *
     * @param paddleSpeed the new speed of the paddle.
     */
    public void setPaddSpeed(int paddleSpeed) {
        checks.add(true);
        this.paddSpeed = paddleSpeed;
    }

    /**
     * setPaddWidth method sets the width of the paddle according to the width given.
     *
     * @param paddleWidth the new width of the paddle.
     */
    public void setPaddWidth(int paddleWidth) {
        checks.add(true);
        this.paddWidth = paddleWidth;
    }

    /**
     * setLevelName method sets the method of the level.
     *
     * @param name the new name of the level.
     */
    public void setLevelName(String name) {
        checks.add(true);
        this.levelName = name;
    }

    /**
     * setLevelBlocks sets the list of the blocks according to the blocks given.
     *
     * @param blocks the new list of the blocks of the level.
     */
    public void setLevelBlocks(List<Block> blocks) {
        checks.add(true);
        this.levelBlocks = blocks;
    }

    /**
     * setNumOfBlocksToRemove method sets the number of blocks to remove according to the number given.
     *
     * @param numOfBlocks the new number of blocks to remove.
     */
    public void setNumOfBlocksToRemove(int numOfBlocks) {
        checks.add(true);
        this.numOfBlocksToRemove = numOfBlocks;
    }

    /**
     * setBackground sets the background of the level according to the background given.
     *
     * @param levelBackground the new background of the level.
     */
    public void setBackground(Sprite levelBackground) {
        checks.add(true);
        this.background = levelBackground;
    }

    /**
     * checkLevel checks if the level has all of the information it needs.
     *
     * @return true if it has all the information needed, false otherwise.
     */
    public boolean checkLevel() {
        return checks.size() == 6;
    }
}
