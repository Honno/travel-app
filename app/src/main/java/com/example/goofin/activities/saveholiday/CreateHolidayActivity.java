package com.example.goofin.activities.saveholiday;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.goofin.R;
import com.example.goofin.activities.HolidayActivity;
import com.example.goofin.models.saveholiday.CreateHolidayViewModel;
import com.example.goofin.models.HolidayBaseViewModel;

import java.time.LocalDate;

public class CreateHolidayActivity extends CreateOrEditHolidayActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        saveHolidayViewModel.setStartDate(LocalDate.now());
    }

    @Override
    protected HolidayBaseViewModel getViewModel() {
        return new ViewModelProvider(this).get(CreateHolidayViewModel.class);
    }

    // Setup [ X  Create       ] toolbar
    @Override
    protected void onCreateToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbar_title_create_holiday));
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected boolean onCreateInsertHolidayButton(Button button) {
        button.setOnClickListener(v -> {
            CreateHolidayViewModel createHolidayViewModel = (CreateHolidayViewModel) saveHolidayViewModel;

            long rowId = createHolidayViewModel.insertHolidayAsync();

            Intent replyIntent = new Intent();
            replyIntent.putExtra(HolidayActivity.EXTRA_HOLIDAY_ID, rowId);
            setResult(RESULT_OK, replyIntent);
            finish();
        });

        return true;
    }
}

/* Setup views */
// TODO cancelled message appears when you dont enter right stuff
//     createHolidayButton.setVisibility(View.VISIBLE);
//        createHolidayButton.setOnClickListener(v -> {
// TODO for viewsmodel yea?


//
//            AppRepository appRepository = new AppRepository(getApplication());
//            appRepository.insertHoliday(holiday);
//
//            Intent replyIntent = new Intent();
//            replyIntent.putExtra(HolidayActivity.EXTRA_HOLIDAY_ID, 0);
//            setResult(RESULT_OK, replyIntent);
//            finish();
//        }


//    // Setup variables
//    start_date = LocalDate.now();
//    end_date = start_date;
//
//    // Setup views
//        startDateButton.setText(start_date.format(Formatters.getLocalDateFormatter()));
//    // TODO cancelled message appears when you dont enter right stuff
//        createHolidayButton.setVisibility(View.VISIBLE);
//        createHolidayButton.setOnClickListener(v -> {
//
//        Holiday holiday = new Holiday(
//                holidayNameView.getText().toString(),
//                start_date,
//                end_date
//        );
//
//        AppRepository appRepository = new AppRepository(getApplication());
//        appRepository.insertHoliday(holiday);
//

//
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
//
//    }
//
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

