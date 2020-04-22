package com.example.zomato.model;

import com.google.gson.annotations.SerializedName;

public class RatingObj{

	@SerializedName("bg_color")
	private BgColor bgColor;

	@SerializedName("title")
	private Title title;

	public void setBgColor(BgColor bgColor){
		this.bgColor = bgColor;
	}

	public BgColor getBgColor(){
		return bgColor;
	}

	public void setTitle(Title title){
		this.title = title;
	}

	public Title getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"RatingObj{" + 
			"bg_color = '" + bgColor + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}