package game;

import java.io.Serializable;

/**
 * Created by user on 01/06/2016.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public String getName() {
        return this.name;
    }
    public int getScore() {
        return this.score;
    }
}
