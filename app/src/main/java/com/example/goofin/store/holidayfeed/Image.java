package com.example.goofin.store.holidayfeed;

import androidx.room.Entity;

@Entity
public class Image extends PlaceableHolidayItem {
    @Override
    public TYPES getItemType() {
        return TYPES.IMAGE;
    }
}
