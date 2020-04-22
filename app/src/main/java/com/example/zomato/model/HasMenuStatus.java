package com.example.zomato.model;

import com.google.gson.annotations.SerializedName;

public class HasMenuStatus{

	@SerializedName("delivery")
	private int delivery;

	@SerializedName("takeaway")
	private int takeaway;

	public void setDelivery(int delivery){
		this.delivery = delivery;
	}

	public int getDelivery(){
		return delivery;
	}

	public void setTakeaway(int takeaway){
		this.takeaway = takeaway;
	}

	public int getTakeaway(){
		return takeaway;
	}

	@Override
 	public String toString(){
		return 
			"HasMenuStatus{" + 
			"delivery = '" + delivery + '\'' + 
			",takeaway = '" + takeaway + '\'' + 
			"}";
		}
}