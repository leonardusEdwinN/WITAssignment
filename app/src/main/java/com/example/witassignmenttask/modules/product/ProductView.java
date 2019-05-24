package com.example.witassignmenttask.modules.product;

import com.example.witassignmenttask.models.DataProduct;

import java.util.ArrayList;

public interface ProductView  {
    void loadProduct(ArrayList<DataProduct> listOfProduct);
    void error(String message);
}
