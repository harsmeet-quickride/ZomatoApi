package com.example.zomato.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.zomato.model.Location;
import com.example.zomato.model.RestaurantsItem;
import com.example.zomato.model.UserRating;
import com.example.zomato.utils.Helper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Rajat Sangrame on 22/4/20.
 * http://github.com/rajatsangrame
 */

@Entity(tableName = "restaurant")
public class Restaurant {

    @PrimaryKey()
    @NonNull
    private String id;

    @NonNull
    @ColumnInfo(name = "has_online_delivery")
    private int hasOnlineDelivery;

    @NonNull
    @ColumnInfo(name = "average_cost_for_two")
    private String averageCostForTwo;

    @NonNull
    @ColumnInfo(name = "highlights")
    private String highlights;

    @NonNull
    @ColumnInfo(name = "price_range")
    private int priceRange;

    @NonNull
    @ColumnInfo(name = "timings")
    private String timings;

    @NonNull
    @ColumnInfo(name = "user_rating")
    private String userRating;

    @NonNull
    @ColumnInfo(name = "is_delivering_now")
    private int isDeliveringNow;

    @NonNull
    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @NonNull
    @ColumnInfo(name = "cuisines")
    private String cuisines;

    @NonNull
    @ColumnInfo(name = "phone_numbers")
    private String phoneNumbers;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "location")
    private String location;

    @NonNull
    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @NonNull
    @ColumnInfo(name = "is_saved")
    private boolean isSaved;

    public Restaurant() {
    }

    public Restaurant(String id, int hasOnlineDelivery, String averageCostForTwo, @NonNull String highlights, int priceRange, @NonNull String timings, @NonNull UserRating userRating, int isDeliveringNow, @NonNull String url, @NonNull String cuisines, @NonNull String phoneNumbers, @NonNull String name, @NonNull String location) {
        this.id = id;
        this.hasOnlineDelivery = hasOnlineDelivery;
        this.averageCostForTwo = averageCostForTwo;
        this.highlights = highlights;
        this.priceRange = priceRange;
        this.timings = timings;
        this.userRating = userRating.getAggregateRating();
        this.isDeliveringNow = isDeliveringNow;
        this.imageUrl = url;
        this.cuisines = cuisines;
        this.phoneNumbers = phoneNumbers;
        this.name = name;
        this.location = location;
        this.timestamp = System.currentTimeMillis();
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public int getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public void setHasOnlineDelivery(int hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public String getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(String averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    @NonNull
    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(@NonNull String highlights) {
        this.highlights = highlights;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    @NonNull
    public String getTimings() {
        return timings;
    }

    public void setTimings(@NonNull String timings) {
        this.timings = timings;
    }

    @NonNull
    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(@NonNull String userRating) {
        this.userRating = userRating;
    }

    public int getIsDeliveringNow() {
        return isDeliveringNow;
    }

    public void setIsDeliveringNow(int isDeliveringNow) {
        this.isDeliveringNow = isDeliveringNow;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NonNull String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(@NonNull String cuisines) {
        this.cuisines = cuisines;
    }

    @NonNull
    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(@NonNull String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getLocation() {
        return location;
    }

    public void setLocation(@NonNull String location) {
        this.location = location;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public static List<Restaurant> getListFromResponse(List<RestaurantsItem> list) {

        List<Restaurant> restaurants = new ArrayList<>();

        for (RestaurantsItem restaurantsItem : list) {

            if (restaurantsItem.getRestaurant() != null) {

                com.example.zomato.model.Restaurant model = restaurantsItem.getRestaurant();

                Restaurant db = new Restaurant(
                        model.getId(),
                        model.getHasOnlineDelivery(),
                        Helper.getCostForTwo(model.getAverageCostForTwo()),
                        Helper.getHighlights(model.getHighlights()),
                        model.getPriceRange(),
                        model.getTimings(),
                        model.getUserRating(),
                        model.getIsDeliveringNow(),
                        model.getThumb(),
                        model.getCuisines(),
                        model.getPhoneNumbers(),
                        model.getName(),
                        new Gson().toJson(model.getLocation(), Location.class)
                );
                restaurants.add(db);
            }
        }

        return restaurants;
    }
}
