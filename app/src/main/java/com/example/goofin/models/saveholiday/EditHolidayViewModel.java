package com.example.goofin.models.saveholiday;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.goofin.models.HolidayBaseViewModel;
import com.example.goofin.store.Holiday;

public class EditHolidayViewModel extends HolidayBaseViewModel {
    public EditHolidayViewModel(@NonNull Application application, long holidayId) {
        super(application, holidayId);
    }

    public void updateHoliday() {
        // TODO
    }
}
