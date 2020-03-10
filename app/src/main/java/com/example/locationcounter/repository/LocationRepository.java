package com.example.locationcounter.repository;

import android.location.Location;

import androidx.lifecycle.MutableLiveData;

import com.example.locationcounter.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

public class LocationRepository {
    private MutableLiveData<List<LocationModel>> mLocationsLiveData;
    private static LocationRepository sInstance;

    public static LocationRepository getInstance() {
        if (sInstance == null)
            sInstance = new LocationRepository();
        return sInstance;
    }

    private LocationRepository() {

        this.mLocationsLiveData = new MutableLiveData<>();
    }

    public void addCurrentLocation(Location location) {
        List<LocationModel> tempList = new ArrayList();
       tempList.addAll( mLocationsLiveData.getValue());
       LocationModel locationModel = new LocationModel();
       locationModel.
       tempList.add(locationModel);
       mLocationsLiveData.setValue(tempList);
    }

    public MutableLiveData<List<LocationModel>> getLocationsLiveData() {
        return mLocationsLiveData;
    }
}
