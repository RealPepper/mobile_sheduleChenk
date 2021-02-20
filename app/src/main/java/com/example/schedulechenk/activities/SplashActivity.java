package com.example.schedulechenk.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.schedulechenk.activities.GuideActivity;
import com.example.schedulechenk.models.ComplexModel;
import com.example.schedulechenk.parser.Parser;

import java.io.Serializable;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "check_launch";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isLaunching()){
            Intent intent = new Intent(this, ChoiceActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            firstLaunch();
        }
        finish();
    }
    //если фалс, то приложение не запускалось
    private void firstLaunch() {
        sp = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        boolean isLaunch = sp.getBoolean("isLaunch", false);

        if (!isLaunch) {
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("isLaunch", true);
            e.apply();
        }
    }
    private boolean isLaunching(){
        sp = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        return sp.getBoolean("isLaunch", false);
    }

}