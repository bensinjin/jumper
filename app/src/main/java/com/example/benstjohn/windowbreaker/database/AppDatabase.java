package com.example.benstjohn.windowbreaker.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.benstjohn.windowbreaker.dao.LevelScoreDao;
import com.example.benstjohn.windowbreaker.entity.LevelScore;

/**
 * Created by benstjohn on 2017-09-28.
 */
@Database(entities = {LevelScore.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LevelScoreDao LevelScoreDao();

    private static AppDatabase dbInstance;

    public static AppDatabase getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "jumper").build();
        }

        return dbInstance;
    }

    public static void destroyInstance() {
        dbInstance = null;
    }
}
