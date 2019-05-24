package com.example.witassignmenttask.modules.product;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.witassignmenttask.models.DataProduct;
import com.example.witassignmenttask.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataProduct> listOfProduct;
    ProductCallBack callback;

    public ProductAdapter(Context context, ArrayList<DataProduct> listOfProduct, ProductCallBack callback) {
        this.context = context;
        this.listOfProduct = listOfProduct;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_product,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        Picasso.get()
                .load(listOfProduct.get(i).getCover())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .fit()
                .into(myViewHolder.imgProduct);
        myViewHolder.txtProductName.setText(listOfProduct.get(i).getName());
        myViewHolder.txtProuctPrice.setText(""+listOfProduct.get(i).getPrice());

        myViewHolder.cardviewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickProduct(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfProduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtProductName;
        TextView txtProuctPrice;
        CardView cardviewProduct;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = (ImageView)itemView.findViewById(R.id.img_product);
            txtProductName = (TextView)itemView.findViewById(R.id.txt_product_name);
            txtProuctPrice = (TextView)itemView.findViewById(R.id.txt_product_price);
            cardviewProduct = (CardView) itemView.findViewById(R.id.cv_product);
        }
    }
    public interface ProductCallBack{

        void onClickProduct(int position);
    }
}
