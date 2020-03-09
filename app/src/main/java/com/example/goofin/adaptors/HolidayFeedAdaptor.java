package com.example.goofin.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goofin.R;
import com.example.goofin.adaptors.feedviewholders.HolidayFeedViewHolder;
import com.example.goofin.adaptors.feedviewholders.ImageViewHolder;
import com.example.goofin.adaptors.feedviewholders.NoteViewHolder;
import com.example.goofin.store.holidayfeed.FeedItem;

import java.util.List;

/**
 * Method for heterogeneous recyclerview comes from yqritc:
 * https://stackoverflow.com/a/29394173/5193926
 */
public class HolidayFeedAdaptor extends RecyclerView.Adapter<HolidayFeedViewHolder> {

    /* Implement onClick methods in adaptor owner */

    private static ClickListener clickListener;

    public void setOnItemClickListener(ClickListener clickListener) {
        clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(List<FeedItem> feed, int position, View view);
    }

    private final LayoutInflater layoutInflater;

    private List<FeedItem> feed;

    public HolidayFeedAdaptor(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public HolidayFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {

            case R.layout.recycler_note_view:
                view = layoutInflater.inflate(R.layout.recycler_note_view, parent, false);
                return new NoteViewHolder(view);

            case R.layout.recycler_image_view:
                view = layoutInflater.inflate(R.layout.recycler_image_view, parent, false);
                return new ImageViewHolder(view);

            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public int getItemViewType(int position) {
        FeedItem.TYPES type = feed.get(position).getItemType();

        switch (type) {
            case NOTE:
                return R.layout.recycler_note_view;
            case IMAGE:
                return R.layout.recycler_image_view;
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayFeedViewHolder holder, int position) {
        FeedItem holidayItem = feed.get(position);

        holder.onBind(holidayItem); // TODO uh is this enough
    }

    @Override
    public int getItemCount() {
        if (feed != null)
            return feed.size();
        return 0;
    }

    public void setFeed(List<FeedItem> feed) {
        this.feed = feed;
        notifyDataSetChanged();
    }
}

