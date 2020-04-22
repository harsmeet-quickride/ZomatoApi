package com.example.zomato.model;

import com.google.gson.annotations.SerializedName;

public class UserRating{

	@SerializedName("aggregate_rating")
	private int aggregateRating;

	@SerializedName("rating_color")
	private String ratingColor;

	@SerializedName("rating_obj")
	private RatingObj ratingObj;

	@SerializedName("rating_text")
	private String ratingText;

	@SerializedName("votes")
	private int votes;

	@SerializedName("rating_tool_tip")
	private String ratingToolTip;

	@SerializedName("custom_rating_text_background")
	private String customRatingTextBackground;

	@SerializedName("custom_rating_text")
	private String customRatingText;

	public void setAggregateRating(int aggregateRating){
		this.aggregateRating = aggregateRating;
	}

	public int getAggregateRating(){
		return aggregateRating;
	}

	public void setRatingColor(String ratingColor){
		this.ratingColor = ratingColor;
	}

	public String getRatingColor(){
		return ratingColor;
	}

	public void setRatingObj(RatingObj ratingObj){
		this.ratingObj = ratingObj;
	}

	public RatingObj getRatingObj(){
		return ratingObj;
	}

	public void setRatingText(String ratingText){
		this.ratingText = ratingText;
	}

	public String getRatingText(){
		return ratingText;
	}

	public void setVotes(int votes){
		this.votes = votes;
	}

	public int getVotes(){
		return votes;
	}

	public void setRatingToolTip(String ratingToolTip){
		this.ratingToolTip = ratingToolTip;
	}

	public String getRatingToolTip(){
		return ratingToolTip;
	}

	public void setCustomRatingTextBackground(String customRatingTextBackground){
		this.customRatingTextBackground = customRatingTextBackground;
	}

	public String getCustomRatingTextBackground(){
		return customRatingTextBackground;
	}

	public void setCustomRatingText(String customRatingText){
		this.customRatingText = customRatingText;
	}

	public String getCustomRatingText(){
		return customRatingText;
	}

	@Override
 	public String toString(){
		return 
			"UserRating{" + 
			"aggregate_rating = '" + aggregateRating + '\'' + 
			",rating_color = '" + ratingColor + '\'' + 
			",rating_obj = '" + ratingObj + '\'' + 
			",rating_text = '" + ratingText + '\'' + 
			",votes = '" + votes + '\'' + 
			",rating_tool_tip = '" + ratingToolTip + '\'' + 
			",custom_rating_text_background = '" + customRatingTextBackground + '\'' + 
			",custom_rating_text = '" + customRatingText + '\'' + 
			"}";
		}
}