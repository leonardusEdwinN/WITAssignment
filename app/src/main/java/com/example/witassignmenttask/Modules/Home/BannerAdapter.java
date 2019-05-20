package com.example.witassignmenttask.Modules.Home;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.witassignmenttask.Models.Banner;
import com.example.witassignmenttask.Models.DataBanner;
import com.example.witassignmenttask.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class BannerAdapter extends SliderAdapter{
    private ArrayList<DataBanner> listBanner;
    Context context;
    LayoutInflater inflater;


    public BannerAdapter(ArrayList<DataBanner> listBanner, Context context) {
        this.listBanner = listBanner;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return listBanner.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        Picasso.get()
                .load(listBanner.get(position).getImage())
                .placeholder(R.drawable.ic_home)
                .error(R.drawable.ic_home)
                .into(imageSlideViewHolder.imageView);
        imageSlideViewHolder.bindImageSlide(listBanner.get(position).getImage());
    }
}
