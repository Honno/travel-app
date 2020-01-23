package com.example.goofin.store;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class HolidayEntityTest {
    private HolidayDao holidayDao;

    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        holidayDao = db.holidayDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeHolidayAndReadMinimal() throws Exception {
        // TODO Create pre-population code for holidays
        HolidayEntity holidayEntity = new HolidayEntity(
            "India",
            LocalDate.of(2019, 05, 26),
            LocalDate.now()
        );

        holidayDao.insert(holidayEntity);

        List<HolidayEntity> holidays = holidayDao.getAll();

        // is equalTo accurate to object's contents

        assertThat(holidays.get(0)).isEqualTo(holidayEntity);
    }

    // TODO Complex holiday tests

}
