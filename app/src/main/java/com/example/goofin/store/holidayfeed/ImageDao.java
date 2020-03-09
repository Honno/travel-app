package com.example.goofin.store.holidayfeed;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ImageDao {
    @Insert
    void insert(Image image);

    @Update
    void update(Image image);

    @Delete
    void delete(Image image);

    @Query("SELECT * FROM images WHERE holiday_id=:holidayId ORDER BY created_at ASC")
    LiveData<List<Image>> getImagesFromHoliday(long holidayId);
}
