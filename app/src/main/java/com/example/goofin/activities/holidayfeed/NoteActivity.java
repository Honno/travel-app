package com.example.goofin.activities.holidayfeed;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.goofin.R;
import com.example.goofin.activities.HolidayActivity;
import com.example.goofin.models.saveholiday.EditHolidayViewModel;

public class NoteActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_CONTENTS = "com.example.goofin.EXTRA_NOTE_CONTENTS";

    private String contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        /* Setup toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /* Setup views */
        final TextView textView = findViewById(R.id.edit_note);
        String extraContents = getIntent().getStringExtra(EXTRA_NOTE_CONTENTS);
        if (extraContents != null)
            textView.setText(extraContents);

        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contents = s.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent replyIntent = new Intent();

            if (contents != null) {
                replyIntent.putExtra(EXTRA_NOTE_CONTENTS, contents);
                setResult(RESULT_OK, replyIntent);
            } else {
                setResult(RESULT_CANCELED, replyIntent);
            }

            finish();
        }
        return true;
    }
}
