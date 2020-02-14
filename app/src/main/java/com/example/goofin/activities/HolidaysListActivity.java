package com.example.goofin.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.goofin.adaptors.HolidaysListAdaptor;
import com.example.goofin.models.HolidaysViewModel;
import com.example.goofin.store.Holiday;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.example.goofin.R;

import java.util.List;

public class HolidaysListActivity extends AppCompatActivity {

    private static final int NEW_HOLIDAY_ACTIVITY_REQUEST_CODE = 1;

    private HolidaysViewModel holidaysViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holidays_list);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final HolidaysListAdaptor adaptor= new HolidaysListAdaptor(this);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        holidaysViewModel = new ViewModelProvider(this).get(HolidaysViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        holidaysViewModel.getAllHolidays().observe(this, new Observer<List<Holiday>>() {
            @Override
            public void onChanged(@Nullable final List<Holiday> holidays) {
                // Update the cached copy of the words in the adapter.
                adaptor.setHolidays(holidays);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HolidaysListActivity.this, HolidayActivity.class);
                startActivityForResult(intent, NEW_HOLIDAY_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    /*
     * When spawned activity is finished, do this.
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == NEW_HOLIDAY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            Holiday holiday = new Holiday(data.getStringExtra(HolidayActivity.NEW_HOLIDAY));
//            holidaysViewModel.insert(holiday);
//        } else { // TODO RESULT_CANCELLED
//            Toast.makeText(
//                    getApplicationContext(),
//                    "erm", // TODO something better yeah?
//                    Toast.LENGTH_LONG).show();
//        }
    }
}
