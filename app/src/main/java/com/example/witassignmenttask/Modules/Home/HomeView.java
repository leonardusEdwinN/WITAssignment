package com.example.witassignmenttask.Modules.Home;

import com.example.witassignmenttask.Models.Banner;
import com.example.witassignmenttask.Models.DataBanner;
import com.example.witassignmenttask.Models.DataHome;
import com.example.witassignmenttask.Models.Home;

import java.util.ArrayList;
import java.util.List;

public interface HomeView {
    void loadGridHome(ArrayList<DataHome> listHomeBlock);
    void error(String message);
    void loadBanner(ArrayList<DataBanner> listBanner);
}
