package com.example.goofin.models.holidayfeed;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.goofin.AppRepository;
import com.example.goofin.store.holidayfeed.Note;

public class EditNoteViewModel extends CreateOrEditNoteViewModel {
    private final long noteId;

    private LiveData<Note> note;
    private Observer<Note> noteObserver = note -> {
        if (note != null) {
            if (!note.getContents().equals(contents.getValue())) {
                contents.postValue(note.getContents());
            }
        }
    };

    public EditNoteViewModel(@NonNull Application application, long noteId) {
        super(application);

        this.noteId = noteId;

        note = appRepository.getNote(noteId);
        note.observeForever(noteObserver);
    }

    public void updateNote() throws IllegalStateException {
        if (contents.getValue() != null)
            throw new IllegalStateException();
        else
            appRepository.updateNote(makeNote());
    }

    @Override
    protected void onCleared() {
        if (note != null)
            note.removeObserver(noteObserver);
        super.onCleared();
    }

    @Override
    protected Note makeNote() {
        Note note = super.makeNote();
        note.setItemId(noteId);

        return note;
    }
}