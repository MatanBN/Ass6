package Game;

import GameLevels.*;
import Animations.AnimationRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * The Ass5Game initializes and start the game.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class Ass5Game {
    /**
     * The main creates the game object, initializes it and runs it.
     * @param args the input from command line.
     */
    public static void main(String[] args) {
        AnimationRunner myAnimationRunner = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(myAnimationRunner, myAnimationRunner.getGui().getKeyboardSensor(), 7);
        List<LevelInformation> myLevels = new ArrayList<LevelInformation>();
        myLevels.add(new DirectHit());
        myLevels.add(new WideEasy());
        myLevels.add(new Green3());
        myLevels.add(new FinalFour());
        if (args.length == 0) {
            gameFlow.runLevels(myLevels);
        } else {
            List<LevelInformation> levelsChoise = new ArrayList<LevelInformation>();
            for (int i = 0; i < args.length; ++i) {
                try {
                    int levelNum = Integer.parseInt(args[i]);
                    if (levelNum >= 1 && levelNum <= 4) {
                        levelsChoise.add(myLevels.get(levelNum - 1));
                    }
                } catch (Exception e) {
                }
            }

            gameFlow.runLevels(levelsChoise);
        }
    }
}
