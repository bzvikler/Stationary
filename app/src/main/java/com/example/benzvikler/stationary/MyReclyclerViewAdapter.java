package com.example.benzvikler.stationary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.benzvikler.stationary.model.TripListItem;

import java.util.List;

/**
 * Created by Mojio on 2016-12-30.
 */

public class MyReclyclerViewAdapter extends RecyclerView.Adapter<MyReclyclerViewAdapter.CustomViewHolder>{
    private List<TripListItem> tripListItems;
    private Context mContext;

    public MyReclyclerViewAdapter(Context context, List<TripListItem> tripListItems) {
        this.mContext = context;
        this.tripListItems = tripListItems;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row,null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        TripListItem tripListItem= tripListItems.get(i);

        //Setting text view title
        customViewHolder.textView.setText(tripListItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return (null != tripListItems ? tripListItems.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.title);
        }

    }
}
