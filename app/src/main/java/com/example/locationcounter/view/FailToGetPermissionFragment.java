package com.example.locationcounter.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.locationcounter.R;
import com.example.locationcounter.databinding.FragmentFailToGetPermissionBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class FailToGetPermissionFragment extends Fragment {

    private FragmentFailToGetPermissionBinding mBinding;

    public FailToGetPermissionFragment() {
        // Required empty public constructor
    }

    public static FailToGetPermissionFragment newInstance() {

        Bundle args = new Bundle();

        FailToGetPermissionFragment fragment = new FailToGetPermissionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_fail_to_get_permission, container, false);
        return mBinding.getRoot();
    }

}
