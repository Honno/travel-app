package com.matthewb.travelapp.models.holidayfeed;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.matthewb.travelapp.store.holidayfeed.Note;

public class EditNoteViewModel extends CreateOrEditNoteViewModel {
    private final long noteId;

    private LiveData<Note> note;
    private Observer<Note> noteObserver = note -> {
        if (note != null)
            contents.setValue(note.getContents());
    };

    public EditNoteViewModel(@NonNull Application application, long noteId) {
        super(application);

        this.noteId = noteId;
        Log.d("heh", String.valueOf(noteId));

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
