package com.example.witassignmenttask.Modules.Home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import dmax.dialog.SpotsDialog;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.witassignmenttask.Models.Banner;
import com.example.witassignmenttask.Models.DataBanner;
import com.example.witassignmenttask.Models.DataHome;
import com.example.witassignmenttask.Models.Home;
import com.example.witassignmenttask.R;
import com.example.witassignmenttask.Utils.PicassoLoadingService;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, parent, false);

        slider = (Slider)view.findViewById(R.id.slider);
        slider.init(new PicassoLoadingService());

        recyclerHome = (RecyclerView)view.findViewById(R.id.recycler_home);

        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getHomeBlockData();
        homePresenter.getBanner();

//        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                loadGridHome();
////                loadBanner();
//            }
//        });

        return  view;
    }


    @Override
    public void loadGridHome(ArrayList<DataHome> listHomeBlock) {
        this.listHomeBlock = listHomeBlock;

//        //ShowDialog
//        alertDialog = new SpotsDialog.Builder().setContext(getContext())
//                .setCancelable(false)
//                .setMessage("Please Wait....")
//                .build();
//        if(!swipeRefreshLayout.isRefreshing())
//            alertDialog.show();

        homeAdapter = new HomeAdapter(getContext(),listHomeBlock);
        recyclerHome.setHasFixedSize(true);
        recyclerHome.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerHome.setAdapter(homeAdapter);
//        if(!swipeRefreshLayout.isRefreshing())
//            alertDialog.dismiss();
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
    }


}
