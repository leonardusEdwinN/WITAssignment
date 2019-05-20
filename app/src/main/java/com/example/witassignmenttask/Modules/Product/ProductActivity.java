package com.example.witassignmenttask.Modules.Product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.witassignmenttask.Models.DataBanner;
import com.example.witassignmenttask.Models.DataHome;
import com.example.witassignmenttask.Models.DataProduct;
import com.example.witassignmenttask.Modules.Home.HomeAdapter;
import com.example.witassignmenttask.Modules.Home.HomePresenter;
import com.example.witassignmenttask.R;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements ProductView{
    ProductAdapter productAdapter;
    ArrayList<DataProduct> listOfProduct;
    RecyclerView recyclerProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        recyclerProduct = (RecyclerView)findViewById(R.id.recycler_home);

        ProductPresenter productPresenter = new ProductPresenter( this);
        productPresenter.getProductData();

    }


    @Override
    public void loadProduct(ArrayList<DataProduct> listOfProduct) {
        this.listOfProduct = listOfProduct;

        productAdapter = new ProductAdapter(this,listOfProduct);
        recyclerProduct.setHasFixedSize(true);
        recyclerProduct.setLayoutManager(new GridLayoutManager(this,2));
        recyclerProduct.setAdapter(productAdapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this,"ERROR", Toast.LENGTH_SHORT).show();
    }
}
