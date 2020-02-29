package com.example.goofin;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goofin.store.AppDatabase;
import com.example.goofin.store.Holiday;
import com.example.goofin.store.HolidayDao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    public long insertHolidayAsync(Holiday holiday) {
        Callable<Long> insertCallable = () -> holidayDao.insert(holiday);
        long rowId = 0;

        Future<Long> future = AppDatabase.databaseWriteExecutor.submit(insertCallable);

        try {
            rowId = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return rowId;
    }

    public LiveData<Holiday> getHoliday(long holidayId) {
        return holidayDao.getHoliday(holidayId);
    }
}
