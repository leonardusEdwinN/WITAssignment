package com.example.witassignmenttask.modules.search;

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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataProduct> listofDataSearch;
    SearchCallback callback;

    public SearchAdapter(Context context, ArrayList<DataProduct> listofDataSearch, SearchCallback callback) {
        this.context = context;
        this.listofDataSearch = listofDataSearch;
        this.callback = callback;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_search,viewGroup,false);
        return new SearchAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        Picasso.get()
                .load(listofDataSearch.get(i).getCover())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .fit()
                .into(myViewHolder.imgSearchProduct);
        myViewHolder.txtSearchProductname.setText(listofDataSearch.get(i).getName());
        myViewHolder.txtSearchProductPrice.setText(""+listofDataSearch.get(i).getPrice());

        myViewHolder.cardViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickSearchProduct(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listofDataSearch.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSearchProduct;
        TextView txtSearchProductname;
        TextView txtSearchProductPrice;
        CardView cardViewSearch;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSearchProduct = (ImageView)itemView.findViewById(R.id.img_search);
            txtSearchProductname = (TextView)itemView.findViewById(R.id.txt_search_name);
            txtSearchProductPrice = (TextView)itemView.findViewById(R.id.txt_search_price);
            cardViewSearch = (CardView) itemView.findViewById(R.id.cv_search);
        }
    }

    public interface SearchCallback{

        void onClickSearchProduct(int position);
    }
}
