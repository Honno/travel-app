package com.matthewb.travelapp.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.matthewb.travelapp.models.holidayfeed.EditNoteViewModel;

public class EditNoteViewModelFactory extends BaseViewModelFactory {

    public EditNoteViewModelFactory(Application application, long rowId) {
        super(application, rowId);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EditNoteViewModel(application, rowId);
    }
}
