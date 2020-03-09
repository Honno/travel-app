package com.example.goofin.store.holidayfeed;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "images")
public class Image extends PlaceableHolidayItem {
    @Override
    public TYPES getItemType() {
        return TYPES.IMAGE;
    }

    @NonNull
    @ColumnInfo(name = "path")
    private String path;

    public Image(String path) {
        this.path = path;
    }

    @NonNull
    public String getPath() {
        return path;
    }

    public void setPath(@NonNull String path) {
        this.path = path;
    }
}
