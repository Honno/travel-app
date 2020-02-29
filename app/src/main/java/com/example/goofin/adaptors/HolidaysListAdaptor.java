package com.example.goofin.adaptors;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goofin.R;
import com.example.goofin.activities.HolidayActivity;
import com.example.goofin.store.Holiday;

import java.util.List;

public class HolidaysListAdaptor extends RecyclerView.Adapter<HolidaysListAdaptor.HolidaysListViewHolder> {

    /* Implement onClick methods in adaptor owner */

    private static ClickListener clickListener;

    public void setOnItemClickListener(ClickListener clickListener) {
        HolidaysListAdaptor.clickListener = clickListener;
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

        public HolidaysListViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> clickListener.onClick(holidays, getAdapterPosition(), v));

            nameView = itemView.findViewById(R.id.text_view);
            // TODO will need to caching image stuff too

        }
    }

    private final LayoutInflater layoutInflater;
    private List<Holiday> holidays;

    public HolidaysListAdaptor(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public HolidaysListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new HolidaysListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HolidaysListViewHolder viewHolder, int position) {
        if (holidays != null) {
            Holiday currentHoliday = holidays.get(position);
            viewHolder.nameView.setText(currentHoliday.getName());
        } else {
            viewHolder.nameView.setText("No Name"); // TODO throw error instead?
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
}
