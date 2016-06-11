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
import java.io.LineNumberReader;
import java.io.IOException;
import java.io.InputStream;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Ass6Game initializes and start the game.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class Ass6Game {
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
        Map<String, ArrayList<String>> map = readSubLevels(args[0]);
        GameFlow gameFlow = new GameFlow(myAnimationRunner, gui.getKeyboardSensor(), 7, myScores,
                getListOfLevels(map.get("h").get(1)));
        gameFlow.chooseTask();
    }

    private static Map<String, ArrayList<String>> readSubLevels(String fileName) {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("resources\\" + fileName);
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(is));
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        String[] levelKeyName = null;
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (reader.getLineNumber() % 2 == 1) {
                    levelKeyName = line.split(":");
                    ArrayList<String> levelNamePath = new ArrayList<String>();
                    levelNamePath.add(levelKeyName[1]);
                    map.put(levelKeyName[0], levelNamePath);
                } else {
                    map.get(levelKeyName[0]).add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read set files");
        }
        return map;
    }


    private static List<LevelInformation> getListOfLevels(String levelFileNames) {
        BufferedReader buffer = null;
        List<LevelInformation> levels = null;
        LevelSpecificationReader levelReader = new LevelSpecificationReader(new Rectangle(0, 0, 800, 600));
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream
                ("resources\\" + levelFileNames);
        buffer = new BufferedReader(new InputStreamReader(is));
        levels = levelReader.fromReader(buffer);
        try {
            buffer.close();
        } catch (IOException e) {
            System.out.println("Error closing level file");
        }
        return levels;

    }

}
