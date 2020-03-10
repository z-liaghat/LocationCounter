package com.example.locationcounter.view_model;

import android.app.Activity;
import android.app.Application;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.locationcounter.model.LocationModel;
import com.example.locationcounter.repository.LocationRepository;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class LocationViewModel extends AndroidViewModel {

    private static final String TAG = "LocationViewModel";
    private LocationRepository mRepository;
    private MutableLiveData<List<LocationModel>> mLocationsLiveData;

    //location
    /**
     * Stores parameters for requests to the FusedLocationProviderApi.
     */
    private LocationRequest mLocationRequest;
    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 2 * 60 * 1000;
    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS;
    /**
     * Callback for Location events.
     */
    private LocationCallback mLocationCallback;


    public LocationViewModel(@NonNull Application application) {
        super(application);
        mRepository = LocationRepository.getInstance();
        mLocationsLiveData = mRepository.getLocationsLiveData();
    }

    public void addCurrentLocation(GoogleApiClient client, final AppCompatActivity activity) {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            mRepository.addCurrentLocation(location);
                        } else {
                            Log.d(TAG, " location is null ");
                        }
                    }
                });
//        LocationRequest request = LocationRequest.create();
//        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        request.setInterval(0);
//        request.setNumUpdates(1);
//        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, location -> {
//            Log.i(TAG, "Got a location: " + location);
//            mRepository.addCurrentLocation(location);
//
//        });
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    /**
     * Creates a callback for receiving location events.
     */
    private void createLocationCallback() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Location currentLocation = locationResult.getLastLocation();
                String lastUpdateTime = DateFormat.getTimeInstance().format(new Date());
                m
                updateLocationUI();
            }
        };
    }


    private void getLastLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Location location = task.getResult();

                            Log.d(TAG, " location is: " + location.getLatitude()
                                    + "long" + location.getLongitude());
                        } else {
                            Log.d(TAG, " exception is: " + task.getException());

                        }
                    }
                });
    }


    public GoogleApiClient getLocationClient(Activity activity) {
        return new GoogleApiClient.Builder(activity)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        //callback
                        Log.d(TAG, "onConnected: ");
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Log.d(TAG, "onConnectionSuspended: ");
                    }
                })
                .build();

    }

    public MutableLiveData<List<Location>> getLocationsLiveData() {
        return mLocationsLiveData;
    }
}
