package game;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.GameLevel;
import biuoop.KeyboardSensor;
import gamelevels.LevelInformation;
import sprites.LiveIndicator;
import sprites.ScoreIndicator;

import javax.swing.text.TabableView;
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

    /**
     * Constructor for the GameFlow class.
     *
     * @param ar    The Animation Runner of the game.
     * @param ks    The KeyboardSensor of the game.
     * @param lives is the number of live.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives) {
        this.ar = ar;
        this.ks = ks;
        liveIndicator = new LiveIndicator(lives);
        score = new ScoreIndicator();
    }

    /**
     * runLevels method runs a given list of levels.
     *
     * @param levels a list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        HighScoresTable tb = new HighScoresTable(3);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar);
            level.initialize(liveIndicator, score);
            tb.add(new ScoreInfo("Mat", score.getScore().getValue()));
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
        List<ScoreInfo> scores = tb.getHighScores();
        for (ScoreInfo sc : scores) {
            System.out.println(sc.getName() + ":" + sc.getScore());
        }
        ar.getGui().close();
    }
}
