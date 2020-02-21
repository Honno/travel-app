package com.example.goofin.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

/**
 * Container of date objects to work with Calendar-based components in Android.
 */
public class CalendarDateContainer {
    private int day;
    private int month;
    private int year;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDate getLocalDateFromCalendarValues(int year, int month, int day) {
        return LocalDate.of(year, month + 1, day);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CalendarDateContainer(LocalDate date) {
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue() - 1;
        this.year = date.getYear();

    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
