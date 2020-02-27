package com.example.goofin.models.saveholiday;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goofin.AppRepository;

import java.time.LocalDate;

// TODO separate model for edits
public abstract class CreateOrEditHolidayViewModel extends AndroidViewModel {

    protected final AppRepository appRepository;

    protected MutableLiveData<String> name;
    protected MutableLiveData<LocalDate> startDate;
    protected MutableLiveData<LocalDate> endDate;

    /**
     * Implement constructor in children to determine initial attributes setup.
     * @param application
     */
    public CreateOrEditHolidayViewModel(@NonNull Application application) {
        super(application);

        appRepository = new AppRepository(application);
    }

    public final void setName(String name) {
        this.name.postValue(name);
    }

    public final void setStartDate(LocalDate startDate) {
        this.startDate.postValue(startDate);
    }

    public final void setEndDate(LocalDate endDate) {
        this.endDate.postValue(endDate);
    }

    public final LiveData<String> getName(String input) {
        return name;
    }

    public final LiveData<LocalDate> getStartDate() {
        return startDate;
    }

    public final LiveData<LocalDate> getEndDate() {
        return endDate;
    }
}
