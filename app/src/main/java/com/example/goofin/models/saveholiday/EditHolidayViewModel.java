package com.example.goofin.models.saveholiday;

import android.app.Application;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class EditHolidayViewModel extends CreateOrEditHolidayViewModel {

    public EditHolidayViewModel(@NonNull Application application, long holidayId) {
        super(application, holidayId);
    }

    public void updateHoliday() {
        appRepository.updateHoliday(makeHoliday());
    }
}

