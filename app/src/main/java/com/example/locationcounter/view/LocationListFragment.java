package com.example.locationcounter.view;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.locationcounter.R;
import com.example.locationcounter.databinding.FragmentLocationListBinding;
import com.example.locationcounter.model.LocationModel;
import com.example.locationcounter.repository.LocationRepository;
import com.example.locationcounter.view.adapter.RecyclerAdapter;
import com.example.locationcounter.view_model.LocationViewModel;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationListFragment extends Fragment {

    private static final int REQUEST_CODE_LOCATION_PERMISSIONS = 0;
    private FragmentLocationListBinding mBinding;
    private GoogleApiClient mClient;
    private LocationViewModel mLocationViewModel;
    private static final String[] LOCATION_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    private RecyclerAdapter mRecyclerAdapter ;
    private LocationRepository mRepository = LocationRepository.getInstance();

    private FusedLocationProviderClient mFusedLocationClient;
    public LocationListFragment() {
        // Required empty public constructor
    }

    public static LocationListFragment newInstance() {

        Bundle args = new Bundle();

        LocationListFragment fragment = new LocationListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationViewModel = ViewModelProviders.of(this).get(LocationViewModel.class);
        mClient = mLocationViewModel.getLocationClient(getActivity());
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        if (hasLocationPermission()) {
            Toast.makeText(getContext(),"has permission" , Toast.LENGTH_LONG).show();
        } else {
            requestPermissions(LOCATION_PERMISSIONS, REQUEST_CODE_LOCATION_PERMISSIONS);
        }

        mLocationViewModel.getLocationsLiveData().observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                setLocationAdapter(locations);
            }
        });

    }

    private void setLocationAdapter(List<LocationModel> locations) {
        if(mRecyclerAdapter == null){
            mRecyclerAdapter = new RecyclerAdapter(locations , getContext());
            mBinding.recyclerViewLocations.setAdapter(mRecyclerAdapter);
        }
        mRecyclerAdapter.setItems(locations);
        mRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_location_list, container, false);
        mBinding.recyclerViewLocations.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastLocation();
//
//                if(mClient.isConnected()) {
//                    LocationRequest request = LocationRequest.create();
//                    request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//                    request.setInterval(0);
//                    request.setNumUpdates(1);
//                    LocationServices.FusedLocationApi.requestLocationUpdates(mLocationViewModel.getLocationClient(getActivity()), request, location -> {
//                        Toast.makeText(getContext(), " location is " + location.getLatitude()
//                                + "long" + location.getLongitude(), Toast.LENGTH_LONG).show();
//                        mRepository.addCurrentLocation(location);
//
//                    });
//                }
//                else{
//                    mClient.connect();
//                    Toast.makeText(getContext(), " try to connect again" , Toast.LENGTH_LONG).show();
//                }


            }
        });
        return mBinding.getRoot();
    }
    private void getLastLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Location location = task.getResult();

                            Toast.makeText(getContext()," location is "+ location.getLatitude()
                                            +"long"+ location.getLongitude(), Toast.LENGTH_LONG).show();
                                }
                         else {
                            Toast.makeText(getContext()," location is null "+task.getException()
                                            , Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mClient.disconnect();
    }

    private boolean hasLocationPermission() {
        int result = ContextCompat
                .checkSelfPermission(getActivity(), LOCATION_PERMISSIONS[0]);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       if(requestCode == REQUEST_CODE_LOCATION_PERMISSIONS){


       }
       else{
           startActivity(FailToGetPermissionActivity.newIntent(getContext()));
       }
    }
}
