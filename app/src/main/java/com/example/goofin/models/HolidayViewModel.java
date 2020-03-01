package com.example.goofin.models;

import android.app.Application;

import androidx.annotation.NonNull;

public class HolidayViewModel extends HolidayBaseViewModel {

    public HolidayViewModel(@NonNull Application application, long holidayId) {
        super(application, holidayId);
    }
}
