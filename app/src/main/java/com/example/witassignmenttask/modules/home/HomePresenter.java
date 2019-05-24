package com.example.witassignmenttask.modules.home;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.witassignmenttask.models.Banner;
import com.example.witassignmenttask.models.Home;
import com.example.witassignmenttask.networks.APIService;
import com.example.witassignmenttask.networks.RetrofitClient;

public class HomePresenter {

    HomeView view;

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    public void getHomeBlockData() {
        APIService apiService = new RetrofitClient().getApiService();

        Call call = apiService.getHomeBlock();
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                if(response.isSuccessful()&& response.body() != null) {

                    view.loadGridHome(response.body().getData());
                    Log.e("doneHome","SUCCESS Get DataHome Home");
                }
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Log.e("errorHome","Failure Get DataHome Home");
            }



        });
    }

    public void getBanner() {
        APIService apiService = new RetrofitClient().getApiService();

        Call call = apiService.getBanner();
        call.enqueue(new Callback<Banner>() {
            @Override
            public void onResponse(Call<Banner> call, Response<Banner> response) {
                if(response.isSuccessful() && response.body() != null) {

                    view.loadBanner(response.body().getData());
                    Log.e("doneBanner","SUCCESS Get DataHome Banner");
                }
            }



            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("errorBanner","Failure Get DataHome Banner");
            }
        });
    }

}
