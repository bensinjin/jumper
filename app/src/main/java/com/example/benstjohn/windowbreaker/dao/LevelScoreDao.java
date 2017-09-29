package com.example.benstjohn.windowbreaker.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.benstjohn.windowbreaker.entity.LevelScore;

import java.util.List;

/**
 * Created by benstjohn on 2017-09-25.
 */

@Dao
public interface LevelScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addLevelScore(LevelScore levelScore);

    @Query("select * from level_scores")
    public List<LevelScore> getAllLevelScores();

    @Query("select * from level_scores where id = :id")
    public LevelScore getLevelScore(long id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLevelScore(LevelScore levelScore);

}