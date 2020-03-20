package com.matthewb.travelapp.store.holidayfeed;

import android.net.Uri;

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
    private Uri uri;

    public Image(Uri uri) {
        this.uri = uri;
    }

    @NonNull
    public Uri getUri() {
        return uri;
    }

    public void setUri(@NonNull Uri uri) {
        this.uri = uri;
    }
}
