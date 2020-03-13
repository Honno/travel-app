package com.example.goofin.models;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.goofin.AppRepository;
import com.example.goofin.store.Holiday;
import com.example.goofin.store.holidayfeed.Image;

import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

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
