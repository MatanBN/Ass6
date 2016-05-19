package TheGame;

import Movement.AnimationRunner;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Created by Matan on 5/19/2016.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GameLevel game;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GameLevel game) {
        this.ar = ar;
        this.ks = ks;
        this.game = game;
    }

    public void runLevels(List<LevelInformation> levels) {
    }
}
