package com.example.goofin;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goofin.store.AppDatabase;
import com.example.goofin.store.Holiday;
import com.example.goofin.store.HolidayDao;

import java.util.List;

public class AppRepository {
    private HolidayDao holidayDao;
    private LiveData<List<Holiday>> holidays;
    private MutableLiveData<Long> lastInsertedHolidayId;

    // Using the application dependency prevents this class to be unit tested
    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        holidayDao = db.holidayDao();
        holidays = holidayDao.getAll();
        lastInsertedHolidayId = new MutableLiveData<>();
    }

    public LiveData<List<Holiday>> getAllHolidays() {
        return holidays;
    }

    public LiveData<Long> insertHolidayAsync(final Holiday holiday) {
        AppDatabase.databaseWriteExecutor.execute(() -> holidayDao.insert(holiday));
        return null; // TODO what
    }

    public LiveData<Holiday> getHoliday(int holiday_id) {
        return holidayDao.getHoliday(holiday_id);
    }
}
