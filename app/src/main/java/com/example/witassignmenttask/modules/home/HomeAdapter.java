package com.example.witassignmenttask.modules.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.witassignmenttask.models.DataHome;
import com.example.witassignmenttask.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    Context context;
    ArrayList<DataHome> listHomeBlock;
    LayoutInflater inflater;

    public HomeAdapter(Context context, ArrayList<DataHome> listHomeBlock) {
        this.context = context;
        this.listHomeBlock = listHomeBlock;
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
                .load(listHomeBlock.get(i).getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(myViewHolder.imgHome);
        //myViewHolder.txtHome.setText(listHomeBlock.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return listHomeBlock.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtHome;
        ImageView imgHome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHome = (TextView)itemView.findViewById(R.id.txt_home);
            imgHome = (ImageView)itemView.findViewById(R.id.img_home);
        }
    }
}
