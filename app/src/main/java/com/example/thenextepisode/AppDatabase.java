package com.example.thenextepisode;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Show.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase db;

    public abstract ShowDao getShowDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (db == null) {
            synchronized (AppDatabase.class) {
                db = Room.databaseBuilder(context,
                        AppDatabase.class, "db-contacts")
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return db;
    }
}
