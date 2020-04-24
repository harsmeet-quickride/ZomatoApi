package com.example.zomato.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.example.zomato.viewModel.SavedRestaurantViewModel;

import java.util.List;

public class SavedFragment extends Fragment implements RestaurantAdapter.RestaurantAdapterListener {
    private static final String TAG = "SavedFragment";
    private FragmentRestaurantBinding mBinding;

    public SavedFragment() {
    }

    public static SavedFragment newInstance() {
        return new SavedFragment();
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

        SavedRestaurantViewModel viewModel = new ViewModelProvider(this).get(SavedRestaurantViewModel.class);
        mBinding.rvRestaurant.setLayoutManager(new LinearLayoutManager(getContext()));

        LiveData<List<Restaurant>> list = viewModel.getSavedRestaurants();
        final RestaurantAdapter adapter = new RestaurantAdapter();
        adapter.setListener(this);
        adapter.setIsSavedFragment(true);
        mBinding.rvRestaurant.setAdapter(adapter);
        list.observe((LifecycleOwner) this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {

                adapter.setRestaurants(restaurants);
                adapter.notifyDataSetChanged();

                Log.i(TAG, "onChanged: " + restaurants.size());
            }
        });
    }

    @Override
    public void onItemClicked(Restaurant restaurant) {

    }

    @Override
    public void onItemOptionsClicked(Restaurant restaurant) {
        RestaurantRepository db = RestaurantRepository.getInstance(getContext());

        if (restaurant.isSaved()) {
            db.updateSave(restaurant.getId(), false);
            Toast.makeText(getContext(), "Removed from Saved", Toast.LENGTH_SHORT).show();
        } else {
            db.updateSave(restaurant.getId(), true);
        }

        Log.i(TAG, "onItemOptionsClicked: ");
    }
}
