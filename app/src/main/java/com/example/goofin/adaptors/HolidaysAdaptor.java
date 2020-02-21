package com.example.goofin.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.goofin.R;
import com.example.goofin.store.Holiday;

import java.util.List;

public class HolidaysAdaptor extends RecyclerView.Adapter<HolidaysAdaptor.HolidaysListViewHolder> {

    /*
     * "RecyclerView.Adapter implementations should subclass ViewHolder and add fields for caching
     *  potentially expensive findViewById(int) results."
     * https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder.html
     */
    class HolidaysListViewHolder extends RecyclerView.ViewHolder {
        private final TextView holidaySummaryView; // TODO Needs to be something more appropriate

        private HolidaysListViewHolder(View itemView) {
            super(itemView);
            holidaySummaryView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater layoutInflater;
    private List<Holiday> holidays;

    public HolidaysAdaptor(Context context) { layoutInflater = LayoutInflater.from(context); }

    @Override
    public HolidaysListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new HolidaysListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HolidaysListViewHolder viewHolder, int position) {
        if (holidays != null) {
            Holiday currentHoliday = holidays.get(position);
            viewHolder.holidaySummaryView.setText(currentHoliday.getName());
        } else {
            viewHolder.holidaySummaryView.setText("No Name");
        }
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (holidays != null)
            return holidays.size();
        else return 0;
    }
}
