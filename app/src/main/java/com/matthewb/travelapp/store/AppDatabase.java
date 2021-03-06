package com.matthewb.travelapp.store;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.matthewb.travelapp.store.holidayfeed.Image;
import com.matthewb.travelapp.store.holidayfeed.ImageDao;
import com.matthewb.travelapp.store.holidayfeed.Note;
import com.matthewb.travelapp.store.holidayfeed.NoteDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Holiday.class, Note.class, Image.class}, version = 1, exportSchema = false)
@androidx.room.TypeConverters({TypeConverters.class})
public abstract class AppDatabase extends RoomDatabase { // TODO schema exporting??

    public abstract HolidayDao holidayDao();
    public abstract NoteDao noteDao();
    public abstract ImageDao imageDao();

    /**
     * Singleton to avoid multiple AppDatabases being opened at once
     */
    private static volatile AppDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    /**
     * ExecutorService that runs database operations asynchronously on a background thread
     */
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                            // TODO .createFromAsset("database/prepopulated.db") // Used for testing and presentation purposes
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}