package com.sergio.Presenter;

import android.app.Dialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sergio.Adapter.DessertListAdapter;
import com.sergio.Api.Request.Batter;
import com.sergio.Api.Request.Batters;
import com.sergio.Api.Request.DessertRequest;
import com.sergio.Api.Request.Topping;
import com.sergio.Contract.ListActContract;
import com.sergio.Model.ListActGetDessertInteractor;
import com.sergio.R;
import com.sergio.View.ListAct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActPresenter implements ListActContract.Presenter,
             ListActContract.GetDessertInteractor.OnFinishedListener{

    private ListActContract.GetDessertInteractor getDessertInteractor;
    private ListAct activity;
    private ArrayList<DessertRequest> filterDessertList;
    private DessertListAdapter dessertListAdapter;
    private String type, batter, topping;
    private Spinner spnType, spnBatter, spnTopping;
    private EditText edtAddName, edtAddPpu;
    private final String SUCCESS = "Dessert was added successfully";

    //***********************CONSTRUCTOR******************************
    public ListActPresenter(ListActContract.GetDessertInteractor getDessertInteractor,
                            ListAct activity){
        this.getDessertInteractor = getDessertInteractor;
        this.activity = activity;
    }

    //***********************OVERRIDE METHODS******************************
    @Override
    public void onClick(View view) {

        Dialog dialog = new Dialog(activity,android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.content_add_dessert);
        spnType = dialog.findViewById(R.id.spinnerType);
        spnBatter = dialog.findViewById(R.id.spinnerBatter);
        spnTopping = dialog.findViewById(R.id.spinnerTopping);
        Button btnAddDessert;
        btnAddDessert = dialog.findViewById(R.id.btnAddDessert);
        edtAddName = dialog.findViewById(R.id.nameDessert);
        edtAddPpu = dialog.findViewById(R.id.ppuDessert);
        //Types for inflate spinner
        String [] arrTypes = {"donut", "bar", "filled", "twist"};
        List<String> types = Arrays.asList(arrTypes);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, R.layout.spn_type_item, types);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnType.setAdapter(adapter);
        //Batters for inflate spinner
        String [] arrBatters = {"Regular", "Chocolate"};
        List<String> batterList = Arrays.asList(arrBatters);
        ArrayAdapter<String> batterArrayAdapter = new ArrayAdapter<>(activity,
                R.layout.spn_type_item, batterList );
        batterArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBatter.setAdapter(batterArrayAdapter);
        //Toppings for inflate spinner
        String [] arrToppings = {"Sugar", "Chocolate", "None", "Glazed", "Maple"};
        List<String> toppingList = Arrays.asList(arrToppings);
        ArrayAdapter<String> toppingAdapter = new ArrayAdapter<>(activity,
                R.layout.spn_type_item, toppingList);
        toppingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTopping.setAdapter(toppingAdapter);
        //Get values from spinners
        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                type = "donut";
            }
        });

        spnBatter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                batter = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                batter = "Regular";
            }
        });

        spnTopping.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                topping = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                topping = "Sugar";
            }
        });
        //Add new dessert to ArrayList
        btnAddDessert.setOnClickListener((View v) -> {

            String name = edtAddName.getText().toString();
            String ppu = edtAddPpu.getText().toString();
            List<Topping> toppings = new ArrayList<>();
            toppings.add(new Topping(topping));

            List<Batter> batters = new ArrayList<>();
            Batter batterObj = new Batter(batter);
            batters.add(batterObj);
            Batters battersObj = new Batters(batters);
            DessertRequest dessertRequest = new DessertRequest(name, ppu, type, battersObj, toppings);
            filterDessertList.add(dessertRequest);
                    Toast.makeText(activity, SUCCESS, Toast.LENGTH_SHORT).show();
            //Update List
            dessertListAdapter = new DessertListAdapter(filterDessertList, activity);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
            activity.getRecyclerView().setLayoutManager(layoutManager);
            activity.getRecyclerView().addItemDecoration(
                    new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
            activity.getRecyclerView().setAdapter(dessertListAdapter);
            cleanFields();
        }
        );
        dialog.show();
    }

    @Override
    public void requestDataFromServer() {
        getDessertInteractor.getDessertArrayList(this);
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onFinished(ArrayList<DessertRequest> dessertList) {

        filterDessertList = dessertList;
        dessertListAdapter = new DessertListAdapter(dessertList, activity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        activity.getRecyclerView().setLayoutManager(layoutManager);
        activity.getRecyclerView().addItemDecoration(
                new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        activity.getRecyclerView().setAdapter(dessertListAdapter);
    }

    @Override
    public void onFailure(Throwable t) {
    }

    //***********************INSTANCE METHODS******************************
    public void initPresenter(){

        getDessertInteractor = new ListActGetDessertInteractor();
        //Launch dialog to add a new dessert
        activity.getAddDessert().setOnClickListener(this::onClick);
        //Filter by dessert type
        activity.getEdtDessertTypeFilter().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void cleanFields(){
        edtAddPpu.setText("");
        edtAddName.setText("");
        spnType.setSelection(0);
        spnBatter.setSelection(0);
        spnTopping.setSelection(0);
    }

    private void filter (String text){

        ArrayList<DessertRequest> filteredDessertList = new ArrayList<>();
        for (DessertRequest dessertRequest: filterDessertList){

            if (dessertRequest.getType().contains(text)){
                filteredDessertList.add(dessertRequest);
            }
        }
        dessertListAdapter.filterList(filteredDessertList);
    }
}
