package com.example.pomodoro.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pomodoro.Session;

import java.util.List;

@Dao
public interface SessionDao {
    @Insert
    void insert(Session session);

    @Query("SELECT * FROM Session ORDER BY timestamp DESC")
    LiveData<List<Session>> getAllSessions();
}
