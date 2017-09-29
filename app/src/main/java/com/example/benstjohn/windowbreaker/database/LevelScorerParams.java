package com.example.benstjohn.windowbreaker.database;

import android.content.Context;

/**
 * Created by benstjohn on 2017-09-28.
 */

public class LevelScorerParams {
    private Context context;
    private LevelScore levelScore;

    public LevelScorerParams(Context context, LevelScore levelScore) {
        this.context = context;
        this.levelScore = levelScore;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LevelScore getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(LevelScore levelScore) {
        this.levelScore = levelScore;
    }
}
