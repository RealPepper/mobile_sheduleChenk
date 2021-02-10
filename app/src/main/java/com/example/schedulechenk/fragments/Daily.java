package com.example.schedulechenk.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.schedulechenk.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Daily extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        bottomNavigationView.setSelectedItemId(R.id.navigation_daily);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch ((menuItem.getItemId()))
                {
                    case R.id.navigation_weekly:
                        startActivity(new Intent(getApplicationContext(),
                                Weekly.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_daily:

                        return true;
                    case R.id.navigation_user:
                        startActivity(new Intent(getApplicationContext(),
                                User.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }



}