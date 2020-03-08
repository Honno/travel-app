package com.example.goofin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.goofin.R;
import com.example.goofin.activities.holidayfeed.NoteActivity;
import com.example.goofin.activities.saveholiday.EditHolidayActivity;
import com.example.goofin.adaptors.HolidayFeedAdaptor;
import com.example.goofin.models.HolidayViewModel;
import com.example.goofin.factories.HolidayViewModelFactory;
import com.example.goofin.store.holidayfeed.FeedItem;
import com.example.goofin.store.holidayfeed.Note;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HolidayActivity extends AppCompatActivity {

    public static final String EXTRA_HOLIDAY_ID = "com.example.goofin.EXTRA_HOLIDAY_ID";
    private static final int CREATE_NOTE = 1;

    private HolidayViewModel holidayViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

        /* Setup adaptor */
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final HolidayFeedAdaptor adaptor = new HolidayFeedAdaptor(this);
        adaptor.setOnItemClickListener((feed, position, view) -> {
            FeedItem feedItem = feed.get(position);
            FeedItem.TYPES type = feedItem.getItemType();

            switch (type) {
                case NOTE:

            }

        });
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* Setup view model */
        // Get id
        long holidayId = getIntent().getExtras().getLong(EXTRA_HOLIDAY_ID); // TODO enforce this extra
        // Instantiate view model
        HolidayViewModelFactory holidayViewModelFactory = new HolidayViewModelFactory(this.getApplication(), holidayId);
        holidayViewModel = new ViewModelProvider(this, holidayViewModelFactory).get(HolidayViewModel.class);
        // Update adaptor
        holidayViewModel.getFeed().observe(this, adaptor::setFeed);

        /* Setup toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Update title with changes to the holidays name
        //   support action bar's setTitle method did not work in observer, so used this solution:
        //   https://stackoverflow.com/questions/26486730/in-android-app-toolbar-settitle-method-has-no-effect-application-name-is-shown/57635712#57635712
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        holidayViewModel.getName().observe(this, name -> {
            toolbarLayout.setTitle(name);
        });
        // TODO dates

        /* Setup views */
        // Edit holiday
        FloatingActionButton editHolidayButton = findViewById(R.id.edit_holiday);
        editHolidayButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), EditHolidayActivity.class);
            intent.putExtra(EXTRA_HOLIDAY_ID, holidayId);

            startActivity(intent);
        });

        /* Setup feed fabs */
        // Reference fabs
        FloatingActionButton feedMenuButton = findViewById(R.id.feed_menu);
        FloatingActionButton addNoteButton = findViewById(R.id.add_note);
        // Listeners
        feedMenuButton.setOnClickListener(v -> feedMenuButton.setExpanded(!feedMenuButton.isExpanded()));
        addNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(HolidayActivity.this, NoteActivity.class);
            startActivityForResult(intent, CREATE_NOTE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_NOTE) {
            if (resultCode == RESULT_OK) {
                Note note = new Note(data.getStringExtra(NoteActivity.EXTRA_NOTE_CONTENTS));
                holidayViewModel.insertNote(note);
            } else {
                Toast.makeText(getApplicationContext(),
                        R.string.message_insert_feed_item_cancelled,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}
