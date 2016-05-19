package TheGame;

import Items.Counter;
import Movement.AnimationRunner;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Created by Matan on 5/19/2016.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private LiveIndicator liveIndicator;
    private ScoreIndicator score;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
        liveIndicator = new LiveIndicator(4);
        score = new ScoreIndicator();
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar);
            level.initialize(liveIndicator, score);
            while (level.getBlockCounter().getValue() != 0 && liveIndicator.getValue() != 0) {
                level.playOneTurn();
            }

            if (level.getBlockCounter().getValue() == 0) {
                score.increase(100);
            } else {
                liveIndicator.decrease();
            }

            if (liveIndicator.getValue() == 0) {
                break;
            }
        }
    }
}
