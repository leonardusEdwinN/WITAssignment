package com.example.witassignmenttask.Modules.Search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.witassignmenttask.Models.DataProduct;
import com.example.witassignmenttask.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    SearchAdapter searchAdapter;
    ArrayList<DataProduct> listDataProduct;
    RecyclerView recyclerProduct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_search, parent, false);
    }
}
