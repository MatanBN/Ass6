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
    private Counter liveIndicator;
    private Counter score;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
        liveIndicator = new Counter();
        liveIndicator.increase(4);
        score = new Counter();

    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar);
            level.initialize(score);
            while (level.getBlockCounter().getValue() != 0 && liveIndicator.getValue() == 0) {
                level.playOneTurn();
            }

            if (level.getBlockCounter().getValue() == 0) {
                score.increase(100);
            } else {
                liveIndicator.decrease(1);
            }

            if (liveIndicator.getValue() == 0) {
                break;
            }
        }
    }
}
