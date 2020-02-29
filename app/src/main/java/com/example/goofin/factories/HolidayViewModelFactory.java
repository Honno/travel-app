package com.example.goofin.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.goofin.models.HolidayViewModel;

public class HolidayViewModelFactory extends HolidayBaseViewModelFactory {
    public HolidayViewModelFactory(Application application, long rowId) {
        super(application, rowId);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HolidayViewModel(application, rowId);
    }
}

