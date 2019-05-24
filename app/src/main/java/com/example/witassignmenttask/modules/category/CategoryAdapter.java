package com.example.witassignmenttask.modules.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.witassignmenttask.models.DataCategory;
import com.example.witassignmenttask.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataCategory> listOfCategory;
    CategoryCallback callback;

    public CategoryAdapter(Context context, ArrayList<DataCategory> listOfCategory, CategoryCallback callback) {
        this.context = context;
        this.listOfCategory = listOfCategory;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_category, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {

        String title = listOfCategory.get(position).getName();
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
        myViewHolder.txtCategoryName.setText((listOfCategory.get(position).getName()));

        // on action when click one item category.
        // on click action using interface so the real action will be on the fragment
        myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickCategory(position);
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
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategoryId = (TextView)itemView.findViewById(R.id.txt_category_id);
            txtCategoryName = (TextView)itemView.findViewById(R.id.txt_category_name);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayoutCategory);
        }
    }

    //this is the interface to call action to get the item position will direct to CategoryFragment
    public interface CategoryCallback{

        void onClickCategory(int position);
    }
}
