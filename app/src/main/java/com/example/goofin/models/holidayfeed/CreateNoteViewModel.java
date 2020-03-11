package com.example.goofin.models.holidayfeed;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.goofin.AppRepository;
import com.example.goofin.store.holidayfeed.Note;

public class CreateNoteViewModel extends CreateOrEditNoteViewModel {

    private long holidayId;

    public CreateNoteViewModel(@NonNull Application application, long holidayId) {
        super(application);

        this.holidayId = holidayId;
    }

    public void insertNote() throws IllegalStateException {
        if (contents.getValue() == null)
            throw new IllegalStateException();
        else {
            Note note = makeNote();
            note.setHolidayId(holidayId);
            appRepository.insertNote(note);
        }
    }
}
