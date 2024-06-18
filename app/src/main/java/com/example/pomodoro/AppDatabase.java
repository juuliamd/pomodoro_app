package com.example.pomodoro;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Session.class, TodoItem.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract com.example.pomodoro.daos.SessionDao sessionDao();
    public abstract com.example.pomodoro.TodoDao todoDao();
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Definiowanie migracji z wersji 1 do 2
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Migracja schematu: dodanie kolumny "description" do tabeli "Session"
            database.execSQL("ALTER TABLE Session ADD COLUMN description TEXT");
        }
    };
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "pomodoro_database")
                            .addMigrations(MIGRATION_1_2)  // Dodaj migrację tutaj
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
