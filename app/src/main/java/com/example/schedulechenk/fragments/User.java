package com.example.schedulechenk.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.schedulechenk.R;
import com.example.schedulechenk.activities.ChoiceActivity;
import com.example.schedulechenk.activities.GuideActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class User extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String PREFERENCES_NAME = "user_choice";
    private Button repeatChoiceSettings,
                   showGuideText;
    private TextView groupAndCorpusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        groupAndCorpusText = findViewById(R.id.groupAndCorpusSettings);
        showGuideText = findViewById(R.id.showGuideSettings);
        repeatChoiceSettings = findViewById(R.id.repeatChoiceSettings);

        sharedPreferences = getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);

        String group = sharedPreferences.getString("groupName",""),
               complex = sharedPreferences.getString("complexName","");
        String groupAndComplex = complex + ", группа " + group;

        groupAndCorpusText.setText(groupAndComplex);


        showGuideText.setOnClickListener(v -> {
            Intent intentGuide = new Intent(getApplicationContext(), GuideActivity.class);
            intentGuide.putExtra("isRepeat", true);
            startActivity(intentGuide);
        });
        repeatChoiceSettings.setOnClickListener(v -> {
            Intent intentChoice = new Intent(getApplicationContext(), ChoiceActivity.class);
            startActivity(intentChoice);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_user);
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
                    /*case R.id.navigation_daily:
                        startActivity(new Intent(getApplicationContext(),
                                Daily.class));
                        overridePendingTransition(0,0);
                        return true;*/
                    case R.id.navigation_user:

                        return true;
                }
                return false;
            }
        });
    }
}