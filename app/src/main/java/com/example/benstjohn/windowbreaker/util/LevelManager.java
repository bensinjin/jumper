package com.example.benstjohn.windowbreaker.util;

/**
 * Created by benstjohn on 2017-09-24.
 */

public class LevelManager {
    private static final LevelManager ourInstance = new LevelManager();
    public static int score = 0;
    public static int possibleScore = 0;
    public static int pointsLeftInPlay = 0;

    public static LevelManager getInstance() {
        return ourInstance;
    }

    private LevelManager() {}

    public static int getScorePercent(int score, int total) {
        return total > 0 ? score / total * 100 : 0;
    }
}
