import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Player {
    private int hp;
    private int score;
    private String name;
    private boolean up;
    private boolean right;

    private static List<String> scores = new LinkedList<>();

    public Player(String name) {
        this.hp = 3;
        this.score = 0;
        this.name = name;
    }

    public void takeDamage(){
        System.out.println("RECEIVED DMG");
        System.out.println("HP: " + (hp-1));
        if(--hp <= 0) die();
    }
    public void die(){
        System.out.println("DIED");
        scores.add(name + ": " + " ".repeat(Math.max(0, Math.max(0, 10 - name.length()))) + score);
        SaveLoad.save();
    }

    public static List<String> getScores() {
        return scores;
    }

    public static void setScores(List<String> scores) {
        Player.scores = scores;
    }

    public int getHp() {
        return hp;
    }
}