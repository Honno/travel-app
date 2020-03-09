package com.example.goofin.adaptors.feedviewholders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.goofin.R;
import com.example.goofin.store.holidayfeed.FeedItem;
import com.example.goofin.store.holidayfeed.Image;
import com.example.goofin.store.holidayfeed.Note;

public class ImageViewHolder extends HolidayFeedViewHolder {
    private ImageView imageView;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image_view);
    }

    public void onBind(FeedItem feedItem) {
        Image image = (Image) feedItem;

        Bitmap bitmap = BitmapFactory.decodeFile(image.getPath());

        imageView.setImageBitmap(bitmap);
    }
}
