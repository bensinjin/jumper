package com.example.benstjohn.jumper.async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.benstjohn.jumper.database.AppDatabase;
import com.example.benstjohn.jumper.entity.LevelScore;

/**
 * Created by benstjohn on 2017-09-29.
 */

public class LevelScoreFetch extends AsyncTask<Void, Void, LevelScore> {
    private Context context;
    private int levelId;
    private TextView tv;

    public LevelScoreFetch(Context context, int levelId, TextView tv) {
        super();
        this.context = context;
        this.levelId = levelId;
        this.tv = tv;
    }

    protected LevelScore doInBackground(Void... params) {
        AppDatabase db = AppDatabase.getInstance(context);
        return db.LevelScoreDao().getLevelScore(levelId);
    }

    protected void onPostExecute(LevelScore ls) {
        String scoreText = "Jumpers saved: ";
        if (ls == null) {
            scoreText += "---";
        } else {
            scoreText += ls.getPointsAwarded() + "/" + ls.getPointsAvailable();
        }
        tv.setText(scoreText);
    }
}
