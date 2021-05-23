package com.example.schedulechenk.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.schedulechenk.NotificationService.NotificationReceiver;
import com.example.schedulechenk.R;
import com.example.schedulechenk.adapters.WeeklyPagerScheduleAdapter;
import com.example.schedulechenk.databinding.ActivityWeeklyBinding;
import com.example.schedulechenk.models.PairModel;
import com.example.schedulechenk.models.ScheduleModel;
import com.example.schedulechenk.parser.Parser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Weekly extends AppCompatActivity {

    private WeeklyPagerScheduleAdapter weeklyScheduleAdapter;
    private ActivityWeeklyBinding activityWeeklyBinding;
    public List<ScheduleModel> scheduleModels;

    private static final String PREFERENCES_NAME = "user_choice";
    private SharedPreferences sharedPreferences;

    private AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        activityWeeklyBinding = DataBindingUtil.setContentView(this, R.layout.activity_weekly);

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

        int currentWeeklyDay = Integer.parseInt(new SimpleDateFormat("u").format(new Date()));

        if (currentWeeklyDay != 7){
            activityWeeklyBinding.WeeklyViewPager.setCurrentItem(currentWeeklyDay-1);
        }
        writeFile();
        restartNotify();
    }
    void writeFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("schedule", MODE_PRIVATE)));
            for(ScheduleModel day:scheduleModels){
                for(PairModel pair:day.getPairModels())
                bw.write(pair.getPair()+pair.getStartTime()+pair.getEndTime()+pair.getIsCancel()+pair.getEducator()+pair.getDiscipline()+pair.getCabinet());

            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void restartNotify() {
        Toast.makeText(Weekly.this, "Create alarm", Toast.LENGTH_SHORT).show();
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT );
        am.cancel(pendingIntent);
        am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000, pendingIntent);
    }
}