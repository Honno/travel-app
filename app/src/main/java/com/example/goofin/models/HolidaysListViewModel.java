package com.example.goofin.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.goofin.AppRepository;
import com.example.goofin.store.Holiday;

import java.util.List;

public class HolidaysListViewModel extends AndroidViewModel {

    private AppRepository appRepository;

    private LiveData<List<Holiday>> holidays;

    public HolidaysListViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        holidays = appRepository.getAllHolidays();
    }

    public LiveData<List<Holiday>> getAllHolidays() {
        return holidays;
    }

    public LiveData<Holiday> getHoliday(int holiday_id) {
        return appRepository.getHoliday(holiday_id);
    }
}
