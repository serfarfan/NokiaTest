package com.sergio.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sergio.Api.Request.Batter;
import com.sergio.Api.Request.DessertRequest;
import com.sergio.Api.Request.Topping;
import com.sergio.R;

import java.util.ArrayList;

public class DessertListAdapter extends RecyclerView.Adapter<DessertListAdapter.DessertViewHolder> {

    private ArrayList<DessertRequest> dessertList;
    private Context context;
    private String REMOVED = "Dessert was removed successfully";

    public DessertListAdapter(ArrayList<DessertRequest> dessertList, Context context){
        this.dessertList = dessertList;
        this.context = context;
    }

    @NonNull
    @Override
    public DessertViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.dessert_item, viewGroup, false);
        return  new DessertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DessertViewHolder dessertViewHolder, int i) {

        dessertViewHolder.txtName.setText(dessertList.get(i).getName());
        dessertViewHolder.txtType.setText(dessertList.get(i).getType());
        dessertViewHolder.txtPpu.setText(String.valueOf(dessertList.get(i).getPpu()));
        //Iterate Toppings list
        Topping topping;
        String type, types = "";
        for (int j = 0; j < dessertList.get(i).getTopping().size(); j++){
            topping = dessertList.get(i).getTopping().get(j);
            type = topping.getType();
            types = types.concat(type + ", ");
        }
        types = types.substring(0, types.length() - 2);
        dessertViewHolder.txtToppingsValues.setText(types);
        //Iterate Batters list
        Batter batter;

        types = "";
        for (int k = 0; k < dessertList.get(i).getBatters().getBatter().size(); k++){
            batter = dessertList.get(i).getBatters().getBatter().get(k);
            type = batter.getType();
            types = types.concat(type + ", ");
        }
        types = types.substring(0, types.length() - 2);
        dessertViewHolder.txtBattersValues.setText(types);
        //Long Click Listener for remove item
        dessertViewHolder.itemView.setOnLongClickListener((View v) -> {
                   dessertList.remove(dessertViewHolder.getAdapterPosition());
                    notifyItemRemoved(dessertViewHolder.getAdapterPosition());
                    notifyItemRangeChanged(dessertViewHolder.getAdapterPosition(), dessertList.size());
                    Toast.makeText(context, REMOVED, Toast.LENGTH_SHORT).show();
                    return true;
        });
    }

    @Override
    public int getItemCount() {
        return dessertList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Class method to filter list of items
    public void filterList(ArrayList<DessertRequest> filteredList){
        dessertList = filteredList;
        notifyDataSetChanged();
    }

    class DessertViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtType, txtPpu, txtBattersValues, txtToppingsValues;

         DessertViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtType = itemView.findViewById(R.id.txtType);
            txtPpu = itemView.findViewById(R.id.txtPpu);
            txtBattersValues = itemView.findViewById(R.id.txtBattersValues);
            txtToppingsValues = itemView.findViewById(R.id.txtToppingsValues);
        }
    }
}
