package com.matthewb.travelapp.activities.holidayfeed;

import android.content.Intent;
import android.view.MenuItem;

import androidx.lifecycle.ViewModelProvider;

import com.matthewb.travelapp.activities.HolidayActivity;
import com.matthewb.travelapp.factories.CreateNoteViewModelFactory;
import com.matthewb.travelapp.models.holidayfeed.CreateNoteViewModel;
import com.matthewb.travelapp.models.holidayfeed.CreateOrEditNoteViewModel;

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
