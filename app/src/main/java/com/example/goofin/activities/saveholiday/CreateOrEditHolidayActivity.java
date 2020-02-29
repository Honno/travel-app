package com.example.goofin.activities.saveholiday;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.goofin.R;
import com.example.goofin.models.HolidayBaseViewModel;
import com.example.goofin.models.saveholiday.CreateOrEditHolidayViewModel;
import com.example.goofin.utils.Converters;
import com.example.goofin.utils.Formatters;
import com.google.android.material.datepicker.MaterialDatePicker;

abstract class CreateOrEditHolidayActivity extends AppCompatActivity {
    protected CreateOrEditHolidayViewModel saveHolidayViewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_holiday);

        /* Get view model */
        saveHolidayViewModel = getViewModel();

        /* Toolbar setup */
        Toolbar toolbar = findViewById(R.id.toolbar);
        onCreateToolbar(toolbar);

        /* Date picker setup */
        MaterialDatePicker<Pair<Long, Long>> datePicker = MaterialDatePicker.Builder.dateRangePicker().build();
        datePicker.addOnPositiveButtonClickListener(selection -> {
            saveHolidayViewModel.setStartDate(Converters.fromEpochTimeToLocalDate(selection.first));
            saveHolidayViewModel.setEndDate(Converters.fromEpochTimeToLocalDate(selection.second));
        });

        /* Views setup */

        // Get references for the desired fields
        final TextView nameView = findViewById(R.id.edit_name);
        final TextView startDateView = findViewById(R.id.start_date);
        final TextView endDateView = findViewById(R.id.end_date);
        final Button editDateButton = findViewById(R.id.edit_dates);
        final Button insertButton = findViewById(R.id.create_holiday);

        // Setup insert button
        boolean insertButtonVisibility = onCreateInsertHolidayButton(insertButton);
        if (insertButtonVisibility)
            insertButton.setVisibility(View.VISIBLE);
        else
            insertButton.setVisibility(View.INVISIBLE);

        // Add date picker to edit date button
        editDateButton.setOnClickListener(view -> {
            androidx.fragment.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
            datePicker.show(fragmentManager, "temp"); // TODO appropriate tag name
        });

        // Update model with edit text fields
        nameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                saveHolidayViewModel.setName(s.toString());
            }

            // Not interested in these interface methods
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });

        // Update fields with the model
        saveHolidayViewModel.getStartDate().observe(this, startDate -> {
            String dataString = startDate.format(Formatters.getLocalDateFormatter());
            startDateView.setText(dataString);
        });
        saveHolidayViewModel.getEndDate().observe(this, endDate -> {
            String dataString = endDate.format(Formatters.getLocalDateFormatter());
            endDateView.setText(dataString);
        });
    }

    protected abstract CreateOrEditHolidayViewModel getViewModel();

    protected abstract void onCreateToolbar(Toolbar toolbar);

    /**
     * Determines insert button functionality, and whether it should be made visible.
     *
     * @param button
     * @return
     */
    protected abstract boolean onCreateInsertHolidayButton(Button button);
}
// TODO Create button at bottom when ACTION_INSERT
// TODO Dialog if you exit but havent save yet


//        // Setup date pickers TODO date ranges
//        startDateButton.setOnClickListener(view -> {
//                    CalendarDateContainer cal_start_date = new CalendarDateContainer(start_date);
//
//                    DatePickerDialog datePickerDialog = new DatePickerDialog(CreateOrEditHolidayActivity.this,
//                            (view1, year, month, day) -> {
//                                start_date = CalendarDateContainer.getLocalDateFromCalendarValues(year, month, day);
//                                startDateButton.setText(start_date.format(Formatters.getLocalDateFormatter()));
//
//                                if (start_date.compareTo(end_date) > 0) {
//                                    end_date = start_date;
//                                    endDateButton.setText(end_date.format(Formatters.getLocalDateFormatter()));
//                                }
//                            }, cal_start_date.getYear(), cal_start_date.getMonth(), cal_start_date.getDay());
//
//                    datePickerDialog.show();
//                }
//        );
//
//        endDateButton.setOnClickListener(view -> { // TODO ui way to say no end date
//                    CalendarDateContainer cal_end_date = new CalendarDateContainer(end_date);
//
//                    DatePickerDialog datePickerDialog = new DatePickerDialog(CreateOrEditHolidayActivity.this,
//                            (view1, year, month, day) -> {
//                                end_date = CalendarDateContainer.getLocalDateFromCalendarValues(year, month, day);
//                                endDateButton.setText(end_date.format(Formatters.getLocalDateFormatter()));
//                            }, cal_end_date.getYear(), cal_end_date.getMonth(), cal_end_date.getDay());
//                    // datePickerDialog.getDatePicker().setMinDate(start_date.toEpochDay()); // TODO min dates
//
//                    datePickerDialog.show();
//                }
//        );


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (getIntent().getAction().equals(ACTION_EDIT)) {
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.menu_edit_holiday, menu);
//            return true;
//        } else {
//            return false; // i.e. don't create options menu
//        }
//    }
