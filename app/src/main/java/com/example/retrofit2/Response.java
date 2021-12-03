package com.example.retrofit2;

import java.util.List;

import com.example.retrofit2.model.ResponseItem;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("Response")
	private List<ResponseItem> response;

	public List<ResponseItem> getResponse(){
		return response;
	}
}