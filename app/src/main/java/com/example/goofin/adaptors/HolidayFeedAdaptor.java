package com.example.goofin.adaptors;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goofin.R;
import com.example.goofin.store.holidayfeed.FeedItem;
import com.example.goofin.store.holidayfeed.Image;
import com.example.goofin.store.holidayfeed.Note;
import com.example.goofin.utils.Rendering;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Method for heterogeneous recyclerview comes from yqritc:
 * https://stackoverflow.com/a/29394173/5193926
 */
public class HolidayFeedAdaptor extends RecyclerView.Adapter<HolidayFeedAdaptor.HolidayFeedViewHolder> {


    /* Implement onClick methods in adaptor owner */

    private ClickListener clickListener;

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(List<FeedItem> feed, int position, View view);
        boolean onLongClick(List<FeedItem> feed, int position, View view);
    }

    /* Setup viewholders */

    public abstract class HolidayFeedViewHolder extends RecyclerView.ViewHolder {
        HolidayFeedViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> clickListener.onClick(feed, getAdapterPosition(), v));
            itemView.setOnLongClickListener(v -> clickListener.onLongClick(feed, getAdapterPosition(), v));
        }

        public abstract void onBind(FeedItem feedItem);
    }


    private class NoteViewHolder extends HolidayFeedAdaptor.HolidayFeedViewHolder {
        private TextView textView;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view);
        }

        public void onBind(FeedItem feedItem) {
            Note note = (Note) feedItem;
            textView.setText(note.getContents());
        }
    }


    private class ImageViewHolder extends HolidayFeedAdaptor.HolidayFeedViewHolder {
        private ImageView imageView;

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
        }

        public void onBind(FeedItem feedItem) {
            Image image = (Image) feedItem;

            imageView.setImageBitmap(Rendering.getBitmap(context, image.getUri()));
        }

    }

    private Context context;

    /* Setup adaptor */

    private final LayoutInflater layoutInflater;

    private List<FeedItem> feed;

    public HolidayFeedAdaptor(Context context) {
        this.context = context;
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

