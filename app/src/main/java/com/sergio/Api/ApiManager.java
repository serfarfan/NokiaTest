package com.sergio.Api;

import com.sergio.Util.Constants;

public class ApiManager {

    private ApiManager() {
    }

    public static ListApi getAPIService() {

        return RetrofitClient.getClient(Constants.URL).create(ListApi.class);
    }
}
