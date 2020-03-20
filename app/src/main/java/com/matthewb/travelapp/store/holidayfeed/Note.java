package com.matthewb.travelapp.store.holidayfeed;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "notes")
public class Note extends PlaceableHolidayItem {

    @NonNull
    @ColumnInfo(name = "contents")
    private String contents;

    @Override
    public TYPES getItemType() {
        return TYPES.NOTE;
    }

    public Note(@NonNull String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
