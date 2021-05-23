package com.example.schedulechenk.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schedulechenk.fragments.Weekly;

public class SplashActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "check_launch";
    SharedPreferences sp;
    SharedPreferences sharedPreferences;
    private static final String PREFERENCES_NAME_CHOICE = "user_choice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //создаем канал уведомлений
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Расписание";
            String description = "Информация об изменении в расписании";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("Chenk",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        sharedPreferences = getSharedPreferences(PREFERENCES_NAME_CHOICE,MODE_PRIVATE);
        int complexId = sharedPreferences.getInt("complexId", 0),
            groupId = sharedPreferences.getInt("group",0);

        if(isLaunching()){
            if(complexId != 0 && groupId !=0){
                Intent intent = new Intent(this, Weekly.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(this, ChoiceActivity.class);
                startActivity(intent);
            }

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