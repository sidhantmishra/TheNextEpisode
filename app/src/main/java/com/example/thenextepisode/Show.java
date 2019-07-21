package com.example.thenextepisode;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "show")
public class Show {
    @PrimaryKey
    @NonNull
    private String showName;

    Show (String showName) {
        this.showName = showName;
    }

    String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
