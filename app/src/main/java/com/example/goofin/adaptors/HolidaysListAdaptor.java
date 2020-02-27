package com.example.goofin.adaptors;

import android.content.Context;
import android.content.Intent;
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

    private static ClickListener clickListener;

    /*
     * "RecyclerView.Adapter implementations should subclass ViewHolder and add fields for caching
     *  potentially expensive findViewById(int) results."
     * https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder.html
     */
    class HolidaysListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView nameView;

        private HolidaysListViewHolder(View itemView)  {
            super(itemView);

            nameView = itemView.findViewById(R.id.text_view);
            // TODO will need to caching image stuff too
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(holidays, getAdapterPosition(), v);
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

    public void setOnItemClickListener(ClickListener clickListener) {
        HolidaysListAdaptor.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(List<Holiday> holidays, int position, View v);
    }
}
