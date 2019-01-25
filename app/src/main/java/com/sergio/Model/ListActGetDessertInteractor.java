package com.sergio.Model;

import com.sergio.Api.ApiManager;
import com.sergio.Api.ListApi;
import com.sergio.Api.Request.DessertRequest;
import com.sergio.Contract.ListActContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActGetDessertInteractor implements ListActContract.GetDessertInteractor {


    @Override
    public void getDessertArrayList(final OnFinishedListener onFinishedListener) {

        ListApi listApi = ApiManager.getAPIService();

        Call<List<DessertRequest>> call = listApi.getDessertList();
        call.enqueue(new Callback<List<DessertRequest>>() {
            @Override
            public void onResponse(Call<List<DessertRequest>> call, Response<List<DessertRequest>> response) {
                ArrayList<DessertRequest> dessertList = new ArrayList<>(response.body());
                onFinishedListener.onFinished(dessertList);
            }

            @Override
            public void onFailure(Call<List<DessertRequest>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
