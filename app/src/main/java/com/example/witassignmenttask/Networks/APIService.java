package com.example.witassignmenttask.Networks;

import com.example.witassignmenttask.Models.Banner;
import com.example.witassignmenttask.Models.DataHome;
import com.example.witassignmenttask.Models.DataCategory;
import com.example.witassignmenttask.Models.DataProduct;
import com.example.witassignmenttask.Models.Home;

import java.util.ArrayList;

import retrofit2.Call;
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
    Call<ArrayList<DataProduct>> getProduct();

}
