package com.example.witassignmenttask.modules.home;

import com.example.witassignmenttask.models.DataBanner;
import com.example.witassignmenttask.models.DataHome;

import java.util.ArrayList;

public interface HomeView {
    void loadGridHome(ArrayList<DataHome> listHomeBlock);
    void error(String message);
    void loadBanner(ArrayList<DataBanner> listBanner);
}
