package com.example.witassignmenttask.modules.search;

import android.util.Log;

import com.example.witassignmenttask.models.DataProduct;
import com.example.witassignmenttask.networks.APIService;
import com.example.witassignmenttask.networks.RetrofitClient;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {
    SearchView view;

    public SearchPresenter(SearchView view) {
        this.view = view;
    }

    public void getDataSearch(String dataSearch) {
        APIService apiService = new RetrofitClient().getApiService();

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("page", 1);
        jsonParams.put("count", 100);
        jsonParams.put("keyword",dataSearch);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());


        Call call = apiService.getSearchProduct(body);
        call.enqueue(new Callback<ArrayList<DataProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<DataProduct>> call, Response<ArrayList<DataProduct>> response) {
                if(response.isSuccessful()&& response.body() != null) {

                    ArrayList<DataProduct> bod = response.body();
                    view.searchGetData(response.body());
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
