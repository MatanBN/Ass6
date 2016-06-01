import animations.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import gamelevels.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The Ass5Game initializes and start the game.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class Ass5Game {
    /**
     * The main creates the game object, initializes it and runs it.
     *
     * @param args the input from command line.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner myAnimationRunner = new AnimationRunner(60, gui);
        GameFlow gameFlow = new GameFlow(myAnimationRunner, gui.getKeyboardSensor(), 7);
        gameFlow.runLevels(getListOfLevels(args));
    }

    /**
     * The getListOfLevels method creates a list of levels according to the user's choice.
     *
     * @param args the user's choice.
     * @return the current level.
     */
    private static List<LevelInformation> getListOfLevels(String[] args) {
        List<LevelInformation> myLevels = new ArrayList<LevelInformation>();
        myLevels.add(new DirectHit());
        myLevels.add(new WideEasy());
        myLevels.add(new Green3());
        myLevels.add(new FinalFour());
        if (args.length == 0) {
            return myLevels;
        } else {
            List<LevelInformation> levelsChoice = new ArrayList<LevelInformation>();
            for (int i = 0; i < args.length; ++i) {
                try {
                    int levelNum = Integer.parseInt(args[i]);
                    if (levelNum >= 1 && levelNum <= 4) {
                        levelsChoice.add(myLevels.get(levelNum - 1));
                    }
                } catch (Exception e) {
                    System.out.println("Invalid level");
                }
            }
            return levelsChoice;
        }
    }
}
