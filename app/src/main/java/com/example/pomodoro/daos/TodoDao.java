package com.example.pomodoro;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert
    void insert(TodoItem todoItem);

    @Delete
    void delete(TodoItem todoItem);

    @Query("SELECT * FROM TodoItem ORDER BY id DESC")
    LiveData<List<TodoItem>> getAllTodos();
}
