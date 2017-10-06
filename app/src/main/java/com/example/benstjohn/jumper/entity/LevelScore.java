package com.example.benstjohn.jumper.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by benstjohn on 2017-09-25.
 */
@Entity(tableName = "level_scores")
public class LevelScore {
    @PrimaryKey
    public final int id;
    @ColumnInfo(name = "points_awarded")
    public int pointsAwarded;
    @ColumnInfo(name = "points_available")
    public int pointsAvailable;


    public LevelScore(int id, int pointsAwarded, int pointsAvailable) {
        this.id = id;
        this.pointsAwarded = pointsAwarded;
        this.pointsAvailable = pointsAvailable;
    }

    public int getId() {
        return id;
    }

    public int getPointsAwarded() {
        return pointsAwarded;
    }

    public void setPointsAwarded(int pointsAwarded) {
        this.pointsAwarded = pointsAwarded;
    }

    public int getPointsAvailable() {
        return pointsAvailable;
    }

    public void setPointsAvailable(int pointsAvailable) {
        this.pointsAvailable = pointsAvailable;
    }
}
