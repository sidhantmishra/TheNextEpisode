package com.example.thenextepisode;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "show")
public class Show {
    @PrimaryKey
    @NonNull
    private String showName;

    @ColumnInfo(name = "desc")
    public String description;

    Show (String showName) {
        this(showName,"");
    }

    Show (String showName, String desc) {
        this.showName = showName;
        this.description = desc;
    }

    String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
