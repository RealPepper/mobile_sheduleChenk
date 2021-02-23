package com.example.schedulechenk.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.schedulechenk.R;
import com.example.schedulechenk.adapters.WeeklyPagerScheduleAdapter;
import com.example.schedulechenk.databinding.ActivityWeeklyBinding;
import com.example.schedulechenk.models.ScheduleModel;
import com.example.schedulechenk.parser.Parser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Weekly extends AppCompatActivity {

    private WeeklyPagerScheduleAdapter weeklyScheduleAdapter;
    private ActivityWeeklyBinding activityWeeklyBinding;
    private List<ScheduleModel> scheduleModels;

    private static final String PREFERENCES_NAME = "user_choice";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);


        activityWeeklyBinding = DataBindingUtil.setContentView(this, R.layout.activity_weekly);
        // тут вызов метода


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_weekly);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch ((menuItem.getItemId()))
            {
                case R.id.navigation_weekly:

                    return true;
                /*case R.id.navigation_daily:
                    startActivity(new Intent(getApplicationContext(),
                            Daily.class));
                    overridePendingTransition(0,0);
                    return true;*/
                case R.id.navigation_user:
                    startActivity(new Intent(getApplicationContext(),
                            User.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        WeeklyScheduleInitialization();
    }

    void WeeklyScheduleInitialization(){
        sharedPreferences = getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
        int complexId = sharedPreferences.getInt("complexId", 0),
            groupId = sharedPreferences.getInt("group",0);

        scheduleModels = new Parser().getScheduleWeb(groupId,complexId);
        weeklyScheduleAdapter = new WeeklyPagerScheduleAdapter(scheduleModels);
        activityWeeklyBinding.WeeklyViewPager.setAdapter(weeklyScheduleAdapter);
    }
}