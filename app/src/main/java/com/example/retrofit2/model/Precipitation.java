package com.example.retrofit2.model;

import com.google.gson.annotations.SerializedName;

public class Precipitation{

	@SerializedName("convective")
	private int convective;

	@SerializedName("fr_rain")
	private int frRain;

	@SerializedName("rate")
	private int rate;

	@SerializedName("ice")
	private int ice;

	@SerializedName("accumulated")
	private int accumulated;

	public int getConvective(){
		return convective;
	}

	public int getFrRain(){
		return frRain;
	}

	public int getRate(){
		return rate;
	}

	public int getIce(){
		return ice;
	}

	public int getAccumulated(){
		return accumulated;
	}
}