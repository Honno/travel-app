package com.example.goofin.models.saveholiday;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.goofin.models.HolidayBaseViewModel;
import com.example.goofin.store.Holiday;

import java.time.LocalDate;

public abstract class CreateOrEditHolidayViewModel extends HolidayBaseViewModel {

    protected Long holidayId;

    protected CreateOrEditHolidayViewModel(@NonNull Application application) {
        super(application);
    }


    protected CreateOrEditHolidayViewModel(@NonNull Application application, long holidayId) {
        super(application, holidayId);

        this.holidayId = holidayId;
    }

    protected Holiday makeHoliday() {
        Holiday holiday = new Holiday(
                name.getValue(),
                startDate.getValue(),
                endDate.getValue()
        );
        holiday.setImageId(thumbnailId.getValue());
        if (holidayId != null)
            holiday.setHolidayId(holidayId);

        return holiday;
    }

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
