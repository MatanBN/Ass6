package TheGame;

import Items.Counter;
import Movement.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.*;
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
        liveIndicator = new LiveIndicator(7);
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
            }

            if (liveIndicator.getValue() == 0) {
                break;
            }
        }
        EndScreen();
    }

    public void EndScreen (){
        DrawSurface d = ar.getGui().getDrawSurface();
        d.setColor(Color.cyan);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        if (liveIndicator.getValue() == 0) {
            d.drawText(d.getWidth() / 4, d.getHeight() / 2, "Game Over. Your score is " +
                    score.getScore().getValue(), 32);
        } else {
            d.drawText(d.getWidth() / 4, d.getHeight() / 2, "You Win! Your score is " +
                    score.getScore().getValue(), 32);
        }
        ar.getGui().show(d);
        while (true) {
            if (this.ks.isPressed(KeyboardSensor.SPACE_KEY)) {
                ar.getGui().close();

            }
        }

    }
}
