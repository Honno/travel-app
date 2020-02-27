package com.example.goofin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.goofin.R;
import com.example.goofin.models.HolidaysListViewModel;
import com.example.goofin.store.Holiday;

public class HolidayActivity extends AppCompatActivity {

    public static final String EXTRA_HOLIDAY_ID = "com.example.goofin.EXTRA_HOLIDAY_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        HolidaysListViewModel holidaysListViewModel = new ViewModelProvider(this).get(HolidaysListViewModel.class);
        int holiday_id = getIntent().getExtras().getInt(EXTRA_HOLIDAY_ID);
        LiveData<Holiday> holidayLiveData = holidaysListViewModel.getHoliday(holiday_id);
        Log.d("heh", String.valueOf(holiday_id));

        holidayLiveData.observe(this, holiday -> {
            if (holiday != null)
                getSupportActionBar().setTitle(holiday.getName());
        });

        // TODO observe thing for when you edit a holiday

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
