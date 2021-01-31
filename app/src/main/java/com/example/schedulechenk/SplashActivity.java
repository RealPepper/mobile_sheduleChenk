package com.example.schedulechenk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    CheckAuthorization checkAuthorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkAuthorization = new CheckAuthorization();

        if(!checkAuthorization.checkingUserAuthorization(SplashActivity.this))
        {
            Intent openAuthActivity = new Intent(this, EnterActivity.class);
            startActivity(openAuthActivity);
        }
        else {
            Intent openActivity = new Intent(this,Daily.class);
            startActivity(openActivity);
        }

        finish();
    }

}