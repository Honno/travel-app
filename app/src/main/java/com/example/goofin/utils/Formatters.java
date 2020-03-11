package com.example.goofin.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.format.DateTimeFormatter;

public class Formatters {
    // TODO caching
    public static DateTimeFormatter getDateFormatter(){
        return DateTimeFormatter.ofPattern("E, MMM dd");
    }

}
