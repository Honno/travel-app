package com.matthewb.travelapp.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.matthewb.travelapp.models.holidayfeed.CreateNoteViewModel;

public class CreateNoteViewModelFactory extends BaseViewModelFactory {

    public CreateNoteViewModelFactory(Application application, long rowId) {
        super(application, rowId);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CreateNoteViewModel(application, rowId);
    }
}
