package com.matthewb.travelapp.activities.saveholiday;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.matthewb.travelapp.R;
import com.matthewb.travelapp.activities.HolidayActivity;
import com.matthewb.travelapp.models.saveholiday.CreateHolidayViewModel;
import com.matthewb.travelapp.models.saveholiday.CreateOrEditHolidayViewModel;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

public class CreateHolidayActivity extends CreateOrEditHolidayActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        saveHolidayViewModel.setStartDate(LocalDate.now());
    }

    @Override
    protected CreateOrEditHolidayViewModel getViewModel() {
        return new ViewModelProvider(this).get(CreateHolidayViewModel.class);
    }

    // Setup [ X  Create       ] toolbar
    @Override
    protected void onCreateToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_close_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbar_title_create_holiday));
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected boolean onCreateInsertHolidayButton(Button button) { // TODO use extended FAB
        button.setOnClickListener(v -> {
            CreateHolidayViewModel createHolidayViewModel = (CreateHolidayViewModel) saveHolidayViewModel;

            try {
                long rowId = createHolidayViewModel.insertHolidayAsync();

                Intent replyIntent = new Intent();
                replyIntent.putExtra(HolidayActivity.EXTRA_HOLIDAY_ID, rowId);
                setResult(RESULT_OK, replyIntent);
                finish();
            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(
                        getApplicationContext(),
                        getResources().getString(R.string.message_insert_holiday_failed),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return true;
    }
}
