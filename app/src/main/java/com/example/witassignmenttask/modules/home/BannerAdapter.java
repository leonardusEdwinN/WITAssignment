package com.example.witassignmenttask.modules.home;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.witassignmenttask.models.DataBanner;
import com.example.witassignmenttask.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

//this class is use to load banner image
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
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(imageSlideViewHolder.imageView);
        //imageSlideViewHolder.bindImageSlide(listBanner.get(position).getImage());
    }
}
