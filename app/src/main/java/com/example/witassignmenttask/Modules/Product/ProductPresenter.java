package com.example.witassignmenttask.Modules.Product;

import android.util.Log;

import com.example.witassignmenttask.Models.DataProduct;
import com.example.witassignmenttask.Models.Home;
import com.example.witassignmenttask.Modules.Home.HomeView;
import com.example.witassignmenttask.Networks.APIService;
import com.example.witassignmenttask.Networks.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter {
    ProductView view;

    public ProductPresenter(ProductView view) {
        this.view = view;
    }

    public void getProductData() {
        APIService apiService = new RetrofitClient().getApiService();

        Call call = apiService.getProduct();
        call.enqueue(new Callback<ArrayList<DataProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<DataProduct>> call, Response<ArrayList<DataProduct>> response) {
                if(response.isSuccessful()&& response.body() != null) {

                    view.loadProduct(response.body());
                    Log.e("doneHome","SUCCESS Get DataHome Home");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DataProduct>> call, Throwable t) {
                Log.e("errorHome","Failure Get DataHome Home");
            }


        });
    }
}
