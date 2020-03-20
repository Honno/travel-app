package com.matthewb.travelapp.store.holidayfeed;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "places")
public class Place extends FeedItem {

    @Override
    public TYPES getItemType() {
        return TYPES.PLACE;
    }

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "location")
    private Location location;

    public Place(@NonNull Location location) {
        this.location = location;
    }


}
