package Game;

import Animations.EndScreen;
import Animations.GameLevel;
import GameLevels.LevelInformation;
import Animations.AnimationRunner;
import Sprites.LiveIndicator;
import Sprites.ScoreIndicator;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The GameFlow class controls the different levels of the game.
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
     * @param ar The Animation Runner of the game.
     * @param ks The KeyboardSensor of the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives) {
        this.ar = ar;
        this.ks = ks;
        liveIndicator = new LiveIndicator(lives);
        score = new ScoreIndicator();
    }

    /**
     * runLevels method runs a given list of levels.
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
        ar.getGui().close();
    }
}
