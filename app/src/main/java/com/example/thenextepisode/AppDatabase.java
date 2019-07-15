package com.example.thenextepisode;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Show.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShowDao getShowDao();
}
