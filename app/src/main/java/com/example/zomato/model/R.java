package com.example.zomato.model;

import com.google.gson.annotations.SerializedName;

public class R{

	@SerializedName("has_menu_status")
	private HasMenuStatus hasMenuStatus;

	@SerializedName("res_id")
	private int resId;

	public void setHasMenuStatus(HasMenuStatus hasMenuStatus){
		this.hasMenuStatus = hasMenuStatus;
	}

	public HasMenuStatus getHasMenuStatus(){
		return hasMenuStatus;
	}

	public void setResId(int resId){
		this.resId = resId;
	}

	public int getResId(){
		return resId;
	}

	@Override
 	public String toString(){
		return 
			"R{" + 
			"has_menu_status = '" + hasMenuStatus + '\'' + 
			",res_id = '" + resId + '\'' + 
			"}";
		}
}