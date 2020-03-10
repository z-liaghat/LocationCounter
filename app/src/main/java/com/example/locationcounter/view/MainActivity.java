package com.example.locationcounter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.locationcounter.R;

public class MainActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return LocationListFragment.newInstance();
    }


}
