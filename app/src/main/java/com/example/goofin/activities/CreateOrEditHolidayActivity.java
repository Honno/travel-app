package com.example.goofin.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.goofin.R;
import com.example.goofin.store.DateConverters;
import com.example.goofin.store.Holiday;
import com.example.goofin.utils.CalendarDateContainer;
import com.example.goofin.utils.Formatters;

import java.time.LocalDate;
import java.util.Calendar;

import static android.content.Intent.ACTION_EDIT;
import static android.content.Intent.ACTION_INSERT;

public class CreateOrEditHolidayActivity extends AppCompatActivity {

    public static final String EXTRA_HOLIDAY = "HOLIDAY"; // TODO what is the convention here

    private LocalDate start_date;
    private LocalDate end_date;

    private Holiday holiday; // TODO just for oncreate?

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_holiday);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Get references for the desired fields
        final TextView holidayNameView = findViewById(R.id.edit_name);
        final Button startDateButton = findViewById(R.id.edit_start_date);
        final Button endDateButton = findViewById(R.id.edit_end_date);
        final Button createHolidayButton = findViewById(R.id.create_holiday);

        // Change activity behaviour depending on whether editing or creating a holiday
        String action = getIntent().getAction();
        if (action.equals(ACTION_EDIT)) {
            // Setup [ <- Edit   save ] toolbar
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getResources().getString(R.string.toolbar_title_edit_holiday));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            // TODO save mark

            // A holiday object is needed for this activity
            holiday = (Holiday) getIntent().getSerializableExtra(EXTRA_HOLIDAY);

            // Setup variables
            start_date = holiday.getStartDate();
            end_date = holiday.getEndDate();

            // Setup views
            holidayNameView.setText(holiday.getName());
            startDateButton.setText(start_date.format(Formatters.getLocalDateFormatter()));
            endDateButton.setText(start_date.format(Formatters.getLocalDateFormatter()));


            // TODO pass into holact

        } else if (action.equals(ACTION_INSERT)) {
            // Setup [ X  Create       ] toolbar
            toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getResources().getString(R.string.toolbar_title_create_holiday));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // TODO How to make it go back to holdayslists hmm

            // Setup variables
            start_date = LocalDate.now();
            end_date = start_date;

            // Setup views
            startDateButton.setText(start_date.format(Formatters.getLocalDateFormatter()));
            // TODO cancelled message appears when you dont enter right stuff
            createHolidayButton.setVisibility(View.VISIBLE);
            createHolidayButton.setOnClickListener(v -> {
                Intent replyIntent = new Intent();

                holiday = new Holiday(
                        holidayNameView.getText().toString(),
                        DateConverters.fromYYYYMMDD(startDateButton.getText().toString()),
                        DateConverters.fromYYYYMMDD(endDateButton.getText().toString())
                );

                replyIntent.putExtra(EXTRA_HOLIDAY, (Parcelable) holiday);
                setResult(RESULT_OK, replyIntent);
                finish();
            });
        } // TODO else throw exception for no supplied action?

        // TODO CALENDAR DATES ARE WEIRD
        // Setup date pickers
        startDateButton.setOnClickListener(view -> {
                    CalendarDateContainer cal_start_date = new CalendarDateContainer(start_date);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(CreateOrEditHolidayActivity.this,
                            (view1, year, month, day) -> {
                                start_date = CalendarDateContainer.getLocalDateFromCalendarValues(year, month, day);
                                startDateButton.setText(start_date.format(Formatters.getLocalDateFormatter()));
                            }, cal_start_date.getYear(), cal_start_date.getMonth(), cal_start_date.getDay());
                    datePickerDialog.show();
                }
        );

        endDateButton.setOnClickListener(view -> {
                    CalendarDateContainer cal_end_date = new CalendarDateContainer(end_date);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(CreateOrEditHolidayActivity.this,
                            (view1, year, month, day) -> {
                                end_date = CalendarDateContainer.getLocalDateFromCalendarValues(year, month, day);
                                endDateButton.setText(end_date.format(Formatters.getLocalDateFormatter()));
                            }, cal_end_date.getYear(), cal_end_date.getMonth(), cal_end_date.getDay());
                    datePickerDialog.getDatePicker().setMinDate(start_date.toEpochDay());
                    datePickerDialog.show();
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getIntent().getAction().equals(ACTION_EDIT)) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_edit_holiday, menu);
            return true;
        } else {
            return false; // i.e. don't create options menu
        }
    }
}
// TODO Create button at bottom when ACTION_INSERT
// TODO Dialog if you exit but havent save yet
