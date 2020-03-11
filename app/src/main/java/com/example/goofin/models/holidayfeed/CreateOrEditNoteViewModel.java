package com.example.goofin.models.holidayfeed;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.goofin.AppRepository;
import com.example.goofin.store.holidayfeed.Note;

public abstract class CreateOrEditNoteViewModel extends AndroidViewModel {
    protected final AppRepository appRepository;

    protected MutableLiveData<String> contents = new MutableLiveData<String>();

    public CreateOrEditNoteViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public void setContents(String contents) {
        this.contents.postValue(contents);
    }

    public LiveData<String> getContents() {
        return contents;
    }

    protected Note makeNote() {
        Note note = new Note(contents.getValue());

        return note;
    }
}



