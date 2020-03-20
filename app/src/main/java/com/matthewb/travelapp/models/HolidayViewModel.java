package com.matthewb.travelapp.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.matthewb.travelapp.store.holidayfeed.FeedItem;
import com.matthewb.travelapp.store.holidayfeed.Image;
import com.matthewb.travelapp.store.holidayfeed.Note;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HolidayViewModel extends HolidayBaseViewModel {

    private final long holidayId;

    private MediatorLiveData<List<FeedItem>> feed;

    private MediatorLiveData<Pair<LocalDate, LocalDate>> dates;

    private LiveData<Image> thumbnail;

    public HolidayViewModel(@NonNull Application application, long holidayId) {
        super(application, holidayId);

        this.holidayId = holidayId;

        /* Access all related feed items */
        LiveData<List<Note>> notes = appRepository.getNotesFromHoliday(holidayId);
        LiveData<List<Image>> images = appRepository.getImagesFromHoliday(holidayId);

        /* Setup feed mediator */
        feed = new MediatorLiveData<>();
        feed.setValue(new ArrayList<>());
        // Add sources
        feed.addSource(notes, items -> updateFeed(items, FeedItem.TYPES.NOTE));
        feed.addSource(images, items -> updateFeed(items, FeedItem.TYPES.IMAGE));

        /* Setup dates mediator */
        dates = new MediatorLiveData<>();
        dates.setValue(new Pair<>(null, null));
        // Add sources
        dates.addSource(startDate, startDate_ ->  {
            if (startDate_ != null) {
                LocalDate endDate_ = null;

                Pair<LocalDate, LocalDate> value = dates.getValue();
                if (value != null) {
                    endDate_ = value.first;
                }

                dates.setValue(new Pair<>(startDate_, endDate_));
            }
        });
        dates.addSource(endDate, endDate_ ->  {
            if (endDate_ != null) {
                LocalDate startDate_ = null;

                Pair<LocalDate, LocalDate> value = dates.getValue();
                if (value != null) {
                    startDate_ = value.second;
                }

                dates.setValue(new Pair<>(startDate_, endDate_));
            }
        });
    }

    private <T extends FeedItem> void updateFeed(List<T> itemsOfType, FeedItem.TYPES type) {
        if (itemsOfType != null && feed.getValue() != null) {
            Stream<FeedItem> feedWithoutType = feed.getValue().stream()
                    .filter(feedItem -> feedItem.getItemType() != type);

            List<FeedItem> sortedFeed = Stream.concat(feedWithoutType, itemsOfType.stream())
                    .sorted((item1, item2) -> item2.getCreatedAt().compareTo(item1.getCreatedAt()))
                    .collect(Collectors.toList());

            feed.setValue(sortedFeed);
        }
    }

    public LiveData<List<FeedItem>> getFeed() {
        return feed;
    }

    public LiveData<Pair<LocalDate, LocalDate>> getDates() {
        return dates;
    }

    public void insertImage(Image image) {
        appRepository.insertImage(setHolidayId(image));
    }

    private <T extends FeedItem> T setHolidayId(T item) {
        item.setHolidayId(holidayId);
        return item;
    }

    public void setThumbnail(long imageId) {
        appRepository.setHolidayThumbnail(holidayId, imageId);
    }

}
