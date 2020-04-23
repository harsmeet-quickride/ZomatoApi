package com.example.zomato.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.zomato.db.Restaurant;
import com.example.zomato.db.RestaurantRepository;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {

    private final RestaurantRepository restaurantRepository;

    public RestaurantViewModel(@NonNull Application application) {
        super(application);
        restaurantRepository = RestaurantRepository.getInstance(application);
    }

    public LiveData<List<Restaurant>> getAllRestaurants(String query, String cuisinesIds) {
        return restaurantRepository.getRestaurantList(query, cuisinesIds);
    }

//    public LiveData<List<Restaurant>> getSavedRestaurants() {
//        return restaurantRepository.getRestaurantList();
//    }

}
