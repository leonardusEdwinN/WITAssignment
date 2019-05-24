package com.example.witassignmenttask.networks;

import com.example.witassignmenttask.models.Banner;
import com.example.witassignmenttask.models.DataCategory;
import com.example.witassignmenttask.models.DataProduct;
import com.example.witassignmenttask.models.Home;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @GET("post/banner")
    Call<Banner> getBanner();

    @GET("post/homeblock")
    Call<Home> getHomeBlock();

    @POST("category/get")
    Call<ArrayList<DataCategory>> getCategory();

    @POST("product/get")
    Call<ArrayList<DataProduct>> getProduct(@Body RequestBody params);

    @POST("product/search")
    Call<ArrayList<DataProduct>> getSearchProduct(@Body RequestBody params);

}
