package com.example.goofin.models.saveholiday;

import android.app.Application;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutionException;

public class CreateHolidayViewModel extends CreateOrEditHolidayViewModel {
    public CreateHolidayViewModel(@NonNull Application application) {
        super(application);
    }

    public long insertHolidayAsync() throws ExecutionException, InterruptedException {
        long rowId = appRepository.insertHoliday(makeHoliday());

        return rowId;
    }
}
