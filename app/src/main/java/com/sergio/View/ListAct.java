package com.sergio.View;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import com.sergio.Contract.ListActContract;
import com.sergio.Model.ListActGetDessertInteractor;
import com.sergio.Presenter.ListActPresenter;
import com.sergio.R;
import com.sergio.Util.Utils;

public class ListAct extends AppCompatActivity implements ListActContract.ViewInterface {

    private EditText edtDessertTypeFilter;
    private ImageView addDessert;
    private ListActPresenter presenter;
    private RecyclerView recyclerView;

    //***********************GETTERS AND SETTERS******************************
    public EditText getEdtDessertTypeFilter() {
        return edtDessertTypeFilter;
    }

    public ImageView getAddDessert() {
        return addDessert;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    //***********************OVERRIDE METHODS******************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (Utils.isConnected(this))presenter.requestDataFromServer();
        else showDialogInternetRequired();
        presenter.initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void initView() {

        edtDessertTypeFilter = findViewById(R.id.autoCompDessert);
        addDessert = findViewById(R.id.addDessert);
        recyclerView = findViewById(R.id.recyclerDessert);
        presenter = new ListActPresenter(new ListActGetDessertInteractor(), this);
    }

    //***********************INSTANCE METHODS******************************
    private void showDialogInternetRequired(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getResources().getString(R.string.titleDialogInternetRequired));
        dialog.setCancelable(false);
        dialog.setMessage(getResources().getString(R.string.txtDialogInternetRequired));
        //Message for Yes button
        dialog.setPositiveButton(getResources().getString(R.string.txtBtnOk),
                (dialogInterface, i) -> {
                });
        //Show the dialog
        dialog.show();
    }

}
