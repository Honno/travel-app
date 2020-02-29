package com.example.goofin.store;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HolidayDao {
    @Insert()
    long insert(Holiday holiday);

    @Query("SELECT * from holidays ORDER BY start_date ASC")
    LiveData<List<Holiday>> getAll();

    @Query("SELECT * from holidays WHERE id=:holidayId")
    LiveData<Holiday> getHoliday(long holidayId);
}