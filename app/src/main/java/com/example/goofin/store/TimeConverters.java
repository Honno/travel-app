package com.example.goofin.store;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeConverters {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDate toDate(String datestamp) {
        return datestamp == null ? null : LocalDate.parse(datestamp);
    }

    @TypeConverter
    public static String toDateString(LocalDate date) {
        return date == null ? null : date.toString();
    }

    @TypeConverter
    public static LocalDateTime toDateTime(String timestamp) {
        return timestamp == null ? null : LocalDateTime.parse(timestamp);
    }

    @TypeConverter
    public static String toDateTimeString(LocalDateTime datetime) {
        return datetime == null ? null : datetime.toString();
    }

}
