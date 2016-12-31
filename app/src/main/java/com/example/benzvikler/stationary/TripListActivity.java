package com.example.benzvikler.stationary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.benzvikler.stationary.callbacks.LoginCallback;
import com.example.benzvikler.stationary.callbacks.TripsListCallback;
import com.example.benzvikler.stationary.model.TripListItem;

import java.util.ArrayList;
import java.util.List;

import io.moj.java.sdk.MojioClient;
import io.moj.java.sdk.model.Trip;
import io.moj.java.sdk.model.response.ListResponse;


public class TripListActivity extends AppCompatActivity {
    private MojioClient mojioClient;
    private List<TripListItem> tripListItems;
    private RecyclerView mRecyclerView;
    private MyReclyclerViewAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        tripListItems = new ArrayList<>();

        // Handle Mojio Login and get REST client
        mojioClient = ((App) getApplicationContext()).getMojioClient();
        mojioClient.login("stationaryTestUser", "Optimus123").enqueue(new LoginCallback(this));
    }

    public void handleGetTrips() {
        mojioClient.rest().getTrips().enqueue(new TripsListCallback(this));
        System.out.println("In handleGeTrips");
    }

    public void handleDisplayTrips(List<Trip> trips) {
        for (int i = 0; i < trips.size(); i++) {
            TripListItem tripListItem = new TripListItem();
            tripListItem.setDate(trips.get(i).getEndTimestamp());
            tripListItem.setStartAddress(trips.get(i).getStartLocation().getAddress().getCity());
            tripListItem.setEndAddress(trips.get(i).getEndLocation().getAddress().getCity());
            tripListItems.add(tripListItem);
        }
        progressBar.setVisibility(View.GONE);
        adapter = new MyReclyclerViewAdapter(TripListActivity.this, tripListItems);
        mRecyclerView.setAdapter(adapter);
    }
}
