package com.sergio.Api;

import com.sergio.Api.Request.DessertRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListApi {

    @GET("v2/5c424e4832000077037327b0/")
    Call<List<DessertRequest>> getDessertList();
}
