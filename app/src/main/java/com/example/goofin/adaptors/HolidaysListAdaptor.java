package com.example.goofin.adaptors;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.goofin.R;
import com.example.goofin.store.Holiday;
import com.example.goofin.store.holidayfeed.Image;
import com.example.goofin.utils.Rendering;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;

public class HolidaysListAdaptor extends RecyclerView.Adapter<HolidaysListAdaptor.HolidaysListViewHolder> {
    /* Implement onClick methods in adaptor owner */

    private ClickListener clickListener;

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(List<Holiday> holidays, int position, View view);
    }


    /*
     * "RecyclerView.Adapter implementations should subclass ViewHolder and add fields for caching
     *  potentially expensive findViewById(int) results."
     * https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder.html
     */
    class HolidaysListViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final ImageView imageView;

        HolidaysListViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> clickListener.onClick(holidays, getAdapterPosition(), v));

            nameView = itemView.findViewById(R.id.text_view);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

    private final Context context;
    private final LayoutInflater layoutInflater;
    private List<Holiday> holidays;
    private List<Image> thumbnails;

    public HolidaysListAdaptor(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public HolidaysListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recycler_holiday_card_view, parent, false);
        return new HolidaysListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HolidaysListViewHolder viewHolder, int position) {
        if (holidays != null) {
            Holiday holiday = holidays.get(position);

            viewHolder.nameView.setText(holiday.getName());

            /* Image stuff */
            Long imageId = holiday.getImageId();
            if (imageId != null && thumbnails != null) {
                try {
                    Uri uri = thumbnails.stream()
                            .filter(image -> imageId.equals(image.getItemId())) // TODO equals may be bad?
                            .findFirst().get()
                            .getUri();

                    viewHolder.imageView.setImageBitmap(Rendering.getBitmap(context, uri));
                } catch (NoSuchElementException e) {

                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (holidays != null)
            return holidays.size();
        else return 0;
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
        notifyDataSetChanged();
    }

    public void setThumbnails(List<Image> thumbnails) {
        this.thumbnails = thumbnails;
        notifyDataSetChanged();
    }

}
