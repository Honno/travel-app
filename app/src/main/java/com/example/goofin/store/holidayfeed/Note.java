package com.example.goofin.store.holidayfeed;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "notes")
public class Note extends PlaceableHolidayItem {

    @ColumnInfo(name = "contents")
    private String contents;

    @Override
    public TYPES getItemType() {
        return TYPES.NOTE;
    }

    public Note() {
        this.contents = null;
    }
    public Note(@NonNull String text) {
        this.contents = text;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
