package game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 01/06/2016.
 */
public class HighScoresTable {
    private List <ScoreInfo> table;

    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        table = new ArrayList(size);
    }

    // Add a high-score.
    public void add(ScoreInfo score) {
        int i = getRank(score.getScore());
        if (i<table.size()) {
            for (int j = table.size() - 1; j > i; j--) {
                table.add(j, table.get(--j));
            }
            table.add(i,score);
        }
    }

    // Return table size.
    public int size() {
        return table.size();
    }

    // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return table;

    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        int i=0;
        while (table.get(i).getScore()>score){
            i++;
        }
        return i;

    }

    // Clears the table
    public void clear() {
        table.clear();
    }

    // Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        try {
            FileInputStream fis = new FileInputStream("input");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<ScoreInfo> ds = (List<ScoreInfo>) ois.readObject();
            ois.close();
        } catch(Exception e){

        }

    }

    // Save table data to the specified file.
    public void save(File filename) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("output");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (ScoreInfo score : table) {
                oos.writeObject(score);
            }
            oos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) { }
}
