import Movement.AnimationRunner;
import TheGame.*;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * The Ass3Game initializes and start the game.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class Ass3Game {
    /**
     * The main creates the game object, initializes it and runs it.
     * @param args the input from command line.
     */
    public static void main(String[] args) {
        AnimationRunner myAnimationRunner = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(myAnimationRunner, myAnimationRunner.getGui().getKeyboardSensor());
        List<LevelInformation> myLevels = new ArrayList<LevelInformation>();
        myLevels.add(new DirectHit());
        myLevels.add(new WideEasy());
        myLevels.add(new Green3());
        myLevels.add(new FinalFour());
        gameFlow.runLevels(myLevels);
    }
}
