package game;

import animations.*;
import animations.Menu;
import biuoop.DialogManager;

import biuoop.KeyboardSensor;
import gamelevels.LevelInformation;
import sprites.LiveIndicator;
import sprites.ScoreIndicator;


import java.io.File;
import java.io.IOException;

import java.util.List;

/**
 * The GameFlow class controls the different levels of the game.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private int lives;
    private ScoreIndicator score;
    private HighScoresTable scoresTable;
    private List<LevelInformation> levels;


    /**
     * Constructor for the GameFlow class.
     *
     * @param ar          The Animation Runner of the game.
     * @param ks          The KeyboardSensor of the game.
     * @param lives       is the number of live.
     * @param scoresTable is the HighScores table of the game.
     * @param levels      is a list with the levels of the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives, HighScoresTable scoresTable, List levels) {
        this.ar = ar;
        this.ks = ks;
        this.lives = lives;
        this.score = new ScoreIndicator();
        this.scoresTable = scoresTable;
        this.levels = levels;


    }

    /**
     * chooseTask creates the tasks of the game, calls the menu to display them,
     * and runs the task that the user chooses.
     */
    public void chooseTask() {
        try {
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(ks);

            //Anonymous classes that defines each task's run method.
            Task<Void> hiScores = new Task<Void>() {
                @Override
                public Void run() {
                    ar.run(new HighScoresAnimation(scoresTable, "i", ks));
                    if (ks.isPressed(KeyboardSensor.SPACE_KEY)) {
                        chooseTask();
                    }
                    return null;
                }
            };

            Task<Void> playGame = new Task<Void>() {
                @Override
                public Void run() {
                    runLevels(levels);
                    return null;
                }
            };

            Task<Void> quitGame = new Task<Void>() {
                @Override
                public Void run() {
                    Runtime.getRuntime().exit(1);
                    return null;
                }
            };

            //Adding the tasks to the tasks list.
            menu.addSelection("h", "High scores", hiScores);
            menu.addSelection("s", "Play", playGame);
            menu.addSelection("q", "Quit", quitGame);
            while (true) {
                ar.run(menu);
                // wait for user selection
                Task<Void> task = menu.getStatus();
                task.run();
            }
        } catch (Exception e) {
            System.out.print("Task Error");
        }
    }


    /**
     * runLevels method runs a given list of levels.
     *
     * @param levels a list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        LiveIndicator liveIndicator = new LiveIndicator(lives);
        score = new ScoreIndicator();
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar);
            level.initialize(liveIndicator, score);
            while (level.getBlockCounter().getValue() != 0 && liveIndicator.getValue() != 0) {
                level.playOneTurn();
            }

            if (level.getBlockCounter().getValue() == 0) {
                score.increase(100);
            }

            if (liveIndicator.getValue() == 0) {
                break;
            }
        }
        ar.run(new StopScreenDecorator(ks, "o", new EndScreen(ks, liveIndicator, score)));
        DialogManager dialog = ar.getGui().getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        scoresTable.add(new ScoreInfo(name, score.getScore().getValue()));
        ar.run(new StopScreenDecorator(ks, "t", new HighScoresAnimation(scoresTable, "t", ks)));
        try {
            scoresTable.save(new File("highscores"));
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
        this.chooseTask();
    }

}
