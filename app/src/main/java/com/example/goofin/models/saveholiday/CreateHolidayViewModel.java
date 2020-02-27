package com.example.goofin.models.saveholiday;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class CreateHolidayViewModel extends CreateOrEditHolidayViewModel {
    public CreateHolidayViewModel(@NonNull Application application) {
        super(application);

        name = new MutableLiveData<>();
        startDate = new MutableLiveData<>();
        endDate = new MutableLiveData<>();
    }
}
