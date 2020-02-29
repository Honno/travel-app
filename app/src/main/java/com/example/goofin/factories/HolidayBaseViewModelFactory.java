package com.example.goofin.factories;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

public abstract class HolidayBaseViewModelFactory implements ViewModelProvider.Factory {
    protected Application application;
    protected long rowId;

    public HolidayBaseViewModelFactory(Application application, long rowId) {
        this.application = application;
        this.rowId = rowId;
    }
}
