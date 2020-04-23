package com.example.zomato.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.zomato.R;
import com.example.zomato.databinding.FragmentRestaurantBinding;
import com.example.zomato.db.Restaurant;
import com.example.zomato.viewModel.RestaurantViewModel;

import java.util.List;

/**
 * Created by Rajat Sangrame on 23/4/20.
 * http://github.com/rajatsangrame
 */
public class RestaurantFragment extends Fragment {

    private static final String TAG = "RestaurantFragment";
    private FragmentRestaurantBinding mBinding;

    public RestaurantFragment() {
    }

    public static RestaurantFragment newInstance(String cuisines) {
        RestaurantFragment fragment = new RestaurantFragment();
        if (cuisines == null) {
            return fragment;
        }
        Bundle args = new Bundle();
        args.putString(cuisines, cuisines);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_restaurant, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        RestaurantViewModel viewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        LiveData<List<Restaurant>> list = viewModel.getAllRestaurants("chicken", "");
        list.observe((LifecycleOwner) this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {

                Log.i(TAG, "onChanged: " + restaurants.size());
            }
        });

    }
}
