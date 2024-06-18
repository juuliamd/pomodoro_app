package com.example.pomodoro.viewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pomodoro.AppDatabase;
import com.example.pomodoro.Session;
import com.example.pomodoro.daos.SessionDao;

import java.util.List;

public class SessionViewModel extends AndroidViewModel {
    private com.example.pomodoro.daos.SessionDao sessionDao;
    private LiveData<List<Session>> allSessions;
    //private SessionDao sessionDao;

    public SessionViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        sessionDao=db.sessionDao();
        allSessions = sessionDao.getAllSessions();
    }

    public LiveData<List<Session>> getAllSessions() {
        return allSessions;
    }

    public void insert(Session session) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sessionDao.insert(session);
        });
    }
}

