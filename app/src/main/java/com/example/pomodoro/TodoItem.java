package com.example.pomodoro;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TodoItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String task;

    public TodoItem(String task) {
        this.task = task;
    }

    public String getTask(){
        return task;
    }
    public void setTask(String task){
        this.task=task;
    }
}
