package com.example.thenextepisode;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ShowDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Show... shows);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    public void update(Show... shows);

    @Delete
    public void delete(Show show);

    @Query("SELECT * FROM show")
    public List<Show> getAllShows();

    @Query("DELETE FROM show")
    public void deleteAllShows();
}
