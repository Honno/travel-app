package com.matthewb.travelapp.store.holidayfeed;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM notes WHERE holiday_id=:holidayId ORDER BY created_at ASC")
    LiveData<List<Note>> getNotesFromHoliday(long holidayId);

    @Query("SELECT * FROM notes WHERE item_id=:noteId")
    LiveData<Note> getNote(long noteId);
}
