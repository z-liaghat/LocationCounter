package com.example.locationcounter.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.adapters.Converters;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {LocationModel.class}, version = 1)
public abstract class LocationDatabase extends RoomDatabase {

    private static LocationDatabase INSTANCE;


    static LocationDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            LocationDatabase.class, "location_database")
                         //   .addCallback(sOnOpenCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract LocationDao locationDao();
}