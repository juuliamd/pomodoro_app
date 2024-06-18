package com.example.pomodoro;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Session {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private long timestamp;

    public Session(String description,long timestamp) {
        this.description=description;

        this.timestamp = timestamp;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


}
