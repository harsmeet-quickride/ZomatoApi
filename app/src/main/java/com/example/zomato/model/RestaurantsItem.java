package com.example.zomato.model;

import com.google.gson.annotations.SerializedName;

public class RestaurantsItem{

	@SerializedName("restaurant")
	private Restaurant restaurant;

	public void setRestaurant(Restaurant restaurant){
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant(){
		return restaurant;
	}

	@Override
 	public String toString(){
		return 
			"RestaurantsItem{" + 
			"restaurant = '" + restaurant + '\'' + 
			"}";
		}
}