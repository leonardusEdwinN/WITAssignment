package com.example.witassignmenttask.Modules.Product;

import com.example.witassignmenttask.Models.DataProduct;

import java.util.ArrayList;

public interface ProductView  {
    void loadProduct(ArrayList<DataProduct> listOfProduct);
    void error(String message);
}
