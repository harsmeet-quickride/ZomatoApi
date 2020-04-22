package com.example.zomato.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AllReviews{

	@SerializedName("reviews")
	private List<ReviewsItem> reviews;

	public void setReviews(List<ReviewsItem> reviews){
		this.reviews = reviews;
	}

	public List<ReviewsItem> getReviews(){
		return reviews;
	}

	@Override
 	public String toString(){
		return 
			"AllReviews{" + 
			"reviews = '" + reviews + '\'' + 
			"}";
		}
}