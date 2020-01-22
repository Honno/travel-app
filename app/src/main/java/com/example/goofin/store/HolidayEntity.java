package com.example.goofin.store;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "holidays")
public class HolidayEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "start_date")
    public LocalDate startDate;

    @ColumnInfo(name = "end_date")
    public LocalDate endDate;

    public HolidayEntity(@NonNull String name, @NonNull LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
