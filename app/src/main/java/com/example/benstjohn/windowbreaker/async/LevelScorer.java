package com.example.benstjohn.windowbreaker.async;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;

import com.example.benstjohn.windowbreaker.database.AppDatabase;
import com.example.benstjohn.windowbreaker.entity.LevelScore;

/**
 * Created by benstjohn on 2017-09-28.
 */

public class LevelScorer extends AsyncTask<LevelScorerParams, Void, Void> {
    protected Void doInBackground(LevelScorerParams... params) {
        // TODO should this return something?
        for (LevelScorerParams param : params) {
            // TODO this can be improved...
            // Let's try and emulate: https://riggaroo.co.za/android-architecture-components-looking-room-livedata-part-1/ for my room config.
            // Use DI etc...
            AppDatabase db = Room.databaseBuilder(param.getContext().getApplicationContext(), AppDatabase.class, "jumper").build();
            LevelScore existingLevelScore = db.LevelScoreDao().getLevelScore(param.getLevelScore().getId());
            LevelScore currentLevelScore = param.getLevelScore();
            // If we couldn't load a LevelScore by this id insert the new one.
            if (existingLevelScore == null) {
                db.LevelScoreDao().addLevelScore(currentLevelScore);
            }
            // If the existing LevelScore is less than the current one replace it with the current LevelScore.
            else if (existingLevelScore != null &&
                     existingLevelScore.getPointsAwarded() < currentLevelScore.getPointsAwarded()) {
                db.LevelScoreDao().updateLevelScore(currentLevelScore);
            }
        }

        return null;
    }
}
