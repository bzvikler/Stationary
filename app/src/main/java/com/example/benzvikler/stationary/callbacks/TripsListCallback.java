package com.example.benzvikler.stationary.callbacks;

import android.util.Log;
import android.widget.Toast;

import com.example.benzvikler.stationary.TripListActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import io.moj.java.sdk.model.Trip;
import io.moj.java.sdk.model.response.ListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Mojio on 2016-12-30.
 */

public class TripsListCallback implements Callback<ListResponse<Trip>> {
    private WeakReference<TripListActivity> parentRef;

    public TripsListCallback(TripListActivity parent) {
        this.parentRef = new WeakReference<>(parent);
    }

    @Override
    public void onResponse(Call<ListResponse<Trip>> call, Response<ListResponse<Trip>> response) {
        TripListActivity parent = parentRef.get();

        if (response.isSuccessful()) {
            ListResponse<Trip> trips= response.body();
            System.out.println("Successfully got trips: " + response.body().getData());
            parent.handleDisplayTrips(trips.getData());
        }
        else {
            Toast.makeText(parentRef.get().getBaseContext(), "Login Response Error", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Login Unsuccessful: " + response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<ListResponse<Trip>> call, Throwable t) {

    }
}
