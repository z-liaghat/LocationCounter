package com.example.locationcounter.view.adapter;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locationcounter.R;
import com.example.locationcounter.databinding.ListItemLocationBinding;
import com.example.locationcounter.model.LocationModel;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter <LocationViewHolder>{

    private List<LocationModel> mLocationList;
    private Context mContext;

    public RecyclerAdapter(List<LocationModel> mLocationList , Context context) {
        this.mLocationList = mLocationList;
        mContext = context;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemLocationBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.list_item_location, parent, false);
        return new LocationViewHolder(binding , mContext);

    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.bind(mLocationList.get(position));
    }

    @Override
    public int getItemCount() {
        return mLocationList.size();
    }

    public void setItems(List<LocationModel> locations) {
        mLocationList = locations;
    }
}

