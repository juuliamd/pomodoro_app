package com.example.pomodoro.viewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pomodoro.AppDatabase;
import com.example.pomodoro.TodoItem;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private com.example.pomodoro.TodoDao todoDao;
    private LiveData<List<TodoItem>> allTodos;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        todoDao = db.todoDao();
        allTodos = todoDao.getAllTodos();
    }

    public LiveData<List<TodoItem>> getAllTodos() {
        return allTodos;
    }

    public void insert(TodoItem todoItem) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.insert(todoItem);
        });
    }
    public void delete(TodoItem todoItem){
        AppDatabase.databaseWriteExecutor.execute(()-> {
                todoDao.delete(todoItem);
        });
    }
}
