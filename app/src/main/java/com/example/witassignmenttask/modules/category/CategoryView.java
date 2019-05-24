package com.example.witassignmenttask.modules.category;


import com.example.witassignmenttask.models.DataCategory;

import java.util.ArrayList;

public interface CategoryView {
    //View is use to connect fragment with presenter
    void loadGridCategory(ArrayList<DataCategory> listOfCategory);
    void error(String message);
}
