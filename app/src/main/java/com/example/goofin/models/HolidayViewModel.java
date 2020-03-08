package com.example.goofin.models;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.goofin.store.holidayfeed.FeedItem;
import com.example.goofin.store.holidayfeed.Note;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HolidayViewModel extends HolidayBaseViewModel {

    private MediatorLiveData<List<FeedItem>> feed;

    public HolidayViewModel(@NonNull Application application, long holidayId) {
        super(application, holidayId);

        /* Access all related feed items */
        LiveData<List<Note>> notes = appRepository.getNotes(holidayId);

        /* Setup feed mediator */
        feed = new MediatorLiveData<>();
        feed.setValue(new ArrayList<>());
        // Add sources
        feed.addSource(notes, items -> updateFeed(items, FeedItem.TYPES.NOTE));
    }

    private <T extends FeedItem> void updateFeed(List<T> itemsOfType, FeedItem.TYPES type) {
        Stream<FeedItem> feedWithoutType = feed.getValue().stream()
                .filter(feedItem -> feedItem.getItemType() != type);

        List<FeedItem> sortedFeed = Stream.concat(feedWithoutType, itemsOfType.stream())
                .sorted((item1, item2) -> item2.getCreatedAt().compareTo(item1.getCreatedAt()))
                .collect(Collectors.toList());

        feed.setValue(sortedFeed);
    }

    public LiveData<List<FeedItem>> getFeed() {
        return feed;
    }

    public void insertNote(@Nullable Note note) {
        appRepository.insertNote(setHolidayId(note));
    }

    public void updateNote(Note note) {
        appRepository.updateNote(note);
    }

    private <T extends FeedItem> T setHolidayId(T item) {
        item.setHolidayId(holiday.getValue().getHolidayId());
        return item;
    }


}
