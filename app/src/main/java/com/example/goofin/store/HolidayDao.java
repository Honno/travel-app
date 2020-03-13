package com.example.goofin.store;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HolidayDao {
    @Insert
    long insert(Holiday holiday);

    @Update
    void update(Holiday holiday);

    @Query("SELECT * FROM holidays ORDER BY start_date ASC")
    LiveData<List<Holiday>> getAll();

    @Query("SELECT * FROM holidays WHERE holiday_id=:holidayId")
    LiveData<Holiday> getHoliday(long holidayId);

    @Query("UPDATE holidays SET image_id=:imageId WHERE holiday_id=:holidayId")
    void setThumbnail(long holidayId, long imageId);
}