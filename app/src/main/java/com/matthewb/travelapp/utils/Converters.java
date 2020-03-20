package com.matthewb.travelapp.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.TimeZone;

public class Converters {
    /**
     * Assuming epochTime is in milliseconds.
     *
     * @param fromEpochTime
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDate fromEpochTimeToLocalDate(Long fromEpochTime) {
        return Instant.ofEpochMilli(fromEpochTime)
                .atZone(getZoneId())
                .toLocalDate();
    }

    // TODO caching
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static ZoneId getZoneId() {
        return TimeZone.getDefault().toZoneId();
    }
}
