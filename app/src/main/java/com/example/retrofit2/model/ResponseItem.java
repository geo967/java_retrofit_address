package com.example.retrofit2.model;

import com.google.gson.annotations.SerializedName;

public class ResponseItem{

	@SerializedName("dt")
	private int dt;

	@SerializedName("precipitation")
	private Object precipitation;

	@SerializedName("main")
	private Main main;

	@SerializedName("clouds")
	private Clouds clouds;

	@SerializedName("wind")
	private Wind wind;

	public int getDt(){
		return dt;
	}

	public Object getPrecipitation(){
		return precipitation;
	}

	public Main getMain(){
		return main;
	}

	public Clouds getClouds(){
		return clouds;
	}

	public Wind getWind(){
		return wind;
	}
}