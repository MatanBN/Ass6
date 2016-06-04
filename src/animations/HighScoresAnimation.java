package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.HighScoresTable;
import game.ScoreInfo;

/**
 * Created by user on 01/06/2016.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scoresTable;
    private String endKey;
    private KeyboardSensor ks;
    private boolean stop;

    public HighScoresAnimation(HighScoresTable scores, String endKey, KeyboardSensor ks) {
        this.scoresTable = scores;
        this.endKey = endKey;
        this.ks = ks;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        for (int i = 0; i < scoresTable.currentSize(); ++i) {
            ScoreInfo score = scoresTable.getScore(i);
            d.drawText(50, 50 * (i + 1), score.getName() + ": " + score.getScore(), 20);
        }
        if (ks.isPressed(KeyboardSensor.SPACE_KEY)){
            this.stop=true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;

    }
}
