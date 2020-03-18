package com.example.goofin.store;

import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TypeConverters {

    /* Dates */
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

    /* Uris */

    @TypeConverter
    public static Uri fromUriString(String value) {
        return value == null ? null : Uri.parse(value);
    }

    @TypeConverter
    public static String uriToString(Uri uri) {
        return uri.toString();
    }
}
