import animations.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import game.HighScoresTable;
import geometry.Rectangle;
import leveldevelopment.LevelSpecificationReader;
import gamelevels.*;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;


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
        HighScoresTable myScores = new HighScoresTable(10);
        try{
            myScores.load(new File("highscores"));
        }catch (Exception e){
            System.out.print("Unable to load the file");
        }
        GameFlow gameFlow = new GameFlow(myAnimationRunner, gui.getKeyboardSensor(), 7, myScores,
                getListOfLevels(args[0]));
        gameFlow.chooseTask();
    }

    private static List<LevelInformation> getListOfLevels(String levelFileNames) {
        BufferedReader buffer = null;
        List<LevelInformation> levels = null;
        LevelSpecificationReader levelReader = new LevelSpecificationReader(new Rectangle(0, 0, 800, 600));
        try {
            File file = new File(levelFileNames);
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            levels = levelReader.fromReader(buffer);
        } catch (IOException e) {
            System.out.println("Unable reading levels file");
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    System.out.println("Error closing level file");
                }
            }
        }
        return levels;

    }

}
