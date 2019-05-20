package com.example.witassignmenttask.Modules.Product;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.witassignmenttask.Models.DataHome;
import com.example.witassignmenttask.Models.DataProduct;
import com.example.witassignmenttask.Modules.Home.HomeAdapter;
import com.example.witassignmenttask.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataProduct> listOfProduct;
    LayoutInflater inflater;

    public ProductAdapter(Context context, ArrayList<DataProduct> listOfProduct) {
        this.context = context;
        this.listOfProduct = listOfProduct;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_grid_home,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Picasso.get()
                .load(listOfProduct.get(i).getCover())
                .placeholder(R.drawable.ic_home)
                .error(R.drawable.ic_home)
                .into(myViewHolder.imgProduct);
        myViewHolder.txtProductName.setText(listOfProduct.get(i).getName());
        myViewHolder.txtProuctPrice.setText(listOfProduct.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return listOfProduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtProductName;
        TextView txtProuctPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = (ImageView)itemView.findViewById(R.id.img_product);
            txtProductName = (TextView)itemView.findViewById(R.id.txt_product_name);
            txtProuctPrice = (TextView)itemView.findViewById(R.id.txt_product_price);
        }
    }
}
