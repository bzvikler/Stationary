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
    private OnItemClickListener onItemClickListener;

    public MyReclyclerViewAdapter(Context context, List<TripListItem> tripListItems) {
        this.mContext = context;
        this.tripListItems = tripListItems;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row,null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final TripListItem tripListItem= tripListItems.get(i);

        //Setting text view title
        customViewHolder.textView.setText(tripListItem.getDate());
        customViewHolder.startLocationTextView.setText(tripListItem.getStartAddress());
        customViewHolder.endLocationTextView.setText(tripListItem.getEndAddress());

        // Setting onClick Listener to appropriate views
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onItemClickListener.onItemClick(tripListItem);
            }
        };
        customViewHolder.view.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return (null != tripListItems ? tripListItems.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;
        protected TextView startLocationTextView;
        protected TextView endLocationTextView;
        protected View view;


        public CustomViewHolder(View view) {
            super(view);
            this.view = view;
            this.textView = (TextView) view.findViewById(R.id.title);
            this.startLocationTextView = (TextView) view.findViewById(R.id.startAddress);
            this.endLocationTextView = (TextView) view.findViewById(R.id.endAddress);
        }

    }
}
