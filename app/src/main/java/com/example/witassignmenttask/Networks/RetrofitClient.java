package com.example.witassignmenttask.Networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    String BASE_URL=" http://api.witcomdev.wit.co.id/api/";

    private Retrofit getRetrofitInstance(){

        Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(BASE_URL)
                                            .addConverterFactory(GsonConverterFactory
                                            .create()).build();

        return  retrofit;
    }

    public APIService getApiService() {
        return getRetrofitInstance().create(APIService.class);
    }
}
