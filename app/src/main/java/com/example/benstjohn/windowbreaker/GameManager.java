package com.example.benstjohn.windowbreaker;

/**
 * Created by benstjohn on 2017-09-24.
 */

public class GameManager {
    private static final GameManager ourInstance = new GameManager();
    public static int score = 0;
    public static int possibleScore = 0;
    public static int pointsLeftInPlay = 0;

    public static GameManager getInstance() {
        return ourInstance;
    }

    private GameManager() {}


    public static void persistScore(String level, int score, int possibleScore) {
        // TODO
    }

    public static int getScorePercent(int score, int total) {
        return total > 0 ? score / total * 100 : 0;
    }
}
