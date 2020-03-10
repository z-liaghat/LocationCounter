package com.example.locationcounter.model;

import android.location.Location;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName =  "location_table")
public class LocationModel {
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name= "latitude")
    private String mLatitude;

    @ColumnInfo (name = "longitude")
    private String mLongitude;

    @ColumnInfo (name = "time")
    private String mTime;

    //save state = -1 means the location is not sent to Firebase
    @ColumnInfo (name = "save_state")
    private int mSaveState;
    private Location mLocation;

    public LocationModel(String latitude, String longitude, String time, int saveState) {
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mTime = time;
        this.mSaveState = saveState;
    }

    public int getId() {
        return mId;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public String getTime() {
        return mTime;
    }

    public int getSaveState() {
        return mSaveState;
    }
}
