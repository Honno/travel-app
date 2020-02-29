package com.example.goofin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.goofin.R;
import com.example.goofin.models.HolidayViewModel;
import com.example.goofin.factories.HolidayViewModelFactory;

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
        holidayViewModel.getName().observe(this, name -> getSupportActionBar().setTitle(name));

        // TODO dates
    }
}
