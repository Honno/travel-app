package com.example.goofin.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.goofin.AppRepository;
import com.example.goofin.store.Holiday;

public class HolidayViewModel extends HolidayBaseViewModel {

    protected HolidayViewModel(@NonNull Application application, long holidayId) {
        super(application, holidayId);
    }
}
