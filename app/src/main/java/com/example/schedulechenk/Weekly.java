package com.example.schedulechenk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Weekly extends AppCompatActivity {

    private  WeeklySheduleAdapter weeklySheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigation_weekly);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch ((menuItem.getItemId()))
                {
                    case R.id.navigation_weekly:

                        return true;
                    case R.id.navigation_daily:
                        startActivity(new Intent(getApplicationContext(),
                                Daily.class));
                        overridePendingTransition(0,0);
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
        setupWeeklySheduleItems();
        ViewPager2 sheduleViewPager2 = findViewById(R.id.viewPager);
        sheduleViewPager2.setAdapter(weeklySheduleAdapter);
    }
    void setupWeeklySheduleItems(){
        List<WeeklyCheduleItem> weeklyCheduleItems = new ArrayList<>();

        WeeklyCheduleItem monday = new WeeklyCheduleItem();
        monday.setDayOfWeek("Monday");
        monday.setCabinetOne("321");

        WeeklyCheduleItem  tuesday= new WeeklyCheduleItem();
        tuesday.setDayOfWeek("Tuesday");
        tuesday.setCabinetOne("3212");

        WeeklyCheduleItem wednesday = new WeeklyCheduleItem();
        wednesday.setDayOfWeek("Wednesday");
        wednesday.setCabinetOne("1");

        WeeklyCheduleItem thursday = new WeeklyCheduleItem();
        thursday.setDayOfWeek("Thursday");
        thursday.setCabinetOne("721");

        WeeklyCheduleItem friday = new WeeklyCheduleItem();
        friday.setDayOfWeek("Friday");
        friday.setCabinetOne("321");

        WeeklyCheduleItem saturday = new WeeklyCheduleItem();
        saturday.setDayOfWeek("Saturday");
        saturday.setCabinetOne("321");

        weeklyCheduleItems.add(monday);
        weeklyCheduleItems.add(tuesday);
        weeklyCheduleItems.add(wednesday);
        weeklyCheduleItems.add(thursday);
        weeklyCheduleItems.add(friday);
        weeklyCheduleItems.add(saturday);

        weeklySheduleAdapter = new WeeklySheduleAdapter(weeklyCheduleItems);
    }
}