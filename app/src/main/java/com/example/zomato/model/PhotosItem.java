package com.example.zomato.model;

import com.google.gson.annotations.SerializedName;

public class PhotosItem{

	@SerializedName("photo")
	private Photo photo;

	public void setPhoto(Photo photo){
		this.photo = photo;
	}

	public Photo getPhoto(){
		return photo;
	}

	@Override
 	public String toString(){
		return 
			"PhotosItem{" + 
			"photo = '" + photo + '\'' + 
			"}";
		}
}