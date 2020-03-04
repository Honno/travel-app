package com.example.goofin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.goofin.R;
import com.example.goofin.activities.saveholiday.EditHolidayActivity;
import com.example.goofin.models.HolidayViewModel;
import com.example.goofin.factories.HolidayViewModelFactory;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HolidayActivity extends AppCompatActivity {

    public static final String EXTRA_HOLIDAY_ID = "com.example.goofin.EXTRA_HOLIDAY_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

        /* Setup view model */
        long holidayId = getIntent().getExtras().getLong(EXTRA_HOLIDAY_ID); // TODO enforce this extra

        HolidayViewModelFactory holidayViewModelFactory = new HolidayViewModelFactory(this.getApplication(), holidayId);
        HolidayViewModel holidayViewModel = new ViewModelProvider(this, holidayViewModelFactory).get(HolidayViewModel.class);

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
        // FloatingActionButton addImageNote = findViewById(R.id.add_note);
        // Listeners
        feedMenuButton.setOnClickListener(v -> feedMenuButton.setExpanded(!feedMenuButton.isExpanded()));
    }
}
