package com.example.locationcounter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LocationListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return LocationListFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static Intent newIntent(Context context){
        return new Intent (context , LocationListActivity.class);
    }
}
