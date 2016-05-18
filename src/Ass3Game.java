import TheGame.DirectHit;
import TheGame.GameLevel;
import TheGame.WideEasy;

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
        GameLevel gameLevel = new GameLevel(new WideEasy());
        gameLevel.initialize();
        gameLevel.run();
    }
}
