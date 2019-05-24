package com.example.witassignmenttask.modules.search;

import com.example.witassignmenttask.models.DataProduct;

import java.util.ArrayList;

public interface SearchView {
    void searchGetData(ArrayList<DataProduct> listOfDataSearch);
    void error(String message);
}
