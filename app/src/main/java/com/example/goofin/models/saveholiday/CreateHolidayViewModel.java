package com.example.goofin.models.saveholiday;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goofin.store.Holiday;

public class CreateHolidayViewModel extends CreateOrEditHolidayViewModel {
    public CreateHolidayViewModel(@NonNull Application application) {
        super(application);

        name = new MutableLiveData<>();
        startDate = new MutableLiveData<>();
        endDate = new MutableLiveData<>();
    }

    public long insertHolidayAsync() {
        Holiday holiday = new Holiday(
                name.getValue(),
                startDate.getValue(),
                endDate.getValue()
        );

        long rowId = appRepository.insertHolidayAsync(holiday);

        return rowId;
    }
}
