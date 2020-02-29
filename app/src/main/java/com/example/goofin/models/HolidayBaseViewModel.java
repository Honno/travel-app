package com.example.goofin.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.goofin.AppRepository;
import com.example.goofin.store.Holiday;

import java.time.LocalDate;

// TODO separate model for edits
public abstract class HolidayBaseViewModel extends AndroidViewModel {

    protected final AppRepository appRepository;

    protected MutableLiveData<String> name = new MutableLiveData();
    protected MutableLiveData<LocalDate> startDate = new MutableLiveData();
    protected MutableLiveData<LocalDate> endDate = new MutableLiveData();

    /* When you are creating a holiday */

    protected HolidayBaseViewModel(@NonNull Application application) {
        super(application);

        appRepository = new AppRepository(application);
    }

    /* When you desire a specific holiday */

    protected LiveData<Holiday> holidayObservable;

    protected Observer<Holiday> holidayObserver = holiday -> {
        if (holiday != null) {
            if (!holiday.getName().equals(name.getValue()))
                name.postValue(holiday.getName());
            if (!holiday.getStartDate().equals(startDate.getValue()))
                startDate.postValue(holiday.getEndDate());
            if (!holiday.getStartDate().equals(endDate.getValue()))
                endDate.postValue(holiday.getEndDate());
        }
    };

    protected HolidayBaseViewModel(@NonNull Application application, long holidayId) {
        super(application);

        appRepository = new AppRepository(application);

        holidayObservable = appRepository.getHoliday(holidayId);
        holidayObservable.observeForever(holidayObserver);
    }

    @Override
    protected void onCleared() {
        if (holidayObservable != null)
            holidayObservable.removeObserver(holidayObserver);

    }

    /* Getters */

    public LiveData<String> getName() {
        return name;
    }

    public LiveData<LocalDate> getStartDate() {
        return startDate;
    }

    public LiveData<LocalDate> getEndDate() {
        return endDate;
    }
}


