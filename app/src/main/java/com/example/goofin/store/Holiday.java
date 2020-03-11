package com.example.goofin.store;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "holidays")
public class Holiday {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "holidayId")
    @NonNull
    private long holidayId;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "start_date")
    private LocalDate startDate;

    @ColumnInfo(name = "end_date")
    private LocalDate endDate;

    @ColumnInfo(name = "image_id")
    private long imageId;

    /**
     * Minimal constructor.
     *
     * endDate does not need to be specified so parameter doesn't have @NonNull
     * decorator.
     *
     * @param name
     * @param startDate
     * @param endDate
     */
    public Holiday(@NonNull String name, @NonNull LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public void setHolidayId(long id) {
        holidayId = id;
    }

    public long getHolidayId() {
        return holidayId;
    }

    public String getName() {
        return name;
    }

    public long getImageId() {
        return imageId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
}