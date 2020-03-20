package com.matthewb.travelapp.activities.holidayfeed;

import android.content.Intent;
import android.view.MenuItem;

import androidx.lifecycle.ViewModelProvider;

import com.matthewb.travelapp.factories.EditNoteViewModelFactory;
import com.matthewb.travelapp.models.holidayfeed.CreateOrEditNoteViewModel;
import com.matthewb.travelapp.models.holidayfeed.EditNoteViewModel;


public class EditNoteActivity extends CreateOrEditNoteActivity {
    public static final String EXTRA_NOTE_ID = "EXTRA_NOTE_ID";

    @Override
    protected CreateOrEditNoteViewModel getViewModel() {
        long noteId = getIntent().getExtras().getLong(EXTRA_NOTE_ID); // TODO enforce this extra?

        EditNoteViewModelFactory editNoteViewModelFactory = new EditNoteViewModelFactory(this.getApplication(), noteId);
        return new ViewModelProvider(this, editNoteViewModelFactory).get(EditNoteViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent replyIntent = new Intent();

            try {
                ((EditNoteViewModel) noteViewModel).updateNote();
                setResult(RESULT_OK, replyIntent);
            } catch (IllegalStateException e) {
                // TODO maybe auto delete note if contents removed?
                setResult(RESULT_CANCELED, replyIntent);
            }

            finish();
        }
        return true;
    }
}
