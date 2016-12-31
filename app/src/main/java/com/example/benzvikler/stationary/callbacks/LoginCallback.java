package com.example.benzvikler.stationary.callbacks;

import android.util.Log;
import android.widget.Toast;

import com.example.benzvikler.stationary.TripListActivity;

import java.lang.ref.WeakReference;

import io.moj.java.sdk.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Mojio on 2016-12-30.
 */

public class LoginCallback implements Callback<User> {
    private WeakReference<TripListActivity> parentRef;

    public LoginCallback(TripListActivity parent) {
        this.parentRef = new WeakReference<>(parent);
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        TripListActivity parent = parentRef.get();

        if (response.isSuccessful()) {
            User user = response.body();
            // TripListActivity.getAuthenticator().setUser(user);
            parent.handleGetTrips();
            System.out.println("User Successfully Logged In: " + user.toString());
            // TODO: add support to dynamically update recycler view using trip observers
            // registerTripObserver();
        }
        else {
            Toast.makeText(parentRef.get().getBaseContext(), "Login Response Error", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Login Unsuccessful: " + response.errorBody().toString());
        }

    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Toast.makeText(parentRef.get().getBaseContext(), "Login Failed", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Error logging in", t);
    }
}
