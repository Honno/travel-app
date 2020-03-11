package com.example.goofin.activities.saveholiday;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.goofin.R;
import com.example.goofin.models.saveholiday.CreateOrEditHolidayViewModel;
import com.example.goofin.utils.Converters;
import com.example.goofin.utils.Formatters;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

abstract class CreateOrEditHolidayActivity extends AppCompatActivity {
    private static final String FRAGMENT_MANAGER_TAG = "DATE_PICKERS";

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
        MaterialDatePicker.Builder<Long> datePickerBuilder = MaterialDatePicker.Builder.datePicker();
        // Start picker
        MaterialDatePicker<Long> startDatePicker = datePickerBuilder.build();
        startDatePicker.addOnPositiveButtonClickListener(selection -> {
            saveHolidayViewModel.setStartDate(Converters.fromEpochTimeToLocalDate(selection));
        });
        // End picker
        MaterialDatePicker<Long> endDatePicker = datePickerBuilder.build();
        endDatePicker.addOnPositiveButtonClickListener(selection -> {
            saveHolidayViewModel.setEndDate(Converters.fromEpochTimeToLocalDate(selection));
        });

        /* Views setup */

        // Get references for the desired fields
        final TextView nameView = findViewById(R.id.edit_name);
        final TextView startDateView = findViewById(R.id.edit_start_date);
        final TextView endDateView = findViewById(R.id.edit_end_date);
        final Button insertButton = findViewById(R.id.create_holiday);

        // Update fields with the model
        saveHolidayViewModel.getStartDate().observe(this, startDate -> {
            if (startDate != null) {
                String dataString = startDate.format(Formatters.getDateFormatter());
                startDateView.setText(dataString);
            }
        });
        saveHolidayViewModel.getEndDate().observe(this, endDate -> {
            if (endDate != null) {
                String dataString = endDate.format(Formatters.getDateFormatter());
                endDateView.setText(dataString);
            }
        });

        // Update model with edit text views
        nameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                saveHolidayViewModel.setName(s.toString());
            }
            // Not interested in these interface methods
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        });

        // Add date picker to edit date views
        startDateView.setInputType(InputType.TYPE_NULL);
        endDateView.setInputType(InputType.TYPE_NULL);
        startDateView.setOnClickListener(v -> startDatePicker.show(this.getSupportFragmentManager(), FRAGMENT_MANAGER_TAG));
        endDateView.setOnClickListener(v -> endDatePicker.show(this.getSupportFragmentManager(), FRAGMENT_MANAGER_TAG));

        // Setup insert button // TODO make this a material fab thingy
        boolean insertButtonVisibility = onCreateInsertHolidayButton(insertButton);
        if (insertButtonVisibility)
            insertButton.setVisibility(View.VISIBLE);
        else
            insertButton.setVisibility(View.INVISIBLE);


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

