package com.example.witassignmenttask.modules;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.witassignmenttask.modules.category.CategoryFragment;
import com.example.witassignmenttask.modules.home.HomeFragment;
import com.example.witassignmenttask.modules.search.SearchFragment;
import com.example.witassignmenttask.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            TextView txtTitle = findViewById(R.id.txtToolBar);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    txtTitle.setText("Home");
                    txtTitle.setVisibility(View.VISIBLE);
                    transaction.replace(R.id.frameLayoutMain,new HomeFragment()).commit();
                    return true;
                case R.id.navigation_search:
                    txtTitle.setVisibility(View.INVISIBLE);
                    transaction.replace(R.id.frameLayoutMain,new SearchFragment()).commit();
                    return true;
                case R.id.navigation_category:
                    txtTitle.setText("Category");
                    txtTitle.setVisibility(View.VISIBLE);
                    transaction.replace(R.id.frameLayoutMain,new CategoryFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtTitle = findViewById(R.id.txtToolBar);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        txtTitle.setText("Home");
        txtTitle.setVisibility(View.VISIBLE);
        transaction.replace(R.id.frameLayoutMain,new HomeFragment()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
