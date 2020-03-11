package com.example.goofin.activities.holidayfeed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.lifecycle.ViewModelProvider;

import com.example.goofin.activities.HolidayActivity;
import com.example.goofin.factories.CreateNoteViewModelFactory;
import com.example.goofin.models.HolidaysListViewModel;
import com.example.goofin.models.holidayfeed.CreateNoteViewModel;
import com.example.goofin.models.holidayfeed.CreateOrEditNoteViewModel;
import com.example.goofin.models.holidayfeed.EditNoteViewModel;

public class CreateNoteActivity extends CreateOrEditNoteActivity {
    @Override
    protected CreateOrEditNoteViewModel getViewModel() {
        long holidayId = getIntent().getExtras().getLong(HolidayActivity.EXTRA_HOLIDAY_ID); // TODO enforce this extra?

        CreateNoteViewModelFactory createNoteViewModelFactory = new CreateNoteViewModelFactory(this.getApplication(), holidayId);
        return new ViewModelProvider(this, createNoteViewModelFactory).get(CreateNoteViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent replyIntent = new Intent();

            try {
                ((CreateNoteViewModel) noteViewModel).insertNote();
                setResult(RESULT_OK, replyIntent);
            } catch (IllegalStateException e) {
                setResult(RESULT_CANCELED, replyIntent);

            }

            finish();
        }
        return true;
    }
}
