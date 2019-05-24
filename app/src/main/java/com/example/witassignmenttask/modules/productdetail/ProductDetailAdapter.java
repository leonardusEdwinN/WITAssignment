package com.example.witassignmenttask.modules.productdetail;

import android.content.Context;
import android.view.LayoutInflater;
import com.example.witassignmenttask.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class ProductDetailAdapter extends SliderAdapter {
    private ArrayList<String> listProduct;
    Context context;
    LayoutInflater inflater;

    public ProductDetailAdapter(ArrayList<String> listProduct, Context context) {
        this.listProduct = listProduct;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        Picasso.get()
                .load(listProduct.get(position))
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(imageSlideViewHolder.imageView);
        //imageSlideViewHolder.bindImageSlide(listProduct.get(position));
    }
}
