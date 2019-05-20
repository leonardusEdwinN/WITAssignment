package com.example.witassignmenttask.Modules.Category;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.witassignmenttask.Models.DataCategory;
import com.example.witassignmenttask.Modules.Product.ProductActivity;
import com.example.witassignmenttask.R;
import com.example.witassignmenttask.Utils.IRecyclerClickItem;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataCategory> listOfCategory;
    LayoutInflater inflater;

    public CategoryAdapter(Context context, ArrayList<DataCategory> listOfCategory) {
        this.context = context;
        this.listOfCategory = listOfCategory;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_grid_category,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        // 1. Get title
        // 2. Split title ==> Array
        //

        String title = listOfCategory.get(i).getName();
        title = title.replaceAll("-", " ");
        String[] splitTitle= title.split("\\s");
        if(splitTitle.length != 1)
        {
            title = splitTitle[0].charAt(0)+""+splitTitle[1].charAt(0)+"";
        }
        else
        {
            title = splitTitle[0].charAt(0)+""+splitTitle[0].charAt(1)+"";
        }
        myViewHolder.txtCategoryId.setText(title);
        myViewHolder.txtCategoryName.setText((listOfCategory.get(i).getName()));

        myViewHolder.setRecyclerClickItemListener(new IRecyclerClickItem() {
            @Override
            public void onClick(View view, int position) {
//                Common.comicSelected = listOfCategory.get(position);
                context.startActivity(new Intent(context,ProductActivity.class));//.putExtra("categoryName",));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfCategory.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategoryId;
        TextView txtCategoryName;
        IRecyclerClickItem recyclerClickItem;

        public void setRecyclerClickItemListener(IRecyclerClickItem recyclerItemClickListener){
            this.recyclerClickItem = recyclerItemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategoryId = (TextView)itemView.findViewById(R.id.txt_category_id);
            txtCategoryName = (TextView)itemView.findViewById(R.id.txt_category_name);
        }
    }
}
