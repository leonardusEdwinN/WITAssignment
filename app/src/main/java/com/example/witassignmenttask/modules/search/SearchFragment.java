package com.example.witassignmenttask.modules.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cielyang.android.clearableedittext.ClearableEditText;
import com.example.witassignmenttask.models.DataProduct;
import com.example.witassignmenttask.modules.productdetail.ProductDetailActivity;
import com.example.witassignmenttask.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements  com.example.witassignmenttask.modules.search.SearchView, SearchAdapter.SearchCallback {
    SearchAdapter searchAdapter;
    ArrayList<DataProduct> listDataSearchProduct;
    RecyclerView recyclerSearchProduct;
    ClearableEditText searchView;
    SearchPresenter searchPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_search, parent, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);


        searchPresenter = new SearchPresenter(SearchFragment.this);

        listDataSearchProduct = new ArrayList<>();
        searchAdapter = new SearchAdapter(getContext(),listDataSearchProduct,SearchFragment.this);

        recyclerSearchProduct = (RecyclerView)view.findViewById(R.id.recycler_search);
        recyclerSearchProduct.setHasFixedSize(true);
        recyclerSearchProduct.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerSearchProduct.setAdapter(searchAdapter);

        searchPresenter.getDataSearch("");

        searchView = view.findViewById(R.id.search);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(getContext(), s.toString(), Toast.LENGTH_SHORT).show();
                searchPresenter.getDataSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    @Override
    public void searchGetData(ArrayList<DataProduct> listOfDataSearch) {

        listDataSearchProduct.clear();

        for (DataProduct data : listOfDataSearch)
            listDataSearchProduct.add(data);

        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(String message) {

    }

    @Override
    public void onClickSearchProduct(int position) {
        Intent intent = new Intent(getActivity(), ProductDetailActivity.class );
        intent.putExtra("productDetail", listDataSearchProduct.get(position));
        startActivity(intent);
    }
}
