package com.example.witassignmenttask.Modules.Category;


import com.example.witassignmenttask.Models.DataCategory;

import java.util.ArrayList;

public interface CategoryView {
    void loadGridCategory(ArrayList<DataCategory> listOfCategory);
    void error(String message);
}
