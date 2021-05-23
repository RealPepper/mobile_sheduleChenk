package com.example.schedulechenk.NotificationService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.schedulechenk.R;
import com.example.schedulechenk.fragments.Weekly;
import com.example.schedulechenk.models.PairModel;
import com.example.schedulechenk.models.ScheduleModel;
import com.example.schedulechenk.parser.Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class NotificationService extends Service {

    private SharedPreferences sharedPreferences;
    private static final String PREFERENCES_NAME = "user_choice";
    private List<ScheduleModel> currentSchedule;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Create service", Toast.LENGTH_SHORT).show();
        sharedPreferences = this.getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
        int complexId = sharedPreferences.getInt("complexId", 0),
                groupId = sharedPreferences.getInt("group",0);

        currentSchedule = new Parser().getScheduleWeb(groupId,complexId);
        if(!scheduleIsEquals(this,currentSchedule)){
            createNotification();
            rewriteFile(this,currentSchedule);
        }

        reCreateAlarmManager(this);
        return START_STICKY;
    }
    private Boolean scheduleIsEquals(Context context,List<ScheduleModel> currentScheduleModels){
        String oldSchedule = "";
        String currentSchedule="";

        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput("schedule")));
            // читаем содержимое
            while ((oldSchedule = br.readLine()) != null) {

                for(ScheduleModel day:currentScheduleModels){
                    for(PairModel pair:day.getPairModels())
                        currentSchedule += (pair.getPair()+pair.getStartTime()+pair.getEndTime()+pair.getIsCancel()+pair.getEducator()+pair.getDiscipline()+pair.getCabinet());
                }
                return currentSchedule.equals(oldSchedule);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    private void rewriteFile(Context context,List<ScheduleModel> scheduleModels) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput("schedule", MODE_PRIVATE)));
            for(ScheduleModel day:scheduleModels){
                for(PairModel pair:day.getPairModels())
                    bw.write(pair.getPair()+pair.getStartTime()+pair.getEndTime()+pair.getIsCancel()+pair.getEducator()+pair.getDiscipline()+pair.getCabinet());

            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNotification(){
        //канал уведомлений создается в SplashActivity.Class
        Intent intentTL = new Intent(this, Weekly.class);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "Chenk")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Расписание изменилось")
                        .setContentText("Расписание было изменено")
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(PendingIntent.getActivity(this, 0, intentTL,
                                PendingIntent.FLAG_CANCEL_CURRENT))
                        .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,builder.build());
    }

    private void reCreateAlarmManager(Context context){
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent1 = new Intent(context, NotificationService.class);
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            pendingIntent = PendingIntent.getForegroundService(this, 0,
                    intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        }else {
            pendingIntent = PendingIntent.getService(this, 0,
                    intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        }
        am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+3600000, pendingIntent); //3600000 = час
    }
}
