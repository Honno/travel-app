package com.example.goofin.store;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDate;

public class DateConverters {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDate fromYYYYMMDD(String timestamp) {
        return timestamp == null ? null : LocalDate.parse(timestamp);
    }

    @TypeConverter
    public static String localDateToYYYYMMDD(LocalDate date) {
        return date == null ? null : date.toString();
    }

}
