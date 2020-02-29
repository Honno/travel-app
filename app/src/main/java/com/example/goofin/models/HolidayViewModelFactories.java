package com.example.goofin.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.goofin.models.saveholiday.EditHolidayViewModel;

public class HolidayViewModelFactories {
    private abstract class BaseViewModelFactory implements ViewModelProvider.Factory {
        protected Application application;
        protected long rowId;

        public BaseViewModelFactory(Application application, long rowId) {
            this.application = application;
            this.rowId = rowId;
        }
    }

    public class HolidayViewModelFactory extends BaseViewModelFactory {
        public HolidayViewModelFactory(Application application, long rowId) {
            super(application, rowId);
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new HolidayBaseViewModel(application, rowId);
        }
    }

    public class EditHolidayViewModelFactory extends BaseViewModelFactory {
        public EditHolidayViewModelFactory(Application application, long rowId) {
            super(application, rowId);
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new EditHolidayViewModel(application, rowId);
        }
    }


}
