package com.example.goofin;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goofin.store.AppDatabase;
import com.example.goofin.store.Holiday;
import com.example.goofin.store.HolidayDao;
import com.example.goofin.store.holidayfeed.FeedItem;
import com.example.goofin.store.holidayfeed.Note;
import com.example.goofin.store.holidayfeed.NoteDao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AppRepository {

    private LiveData<List<Holiday>> holidays;

    private HolidayDao holidayDao;

    private NoteDao noteDao;

    // Using the application dependency prevents this class to be unit tested
    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);

        holidayDao = db.holidayDao();
        holidays = holidayDao.getAll();

        noteDao = db.noteDao();
    }

    public LiveData<List<Holiday>> getAllHolidays() {
        return holidays;
    }

    public long insertHoliday(Holiday holiday) throws ExecutionException, InterruptedException {
        return AppDatabase.databaseWriteExecutor.submit(() -> holidayDao.insert(holiday)).get();

    }

    public void updateHoliday(Holiday holiday) {
        AppDatabase.databaseWriteExecutor.execute(() -> holidayDao.update(holiday));
    }

    public LiveData<Holiday> getHoliday(long holidayId) {
        return holidayDao.getHoliday(holidayId);
    }

    public void insertNote(@Nullable Note note) {
        AppDatabase.databaseWriteExecutor.execute(() -> noteDao.insert(note));
    }

    public void updateNote(Note note) {
        noteDao.update(note);
    }

    public LiveData<List<Note>> getNotes(long holidayId) {
        return noteDao.getNotes(holidayId);
    }
}
