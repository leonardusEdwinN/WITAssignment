package com.example.witassignmenttask.modules.category;


import android.util.Log;

import com.example.witassignmenttask.models.DataCategory;
import com.example.witassignmenttask.networks.APIService;
import com.example.witassignmenttask.networks.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {
    CategoryView view;

    //Presenter is used for logic to logic of the module
    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }


    //Hit API Category to get DATA
    public void getCategoryData(){
        APIService apiService = new RetrofitClient().getApiService();

        Call call = apiService.getCategory();
        call.enqueue(new Callback<ArrayList<DataCategory>>() {
            @Override
            public void onResponse(Call<ArrayList<DataCategory>> call, Response<ArrayList<DataCategory>> response) {
                if(response.isSuccessful()) {
                    Log.e("done","get data SUCCESS");
                    view.loadGridCategory(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DataCategory>> call, Throwable t) {
                Log.e("errorCategory","Failure to get data");
            }

        });
    }
}
