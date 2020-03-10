package com.example.locationcounter.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert
    void insert(LocationModel locationModel);

    @Query("SELECT * FROM location_table WHERE save_state = -1")
    List<LocationModel> findNotSavedLocation(int saveState);
}
