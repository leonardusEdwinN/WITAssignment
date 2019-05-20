package com.example.witassignmenttask.Modules.Category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.witassignmenttask.Models.DataCategory;
import com.example.witassignmenttask.R;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements CategoryView{
    CategoryAdapter categoryAdapter;
    ArrayList<DataCategory> listOfCategory;
    RecyclerView recyclerCategory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_category, parent, false);
        recyclerCategory = (RecyclerView)view.findViewById(R.id.recycler_category);

        CategoryPresenter homePresenter = new CategoryPresenter(this);
        homePresenter.getCategoryData();

        return  view;
    }


    @Override
    public void loadGridCategory(ArrayList<DataCategory> listOfCategory) {
        this.listOfCategory = listOfCategory;


        categoryAdapter = new CategoryAdapter(getContext(),listOfCategory);
        recyclerCategory.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getBaseContext(), 3);
        recyclerCategory.setLayoutManager(gridLayoutManager);
        recyclerCategory.setAdapter(categoryAdapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(getContext(),"ERROR", Toast.LENGTH_SHORT).show();
    }
}
