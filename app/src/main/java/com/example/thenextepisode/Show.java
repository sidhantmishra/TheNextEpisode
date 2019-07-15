package com.example.thenextepisode;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "show")
public class Show {
    @PrimaryKey
    @NonNull
    private String showName;

    public Show (String showName) {
        this.showName = showName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
