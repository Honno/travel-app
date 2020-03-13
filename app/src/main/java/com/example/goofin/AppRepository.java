package com.example.goofin;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.goofin.store.AppDatabase;
import com.example.goofin.store.Holiday;
import com.example.goofin.store.HolidayDao;
import com.example.goofin.store.holidayfeed.Image;
import com.example.goofin.store.holidayfeed.ImageDao;
import com.example.goofin.store.holidayfeed.Note;
import com.example.goofin.store.holidayfeed.NoteDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AppRepository {

    private LiveData<List<Holiday>> holidays;

    private HolidayDao holidayDao;

    private NoteDao noteDao;
    private ImageDao imageDao;

    // Using the application dependency prevents this class to be unit tested
    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);

        holidayDao = db.holidayDao();
        holidays = holidayDao.getAll();

        noteDao = db.noteDao();
        imageDao = db.imageDao();
    }

    public LiveData<List<Holiday>> getAllHolidays() {
        return holidays;
    }

    public long insertHoliday(Holiday holiday) throws ExecutionException, InterruptedException {
        return AppDatabase.databaseWriteExecutor.submit(() -> holidayDao.insert(holiday)).get(); // TODO get what?
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

    public LiveData<List<Note>> getNotesFromHoliday(long holidayId) {
        return noteDao.getNotesFromHoliday(holidayId);
    }

    public void insertImage(Image image) {
        AppDatabase.databaseWriteExecutor.execute(() -> imageDao.insert(image));
    }

//    public long insertImageAsync(Image image) throws ExecutionException, InterruptedException {
//        return AppDatabase.databaseWriteExecutor.submit(() -> holidayDao.insert(holiday)).get();
//    }

    public LiveData<List<Image>> getImagesFromHoliday(long holidayId) {
        return imageDao.getImagesFromHoliday(holidayId);
    }

    public LiveData<Note> getNote(long noteId) {
        return noteDao.getNote(noteId);
    }

    public LiveData<List<Image>> getThumbnails() {
        return imageDao.getThumbnails();
    }

    public void setHolidayThumbnail(long holidayId, long imageId) {
        AppDatabase.databaseWriteExecutor.execute(() -> holidayDao.setThumbnail(holidayId, imageId));
    }
}
