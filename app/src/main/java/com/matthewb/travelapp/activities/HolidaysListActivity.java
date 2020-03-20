package com.matthewb.travelapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.matthewb.travelapp.activities.saveholiday.CreateHolidayActivity;
import com.matthewb.travelapp.adaptors.HolidaysListAdaptor;
import com.matthewb.travelapp.models.HolidaysListViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.matthewb.travelapp.R;

public class HolidaysListActivity extends AppCompatActivity {

    private static final int NEW_HOLIDAY_REQUEST_CODE = 1;

    private HolidaysListViewModel holidaysListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holidays_list);

        /* Setup toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Setup views */
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final HolidaysListAdaptor adaptor = new HolidaysListAdaptor(this);
        adaptor.setOnItemClickListener((holidays, position, v) -> {
            Long holidayId = holidays.get(position).getHolidayId();

            Intent intent = new Intent(v.getContext(), HolidayActivity.class);
            intent.putExtra(HolidayActivity.EXTRA_HOLIDAY_ID, holidayId);

            v.getContext().startActivity(intent);
        });
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        holidaysListViewModel = new ViewModelProvider(this).get(HolidaysListViewModel.class);

        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        holidaysListViewModel.getAllHolidays().observe(this, adaptor::setHolidays);
        holidaysListViewModel.getAllHolidayThumbnails().observe(this, adaptor::setThumbnails);

        ExtendedFloatingActionButton fab = findViewById(R.id.add_holiday);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(HolidaysListActivity.this, CreateHolidayActivity.class);
            intent.setAction(Intent.ACTION_INSERT);
            startActivityForResult(intent, NEW_HOLIDAY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_HOLIDAY_REQUEST_CODE && resultCode == RESULT_OK) {
            int holidayId = data.getExtras().getInt(HolidayActivity.EXTRA_HOLIDAY_ID);

            Intent intent = new Intent(HolidaysListActivity.this, HolidayActivity.class);
            intent.putExtra(HolidayActivity.EXTRA_HOLIDAY_ID, holidayId);

            startActivity(intent);
        } else {
            Toast.makeText( // TODO use snackbars?
                    getApplicationContext(),
                    getResources().getString(R.string.message_cancelled_create_holiday),
                    Toast.LENGTH_SHORT).show(); // TODO too verbose?
        }
    }
}
