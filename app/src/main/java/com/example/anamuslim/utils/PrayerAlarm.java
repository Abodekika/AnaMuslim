package com.example.anamuslim.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.anamuslim.notification.prayernotification.PrayerAlarmReceiver;


import java.util.Calendar;

public class PrayerAlarm {

    private static final String TAG = "PrayerAlarm";

    @RequiresApi(api = Build.VERSION_CODES.S)
    public static void setNotificationAlarm(Context context, int hour, int min, int id, String extra, String isActive) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Bundle details = new Bundle();
        details.putString("prayName", extra);
        Intent alarmReceiver = new Intent(context, PrayerAlarmReceiver.class);
        alarmReceiver.putExtras(details);
        Log.d(TAG, "PrayingAlarm" + extra);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, alarmReceiver, PendingIntent.FLAG_MUTABLE);
        if (isActive.equals("1"))
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public static void setNotificationAlarmTest(Context context, int hour, int min, int id, String extra, String isActive) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        // Bundle details = new Bundle();
        // details.putString("prayName", extra);
        //  details.putString("isActive", isActive);

        Intent alarmReceiver = new Intent(context, PrayerAlarmReceiver.class);
        // alarmReceiver.putExtras(details);

        alarmReceiver.putExtra("prayName", extra);
        alarmReceiver.putExtra("isActive", isActive);
        Log.d(TAG, "PrayingAlarm" + isActive);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 200, alarmReceiver, PendingIntent.FLAG_MUTABLE);

        if (isActive.equals("1"))
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 500, pendingIntent);
    }
}
