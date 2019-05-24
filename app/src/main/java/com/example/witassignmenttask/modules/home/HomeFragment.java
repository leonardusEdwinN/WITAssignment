package com.example.witassignmenttask.modules.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.witassignmenttask.models.DataBanner;
import com.example.witassignmenttask.models.DataHome;
import com.example.witassignmenttask.R;
import com.example.witassignmenttask.utils.PicassoLoadingService;
import com.example.witassignmenttask.utils.ViewDialog;

import java.util.ArrayList;

import ss.com.bannerslider.Slider;

public class HomeFragment extends Fragment implements HomeView{
    HomeAdapter homeAdapter;
    ArrayList<DataHome> listHomeBlock;
    ArrayList<DataBanner> listBanner;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerHome;
    BannerAdapter bannerAdapter;
    Slider slider;
    AlertDialog alertDialog;
    ViewDialog  viewDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, parent, false);

        slider = (Slider)view.findViewById(R.id.slider);
        slider.init(new PicassoLoadingService());

        recyclerHome = (RecyclerView)view.findViewById(R.id.recycler_home);

        HomePresenter homePresenter = new HomePresenter(this);

        viewDialog = new ViewDialog(getActivity());
        viewDialog.showDialog();

        homePresenter.getHomeBlockData();
        homePresenter.getBanner();

        return  view;
    }


    @Override
    public void loadGridHome(ArrayList<DataHome> listHomeBlock) {
        this.listHomeBlock = listHomeBlock;

        homeAdapter = new HomeAdapter(getContext(),listHomeBlock);
        recyclerHome.setHasFixedSize(true);
        recyclerHome.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerHome.setAdapter(homeAdapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(getContext(),"ERROR", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadBanner(ArrayList<DataBanner> listBanner) {
        this.listBanner = listBanner;

        bannerAdapter = new BannerAdapter(listBanner,getContext());
        slider.setAdapter(bannerAdapter);

        viewDialog.hideDialog();
    }


}
