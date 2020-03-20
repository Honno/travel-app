package com.matthewb.travelapp.store;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.matthewb.travelapp.store.holidayfeed.Image;

public class HolidayAndThumbnail {
    @Embedded
    public Holiday holiday;
    @Relation(
            parentColumn = "image_id",
            entityColumn = "item_id"
    )
    public Image thumbnail;

}
