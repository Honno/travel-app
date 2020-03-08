package com.example.goofin.adaptors.feedviewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goofin.store.holidayfeed.FeedItem;

public abstract class HolidayFeedViewHolder extends RecyclerView.ViewHolder {
    public HolidayFeedViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void onBind(FeedItem feedItem);
}
