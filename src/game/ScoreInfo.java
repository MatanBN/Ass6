package game;

/**
 * Created by user on 01/06/2016.
 */
public class ScoreInfo {
    private String name;
    private int score;

    public ScoreInfo(String name, int score) {
        this.name=name;
        this.score=score;
    }
    public String getName() {
        return this.name;
    }
    public int getScore() {
        return this.score;
    }
}
