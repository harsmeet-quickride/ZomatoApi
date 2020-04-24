package com.example.zomato.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void bulkInsert(List<Restaurant> restaurants);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Restaurant restaurant);

    @Query("SELECT * from restaurant ORDER BY timestamp DESC")
    LiveData<List<Restaurant>> getAllRestaurants();

    @Query("SELECT * from restaurant WHERE cuisines LIKE '%' || :cuisine || '%'")
    LiveData<List<Restaurant>> getRestaurants(String cuisine);

    @Query("SELECT * from restaurant WHERE id=:id")
    Restaurant getRestaurant(String id);

    @Query("SELECT * from restaurant WHERE is_saved=1 ORDER BY timestamp DESC")
    LiveData<List<Restaurant>> getSavedRestaurants();

    @Query("UPDATE restaurant SET timestamp = :timeStamp, is_saved = :isSaved WHERE id = :id")
    int updateSave(String id, boolean isSaved, long timeStamp);
}
