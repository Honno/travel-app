package com.example.goofin.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.format.DateTimeFormatter;

public class Formatters {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static DateTimeFormatter getLocalDateFormatter(){
        return DateTimeFormatter.ofPattern("E, MMM dd yyyy");
    }

}
