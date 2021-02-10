package com.example.schedulechenk.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.schedulechenk.activities.GuideActivity;

public class SplashActivity extends AppCompatActivity {

    //CheckAuthorization checkAuthorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* checkAuthorization = new CheckAuthorization();

        if(!checkAuthorization.checkingUserAuthorization(SplashActivity.this))
        {
            Intent openAuthActivity = new Intent(this, EnterActivity.class);
            startActivity(openAuthActivity);
        }
        else {
            Intent openActivity = new Intent(this,Daily.class);
            startActivity(openActivity);
        }*/
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

}