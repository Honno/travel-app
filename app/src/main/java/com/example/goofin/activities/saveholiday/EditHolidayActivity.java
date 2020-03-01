package com.example.goofin.activities.saveholiday;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.goofin.R;
import com.example.goofin.activities.HolidayActivity;
import com.example.goofin.factories.EditHolidayViewModelFactory;
import com.example.goofin.factories.HolidayViewModelFactory;
import com.example.goofin.models.HolidayViewModel;
import com.example.goofin.models.saveholiday.CreateHolidayViewModel;
import com.example.goofin.models.saveholiday.CreateOrEditHolidayViewModel;
import com.example.goofin.models.saveholiday.EditHolidayViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.time.LocalDate;

import static android.content.Intent.ACTION_EDIT;

// TODO snackbar undo updates on variables
public class EditHolidayActivity extends CreateHolidayActivity {

    private AlertDialog confirmDialog;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Build and save confirmation dialog */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.edit_holiday_back_dialog_title)
               .setMessage(R.string.edit_holiday_back_dialog_message);
        builder.setPositiveButton(R.string.edit_holiday_back_dialog_ok, (dialog, id) -> onBackPressed());
        builder.setNegativeButton(R.string.edit_holiday_back_dialog_cancel, (dialog, which) -> {});

        confirmDialog = builder.create();
    }


    @Override
    protected CreateOrEditHolidayViewModel getViewModel() {
        long holidayId = getIntent().getExtras().getLong(HolidayActivity.EXTRA_HOLIDAY_ID); // TODO enforce this extra?

        EditHolidayViewModelFactory editHolidayViewModelFactory = new EditHolidayViewModelFactory(this.getApplication(), holidayId);
        return new ViewModelProvider(this, editHolidayViewModelFactory).get(EditHolidayViewModel.class);
    }

    // Setup [ <- Edit   save ] toolbar
    @Override
    protected void onCreateToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * Set up the save button in the toolbar
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit_holiday, menu);
        return true;
    }

    /**
     * Appropriate responses for each item selection
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // TODO check if changes actually made to warrant dialog
                confirmDialog.show();
                break;
            case R.id.save_edit:
                ((EditHolidayViewModel) saveHolidayViewModel).updateHoliday();
                onBackPressed();
                break;
        }
        return true;
    }

    /**
     * Hide insert button
     *
     * @param button
     * @return
     */
    @Override
    protected boolean onCreateInsertHolidayButton(Button button) {
        return false;
    }
}