package com.sergio.Contract;

import android.view.View;

import com.sergio.Api.Request.DessertRequest;

import java.util.ArrayList;

public interface ListActContract {

    interface ViewInterface{
        void initView();
    }


    interface GetDessertInteractor {

        interface OnFinishedListener{
            void onFinished(ArrayList<DessertRequest> dessertList);
            void onFailure(Throwable t);
       }

       void getDessertArrayList(OnFinishedListener onFinishedListener);
    }

    interface Presenter{

        void onClick(View view);
        void requestDataFromServer();
        void onDestroy();
    }


}
