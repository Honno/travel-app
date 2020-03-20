package com.matthewb.travelapp.activities.holidayfeed;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.matthewb.travelapp.R;
import com.matthewb.travelapp.models.holidayfeed.CreateOrEditNoteViewModel;

abstract class CreateOrEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_CONTENTS = "com.matthewb.goofin.EXTRA_NOTE_CONTENTS";

    protected CreateOrEditNoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        /* Setup toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /* Setup viewmodel */
        noteViewModel = getViewModel();

        /* Setup views */
        final EditText editContentsView = findViewById(R.id.edit_note);
        noteViewModel.getContents().observe(this, contents -> {
            String text = editContentsView.getText().toString();
            if (text.equals(""))
                editContentsView.setText(contents);
        });
        editContentsView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                noteViewModel.setContents(s.toString());
            }

            // TODO data binding would be much nicer
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
    }

    protected abstract CreateOrEditNoteViewModel getViewModel();
}
