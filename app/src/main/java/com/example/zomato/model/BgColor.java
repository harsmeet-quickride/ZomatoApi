package com.example.zomato.model;

import com.google.gson.annotations.SerializedName;

public class BgColor{

	@SerializedName("type")
	private String type;

	@SerializedName("tint")
	private String tint;

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setTint(String tint){
		this.tint = tint;
	}

	public String getTint(){
		return tint;
	}

	@Override
 	public String toString(){
		return 
			"BgColor{" + 
			"type = '" + type + '\'' + 
			",tint = '" + tint + '\'' + 
			"}";
		}
}