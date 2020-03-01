package com.example.goofin.models.saveholiday;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.goofin.models.HolidayBaseViewModel;
import com.example.goofin.store.Holiday;

public class CreateHolidayViewModel extends CreateOrEditHolidayViewModel {
    public CreateHolidayViewModel(@NonNull Application application) {
        super(application);
    }

    // TODO make this an observable
    public long insertHolidayAsync() {
        long rowId = appRepository.insertHolidayAsync(makeHoliday());

        return rowId;
    }
}
