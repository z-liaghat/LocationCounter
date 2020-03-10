package com.example.locationcounter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FailToGetPermissionActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return FailToGetPermissionFragment.newInstance();
    }

    public static Intent newIntent(Context context){
        return new Intent(context ,FailToGetPermissionActivity.class );
    }


}
