package com.matthewb.travelapp.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.matthewb.travelapp.AppRepository;
import com.matthewb.travelapp.store.Holiday;
import com.matthewb.travelapp.store.HolidayAndThumbnail;
import com.matthewb.travelapp.store.holidayfeed.Image;

import java.time.LocalDate;

// TODO separate model for edits
public abstract class HolidayBaseViewModel extends AndroidViewModel {

    protected final AppRepository appRepository;

    protected MutableLiveData<String> name = new MutableLiveData();
    protected MutableLiveData<LocalDate> startDate = new MutableLiveData();
    protected MutableLiveData<LocalDate> endDate = new MutableLiveData();
    protected MutableLiveData<Long> thumbnailId = new MutableLiveData<>();

    /* When you are creating a holiday */

    protected HolidayBaseViewModel(@NonNull Application application) {
        super(application);

        appRepository = new AppRepository(application);
    }

    /* When you desire a specific holiday */

    protected LiveData<HolidayAndThumbnail> holidayWithThumbnail;
    protected MutableLiveData<Holiday> holiday;
    protected MutableLiveData<Image> thumbnail;

    private Observer<HolidayAndThumbnail> holidayAndThumbnailObserver = holidayAndThumbnail -> {
        if (holidayAndThumbnail != null) {
            holiday.setValue(holidayAndThumbnail.holiday);
            thumbnail.setValue(holidayAndThumbnail.thumbnail);
        }
    };
    private Observer<Holiday> holidayObserver = holiday -> {
        if (holiday != null) {
            name.setValue(holiday.getName());
            startDate.setValue(holiday.getEndDate());
            endDate.setValue(holiday.getEndDate());
            thumbnailId.setValue(holiday.getImageId());
        }
    };

    protected HolidayBaseViewModel(@NonNull Application application, long holidayId) {
        super(application);

        appRepository = new AppRepository(application);

        holiday = new MutableLiveData<>();
        thumbnail = new MutableLiveData<>();

        holidayWithThumbnail = appRepository.getHolidayWithThumbnail(holidayId);
        holidayWithThumbnail.observeForever(holidayAndThumbnailObserver);

        holiday.observeForever(holidayObserver);
    }

    @Override
    protected void onCleared() {
        if (holiday != null) {
            holidayWithThumbnail.removeObserver(holidayAndThumbnailObserver);
            holiday.removeObserver(holidayObserver);
        }
        super.onCleared();

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

    public LiveData<Image> getThumbnail() {
        return thumbnail;
    }
}


