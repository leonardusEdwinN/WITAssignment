package com.example.witassignmenttask.modules.productdetail;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.witassignmenttask.models.DataProduct;
import com.example.witassignmenttask.R;
import com.example.witassignmenttask.utils.PicassoLoadingService;

import java.util.ArrayList;

import ss.com.bannerslider.Slider;

public class ProductDetailActivity extends AppCompatActivity {

    ProductDetailAdapter productDetailAdapter;
    TextView txtProductName;
    TextView txtDisount;
    TextView txtPrice;
    ArrayList<String> listImage;
    Button btnAddBag;
    TextView txtCount;
    Slider slider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        //add back button
        ImageButton btnBack = (ImageButton)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //to get Data from Product
        DataProduct productDetail = getIntent().getParcelableExtra("productDetail");

        //Initialization
        txtProductName = (TextView)findViewById(R.id.txt_productNameDetail);
        txtPrice = (TextView)findViewById(R.id.txtPriceDetail);
        txtCount = (TextView)findViewById(R.id.txtCount);
        btnAddBag = (Button)findViewById(R.id.btnAddtoBag);
        btnAddBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetailActivity.this, "You Add Item to Bag", Toast.LENGTH_SHORT).show();
                int count = Integer.parseInt(String.valueOf(txtCount.getText())) + 1;
                txtCount.setText( ""+count);
            }
        });
        txtCount = (TextView)findViewById(R.id.txtCount);
        txtDisount = (TextView)findViewById(R.id.txtDiscountPriceDetail);
        txtDisount.setPaintFlags(txtDisount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        //Add Image to list for banner
        listImage = new ArrayList<>();
        listImage.add(productDetail.getCover());

        //set Text on TextView
        txtProductName.setText(productDetail.getName());
        txtPrice.setText( ""+productDetail.getPrice());
        txtDisount.setText(""+productDetail.getPriceBeforeDiscount());

        slider = (Slider)findViewById(R.id.sliderProductDetail);
        slider.init(new PicassoLoadingService());

        //Set Adapter to show iamge in Banner
        productDetailAdapter = new ProductDetailAdapter(listImage,this);
        slider.setAdapter(productDetailAdapter);
    }
}
