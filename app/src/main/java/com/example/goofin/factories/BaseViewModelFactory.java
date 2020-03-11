package com.example.goofin.factories;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

public abstract class BaseViewModelFactory implements ViewModelProvider.Factory {
    protected Application application;
    protected long rowId;

    public BaseViewModelFactory(Application application, long rowId) {
        this.application = application;
        this.rowId = rowId;
    }
}
