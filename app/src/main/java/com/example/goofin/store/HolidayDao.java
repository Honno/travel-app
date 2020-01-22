package com.example.goofin.store;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HolidayDao {
    @Insert()
    void insert(HolidayEntity holidayEntity);

    @Query("SELECT * from holidays ORDER BY start_date ASC")
    List<HolidayEntity> getAll();
}
