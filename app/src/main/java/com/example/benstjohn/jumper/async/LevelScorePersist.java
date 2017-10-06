package com.example.benstjohn.jumper.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.benstjohn.jumper.database.AppDatabase;
import com.example.benstjohn.jumper.entity.LevelScore;

/**
 * Created by benstjohn on 2017-09-28.
 */

public class LevelScorePersist extends AsyncTask<Void, Void, Void> {
    private Context context;
    private LevelScore ls;

    public LevelScorePersist(Context context, LevelScore ls) {
        this.context = context;
        this.ls = ls;
    }

    protected Void doInBackground(Void... params) {
        // TODO should this return something?
        AppDatabase db = AppDatabase.getInstance(context);
        LevelScore existingLevelScore = db.LevelScoreDao().getLevelScore(ls.getId());
        // If we couldn't load a LevelScore by this id insert the new one.
        if (existingLevelScore == null) {
            db.LevelScoreDao().addLevelScore(ls);
        }
        // If the existing LevelScore is less than the current one replace it with the current LevelScore.
        else if (existingLevelScore != null && existingLevelScore.getPointsAwarded() < ls.getPointsAwarded()) {
            db.LevelScoreDao().updateLevelScore(ls);
        }

        return null;
    }
}
