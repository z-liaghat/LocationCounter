package com.example.locationcounter.view.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locationcounter.databinding.ListItemLocationBinding;
import com.example.locationcounter.model.LocationModel;

class LocationViewHolder extends RecyclerView.ViewHolder {
    private LocationModel mLocationModel;
    private ListItemLocationBinding mBinding;
    private Context mContext;

    public LocationViewHolder(@NonNull ListItemLocationBinding binding , Context context) {
        super(binding.getRoot());
        mBinding = binding;
        mContext = context;
    }

    public void bind(LocationModel locationModel) {
        mLocationModel = locationModel;
        mBinding.textViewLat.setText(mLocationModel.getLatitude());
        mBinding.textViewLon.setText(mLocationModel.getLongitude());
    }
}
