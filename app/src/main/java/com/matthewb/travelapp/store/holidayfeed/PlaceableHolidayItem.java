package com.matthewb.travelapp.store.holidayfeed;

import androidx.room.Entity;

@Entity
abstract class PlaceableHolidayItem extends FeedItem {
    // TODO location relation
    protected PlaceableHolidayItem() {
        super();
    }
}
