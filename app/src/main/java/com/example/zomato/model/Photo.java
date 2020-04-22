package com.example.zomato.model;

import com.google.gson.annotations.SerializedName;

public class Photo{

	@SerializedName("thumb_url")
	private String thumbUrl;

	@SerializedName("friendly_time")
	private String friendlyTime;

	@SerializedName("res_id")
	private int resId;

	@SerializedName("width")
	private int width;

	@SerializedName("caption")
	private String caption;

	@SerializedName("id")
	private String id;

	@SerializedName("user")
	private User user;

	@SerializedName("url")
	private String url;

	@SerializedName("timestamp")
	private int timestamp;

	@SerializedName("height")
	private int height;

	public void setThumbUrl(String thumbUrl){
		this.thumbUrl = thumbUrl;
	}

	public String getThumbUrl(){
		return thumbUrl;
	}

	public void setFriendlyTime(String friendlyTime){
		this.friendlyTime = friendlyTime;
	}

	public String getFriendlyTime(){
		return friendlyTime;
	}

	public void setResId(int resId){
		this.resId = resId;
	}

	public int getResId(){
		return resId;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setCaption(String caption){
		this.caption = caption;
	}

	public String getCaption(){
		return caption;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setTimestamp(int timestamp){
		this.timestamp = timestamp;
	}

	public int getTimestamp(){
		return timestamp;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"Photo{" + 
			"thumb_url = '" + thumbUrl + '\'' + 
			",friendly_time = '" + friendlyTime + '\'' + 
			",res_id = '" + resId + '\'' + 
			",width = '" + width + '\'' + 
			",caption = '" + caption + '\'' + 
			",id = '" + id + '\'' + 
			",user = '" + user + '\'' + 
			",url = '" + url + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}