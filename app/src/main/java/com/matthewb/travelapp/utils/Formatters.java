package com.matthewb.travelapp.utils;

import java.time.format.DateTimeFormatter;

public class Formatters {
    // TODO caching
    public static DateTimeFormatter getDateFormatter(){
        return DateTimeFormatter.ofPattern("E, MMM dd");
    }

}
