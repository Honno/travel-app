package com.example.goofin.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.goofin.factories.HolidayBaseViewModelFactory;
import com.example.goofin.models.saveholiday.EditHolidayViewModel;

public class EditHolidayViewModelFactory extends HolidayBaseViewModelFactory {
    public EditHolidayViewModelFactory(Application application, long rowId) {
        super(application, rowId);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EditHolidayViewModel(application, rowId);
    }
}

