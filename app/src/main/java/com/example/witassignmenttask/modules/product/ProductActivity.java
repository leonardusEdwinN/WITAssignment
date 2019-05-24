package com.example.witassignmenttask.modules.product;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.example.witassignmenttask.models.DataProduct;
import com.example.witassignmenttask.modules.productdetail.ProductDetailActivity;
import com.example.witassignmenttask.R;
import com.example.witassignmenttask.utils.ViewDialog;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements ProductView, ProductAdapter.ProductCallBack {
    ProductAdapter productAdapter;
    ArrayList<DataProduct> listOfProduct;
    RecyclerView recyclerProduct;
    int count;
    ProductPresenter productPresenter;
    String category_name;
    SwipyRefreshLayout refresh;
    ViewDialog viewDialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
        }


        if (id == R.id.action_category) {//this is menu to sort action
            Toast.makeText(ProductActivity.this, "Sort clicked", Toast.LENGTH_LONG).show();
            openBottomSort();
            return true;
        }else if(id == R.id.action_filter){//this is menu to filter action
            Toast.makeText(ProductActivity.this, "Filter clicked", Toast.LENGTH_LONG).show();
            openBottomFilter();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        recyclerProduct = (RecyclerView)findViewById(R.id.recycler_product);

        viewDialog = new ViewDialog(this);
        viewDialog.showDialog();

        Toolbar productToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(productToolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        TextView txToolBar = (TextView)findViewById(R.id.txtToolBar);
        txToolBar.setText("Product");

        category_name = getIntent().getStringExtra("category_name");//get data from another activity

        listOfProduct = new ArrayList<>();
        productAdapter = new ProductAdapter(this,listOfProduct,this);
        recyclerProduct.setHasFixedSize(true);
        recyclerProduct.setLayoutManager(new GridLayoutManager(this,2));
        recyclerProduct.setAdapter(productAdapter);

        count = 10;

        productPresenter = new ProductPresenter( this);
        productPresenter.getProductData(count,category_name);

        refresh = findViewById(R.id.refresh_layout);

        //function to refresh on +10 item when you load on the bottom page
        //when you load on top of page it will come 10 again
        refresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if(direction == SwipyRefreshLayoutDirection.TOP)
                    count = 10;
                else
                    count +=10;


                productPresenter.getProductData(count,category_name);
            }
        });

    }


    @Override
    public void loadProduct(ArrayList<DataProduct> listOfProduct) {
//        this.listOfProduct = listOfProduct;
        if(refresh.isRefreshing())
            refresh.setRefreshing(false);

        this.listOfProduct.clear();

        for(DataProduct prod: listOfProduct)
            this.listOfProduct.add(prod);

        productAdapter.notifyDataSetChanged();

        viewDialog.hideDialog();

    }

    @Override
    public void error(String message) {
        Toast.makeText(this,"ERROR", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickProduct(int position) {

        Intent intent = new Intent(this, ProductDetailActivity.class );
        intent.putExtra("productDetail",listOfProduct.get(position));
        startActivity(intent);

    }

    //to show sort dialog on the bottom of page
    public void openBottomSort() {

        View view = getLayoutInflater().inflate(R.layout.fragment_dialog_sort, null);

        final Dialog mBottomSheetDialog  = new Dialog(ProductActivity.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();

        RadioButton lowerPrice = (RadioButton) view.findViewById(R.id.rd_lowestPrice);
        RadioButton highestPrice = (RadioButton) view.findViewById(R.id.rd_highestPrice);
        RadioButton quantity = (RadioButton) view.findViewById(R.id.rd_quantity);
        ImageButton close = (ImageButton)view.findViewById(R.id.btnCloseSort);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });

        //function to sort based on lowest price
        lowerPrice.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                ArrayList<DataProduct> toSort = new ArrayList<>();
                for (DataProduct data : listOfProduct)
                    toSort.add(data);

                Ordering<DataProduct> orderingByLowestPrice = new Ordering<DataProduct>() {
                    @Override
                    public int compare(DataProduct p1, DataProduct p2) {
                        return Ints.compare(p1.getPrice(), p2.getPrice());
                    }
                };

                toSort.sort(orderingByLowestPrice);
                loadProduct(toSort);
                Toast.makeText(ProductActivity.this, "Sort by Lowest Price...", Toast.LENGTH_LONG).show();
                mBottomSheetDialog.dismiss();
            }
        });


        //function to sort based on highest price
        highestPrice.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                ArrayList<DataProduct> toSort = new ArrayList<>();
                for (DataProduct data : listOfProduct)
                    toSort.add(data);

                Ordering<DataProduct> orderingByLowestPrice = new Ordering<DataProduct>() {
                    @Override
                    public int compare(DataProduct p1, DataProduct p2) {
                        return Ints.compare(p2.getPrice(), p1.getPrice());
                    }
                };

                toSort.sort(orderingByLowestPrice);
                loadProduct(toSort);

                Toast.makeText(ProductActivity.this, "Sort by Highest Price...", Toast.LENGTH_LONG).show();
                mBottomSheetDialog.dismiss();
            }
        });

        //function to sort based on quantity
        quantity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                ArrayList<DataProduct> toSort = new ArrayList<>();
                for (DataProduct data : listOfProduct)
                    toSort.add(data);

                Ordering<DataProduct> orderingByLowestPrice = new Ordering<DataProduct>() {
                    @Override
                    public int compare(DataProduct p1, DataProduct p2) {
                        return Ints.compare(p1.getQuantity(), p2.getQuantity());
                    }
                };

                toSort.sort(orderingByLowestPrice);
                loadProduct(toSort);

                Toast.makeText(ProductActivity.this, "Sort by Quantity...", Toast.LENGTH_LONG).show();
                mBottomSheetDialog.dismiss();
            }
        });
    }


    //to show filter dialog on the bottom of page
    public void openBottomFilter() {
        View view = getLayoutInflater().inflate(R.layout.fragment_dialog_filter, null);

        final Dialog mBottomSheetDialog = new Dialog(ProductActivity.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();

        final TextView txtRSCH = (TextView)view.findViewById(R.id.txtRSCH);
        final TextView txtMale = (TextView)view.findViewById(R.id.txtMale);
        final TextView txtNavy = (TextView)view.findViewById(R.id.txtNavy);
        final TextView txtBlue = (TextView)view.findViewById(R.id.txtBlue);
        final TextView txtRed = (TextView)view.findViewById(R.id.txtRed);
        final TextView txtAngs = (TextView)view.findViewById(R.id.txtAngs);
        final TextView txtIngs = (TextView)view.findViewById(R.id.txtIngs);
        final TextView txtSot = (TextView)view.findViewById(R.id.txtSot);
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) view.findViewById(R.id.bar_range_price);
        final TextView tvMin = (TextView) view.findViewById(R.id.textMin1);
        final TextView tvMax = (TextView) view.findViewById(R.id.textMax1);

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText("Rp. "+String.valueOf(minValue));
                tvMax.setText("Rp. "+String.valueOf(maxValue));
            }
        });

        // set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });

        Button btnFilter = (Button)view.findViewById(R.id.btnFilter);
        ImageButton close = (ImageButton)view.findViewById(R.id.btnCloseFilter);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });

        //UI to change color from dark gray to blue
        //=======================================
        txtRSCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtRSCH.setBackgroundResource(R.drawable.rounded_filter_blue);
            }
        });
        txtMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMale.setBackgroundResource(R.drawable.rounded_filter_blue);
            }
        });
        txtNavy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNavy.setBackgroundResource(R.drawable.rounded_filter_blue);
            }
        });
        txtBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBlue.setBackgroundResource(R.drawable.rounded_filter_blue);
            }
        });
        txtRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtRed.setBackgroundResource(R.drawable.rounded_filter_blue);
            }
        });
        txtAngs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAngs.setBackgroundResource(R.drawable.rounded_filter_blue);
            }
        });
        txtIngs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtIngs.setBackgroundResource(R.drawable.rounded_filter_blue);
            }
        });
        txtSot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSot.setBackgroundResource(R.drawable.rounded_filter_blue);
            }
        });
        //===========================================

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductActivity.this, "Filtering...", Toast.LENGTH_LONG).show();
                mBottomSheetDialog.dismiss();
            }
        });
    }
}
