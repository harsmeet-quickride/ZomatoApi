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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zomato.R;
import com.example.zomato.adapter.RestaurantAdapter;
import com.example.zomato.databinding.FragmentRestaurantBinding;
import com.example.zomato.db.Restaurant;
import com.example.zomato.db.RestaurantRepository;
import com.example.zomato.viewModel.RestaurantViewModel;

import java.util.List;

/**
 * Created by Rajat Sangrame on 23/4/20.
 * http://github.com/rajatsangrame
 */
public class RestaurantFragment extends Fragment implements RestaurantAdapter.RestaurantAdapterListener {

    private static final String TAG = "RestaurantFragment";
    private FragmentRestaurantBinding mBinding;
    private String CUISINE = "";
    private static final String PARAM = "CUISINE";
    private RestaurantAdapter mAdapter;

    public RestaurantFragment() {
    }

    public static RestaurantFragment newInstance(String cuisines) {
        RestaurantFragment fragment = new RestaurantFragment();
        if (cuisines == null) {
            return fragment;
        }
        Bundle args = new Bundle();
        args.putString(PARAM, cuisines);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            CUISINE = getArguments().getString(PARAM);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RestaurantViewModel viewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
        mBinding.rvRestaurant.setLayoutManager(new LinearLayoutManager(getContext()));

        LiveData<List<Restaurant>> list = viewModel.getAllRestaurants("", CUISINE);
        mAdapter = new RestaurantAdapter();
        mAdapter.setListener(this);
        mBinding.rvRestaurant.setAdapter(mAdapter);
        list.observe((LifecycleOwner) this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {

                mAdapter.setRestaurants(restaurants);
                mAdapter.notifyDataSetChanged();

                Log.i(TAG, "onChanged: " + restaurants.size());
            }
        });

    }

    @Override
    public void onItemClicked(Restaurant restaurant) {

        Log.i(TAG, "onItemClicked: Item");
    }

    @Override
    public void onItemOptionsClicked(Restaurant restaurant, int pos) {
        RestaurantRepository db = RestaurantRepository.getInstance(getContext());

        if (restaurant.isSaved()) {
            db.updateSave(restaurant.getId(), false);
        } else {
            db.updateSave(restaurant.getId(), true);
        }
        mBinding.rvRestaurant.scrollToPosition(pos);
        Log.i(TAG, "onItemOptionsClicked: ");

    }
}
