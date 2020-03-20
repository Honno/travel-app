package com.matthewb.travelapp.store.holidayfeed;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.matthewb.travelapp.store.Holiday;

import java.time.LocalDateTime;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Holiday.class,
        parentColumns = "holiday_id",
        childColumns = "item_id",
        onDelete = CASCADE))
public class FeedItem {

    public enum TYPES {NOTE, IMAGE, PLACE}

    public TYPES getItemType() { return null; }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "item_id")
    private long itemId;

    @NonNull
    @ColumnInfo(name = "holiday_id")
    private long holidayId;

    @ColumnInfo(name = "created_at")
    public LocalDateTime createdAt;

    public FeedItem() {
        createdAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public long getItemId() {
        return itemId;
    }

    public long getHolidayId() {
        return holidayId;
    }

    public void setItemId(long id) {
        itemId = id;
    }

    public void setHolidayId(long holidayId) {
        this.holidayId = holidayId;
    }
}
