package com.example.schedulechenk.NitificationService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

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

import static android.content.Context.MODE_PRIVATE;

public class ChangeScheduleNotification extends BroadcastReceiver {
    private SharedPreferences sharedPreferences;
    private static final String PREFERENCES_NAME = "user_choice";

    private List<ScheduleModel> currentSchedule;

    private Intent intentTL;



    @Override
    public void onReceive(Context context, Intent intent) {
       //Интент для активити, которую мы хотим запускать при нажатии на уведомление
        intentTL = new Intent(context, Weekly.class);

        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
        int complexId = sharedPreferences.getInt("complexId", 0),
                groupId = sharedPreferences.getInt("group",0);

        currentSchedule = new Parser().getScheduleWeb(groupId,complexId);

        if(!scheduleIsEquals(context,currentSchedule)){
            createNotification(context, intent);
            rewriteFile(context,currentSchedule);
        }

        // Установим следующее напоминание.
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + /*7200*1000*/5000, pendingIntent);
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
    void rewriteFile(Context context,List<ScheduleModel> scheduleModels) {
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
    private void createNotification(Context context,Intent intent){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, "Chenk")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Расписание изменилось")
                        .setContentText("Расписание на  изменилось")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(PendingIntent.getActivity(context, 0, intentTL,
                                PendingIntent.FLAG_CANCEL_CURRENT))
                        .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1,builder.build());

    }
}