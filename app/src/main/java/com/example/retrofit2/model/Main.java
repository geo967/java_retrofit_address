package com.example.retrofit2.model;

import com.google.gson.annotations.SerializedName;

public class Main{

	@SerializedName("temp")
	private double temp;

	@SerializedName("dew_point")
	private double dewPoint;

	@SerializedName("grnd_pressure")
	private double grndPressure;

	@SerializedName("humidity")
	private double humidity;

	@SerializedName("pressure")
	private double pressure;

	public double getTemp(){
		return temp;
	}

	public double getDewPoint(){
		return dewPoint;
	}

	public double getGrndPressure(){
		return grndPressure;
	}

	public double getHumidity(){
		return humidity;
	}

	public double getPressure(){
		return pressure;
	}
}