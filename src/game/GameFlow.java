package game;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.GameLevel;
import animations.HighScoresAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamelevels.LevelInformation;
import sprites.LiveIndicator;
import sprites.ScoreIndicator;

import javax.swing.text.TabableView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private LiveIndicator liveIndicator;
    private ScoreIndicator score;
    private HighScoresTable scoresTable;


    /**
     * Constructor for the GameFlow class.
     *
     * @param ar    The Animation Runner of the game.
     * @param ks    The KeyboardSensor of the game.
     * @param lives is the number of live.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives, HighScoresTable scoresTable) {
        this.ar = ar;
        this.ks = ks;
        liveIndicator = new LiveIndicator(lives);
        score = new ScoreIndicator();
        this.scoresTable = scoresTable;
    }

    /**
     * runLevels method runs a given list of levels.
     *
     * @param levels a list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
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
        ar.run(new EndScreen(ks, liveIndicator, score));
        DialogManager dialog = ar.getGui().getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        scoresTable.add(new ScoreInfo(name, score.getScore().getValue()));
        ar.run(new HighScoresAnimation(scoresTable, "t", ks));
        try {
            scoresTable.save(new File("highscores"));
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
        ar.getGui().close();
    }
}
