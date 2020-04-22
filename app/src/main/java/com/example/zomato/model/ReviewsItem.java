package com.example.zomato.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ReviewsItem{

	@SerializedName("review")
	private List<Object> review;

	public void setReview(List<Object> review){
		this.review = review;
	}

	public List<Object> getReview(){
		return review;
	}

	@Override
 	public String toString(){
		return 
			"ReviewsItem{" + 
			"review = '" + review + '\'' + 
			"}";
		}
}