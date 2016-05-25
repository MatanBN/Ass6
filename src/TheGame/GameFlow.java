package TheGame;

import Movement.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
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
        endScreen();
    }

    /**
     * endScreen method runs the end screen of the game.\.
     */
    public void endScreen (){
        DrawSurface d = ar.getGui().getDrawSurface();
        if (liveIndicator.getValue() == 0) {
            d.setColor(Color.blue);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.white);
            d.drawText(d.getWidth() / 4+110, d.getHeight() / 2-50, "Game Over :(", 32);
            d.drawText(d.getWidth() / 4+100, d.getHeight() / 2+50, "Your score is " +
                    score.getScore().getValue(), 32);
        } else {
            d.setColor(Color.blue);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.yellow);
            d.drawText(d.getWidth() / 4+40, d.getHeight() / 2-50, "Congratulations You Win!", 32);
            d.drawText(d.getWidth() / 4+80, d.getHeight() / 2+50, "Your score is " +
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
