package com.example.goofin.models.saveholiday;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.goofin.models.HolidayBaseViewModel;

import java.time.LocalDate;

public abstract class CreateOrEditHolidayViewModel extends HolidayBaseViewModel {
    protected CreateOrEditHolidayViewModel(@NonNull Application application) {
        super(application);
    }

    /* Setters */

    public void setName(String name) {
        this.name.postValue(name);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.postValue(startDate);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate.postValue(endDate);
    }
}
