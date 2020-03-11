package com.example.goofin.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.goofin.models.holidayfeed.EditNoteViewModel;

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
