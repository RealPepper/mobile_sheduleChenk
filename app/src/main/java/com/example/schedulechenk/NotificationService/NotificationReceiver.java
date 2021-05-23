package com.example.schedulechenk.NotificationService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        Intent service = new Intent(context, NotificationService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(service);
        }else {
            context.startService(service);
        }
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = null;
            pendingIntent = PendingIntent.getService(context, 0,
                    service, PendingIntent.FLAG_CANCEL_CURRENT);

        am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+3600000, pendingIntent); //3600000 = 1 час
    }
}
