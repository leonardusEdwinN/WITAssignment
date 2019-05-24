package com.example.witassignmenttask.modules.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.witassignmenttask.models.DataCategory;
import com.example.witassignmenttask.modules.product.ProductActivity;
import com.example.witassignmenttask.R;
import com.example.witassignmenttask.utils.ViewDialog;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements CategoryView, CategoryAdapter.CategoryCallback {
    CategoryAdapter categoryAdapter;
    ArrayList<DataCategory> listOfCategory;
    RecyclerView recyclerCategory;
    ViewDialog viewDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_category, parent, false);
        recyclerCategory = (RecyclerView)view.findViewById(R.id.recycler_category);

        viewDialog = new ViewDialog(getActivity());
        viewDialog.showDialog();

        CategoryPresenter homePresenter = new CategoryPresenter(this);
        homePresenter.getCategoryData();

        return  view;
    }

    //this is the function implement of CategoryView
    @Override
    public void loadGridCategory(ArrayList<DataCategory> listOfCategory) {
        this.listOfCategory = listOfCategory;


        categoryAdapter = new CategoryAdapter(getContext(),listOfCategory,this);
        recyclerCategory.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getBaseContext(), 3);
        recyclerCategory.setLayoutManager(gridLayoutManager);
        recyclerCategory.setAdapter(categoryAdapter);

        viewDialog.hideDialog();
    }

    @Override
    public void error(String message) {
        Toast.makeText(getContext(),"ERROR", Toast.LENGTH_SHORT).show();
    }

    //when you click on item, directly change to another activity with data
    @Override
    public void onClickCategory(int position) {
        Intent intent = new Intent(getContext(), ProductActivity.class );
        intent.putExtra("category_name", listOfCategory.get(position).getName());
        getContext().startActivity(intent);
    }
}
