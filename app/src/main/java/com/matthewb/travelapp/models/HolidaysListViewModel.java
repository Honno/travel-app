package com.matthewb.travelapp.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.matthewb.travelapp.AppRepository;
import com.matthewb.travelapp.store.Holiday;
import com.matthewb.travelapp.store.holidayfeed.Image;

import java.util.List;

public class HolidaysListViewModel extends AndroidViewModel {

    private AppRepository appRepository;

    private LiveData<List<Holiday>> holidays;

    private LiveData<List<Image>> thumbnails;

    public HolidaysListViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        holidays = appRepository.getAllHolidays();
        thumbnails = appRepository.getThumbnails();
    }

    public LiveData<List<Holiday>> getAllHolidays() {
        return holidays;
    }

    public LiveData<List<Image>> getAllHolidayThumbnails() {
        return thumbnails;
    }
}
