package com.example.retrofit2;

import com.example.retrofit2.model.ResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Myapi {
/*    @GET("posts")
    Call<List<Model>> getModels();

    @GET("/photos")
    Call<List<Model2>> getModel2();*/

    @GET("dc283e42-94ee-4363-972e-a42f37a83144")
    Call<List<ResponseItem>> getResponseItem();
}
