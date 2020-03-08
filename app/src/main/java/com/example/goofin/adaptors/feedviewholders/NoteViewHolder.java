package com.example.goofin.adaptors.feedviewholders;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.goofin.R;
import com.example.goofin.store.holidayfeed.FeedItem;
import com.example.goofin.store.holidayfeed.Note;

public class NoteViewHolder extends HolidayFeedViewHolder {
    private TextView textView;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.text_view);
    }

    public void onBind(FeedItem feedItem) {
        Note note = (Note) feedItem;
        Log.d("heh", note.getContents());
        textView.setText(note.getContents());
    }
}
