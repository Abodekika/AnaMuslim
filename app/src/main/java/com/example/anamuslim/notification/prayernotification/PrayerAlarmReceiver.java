package com.example.anamuslim.notification.prayernotification;

import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;


import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


import com.example.anamuslim.sharedpreferences.PrayerPreferences;

import java.util.concurrent.TimeUnit;

public class PrayerAlarmReceiver extends BroadcastReceiver {

    private String prayingName;
    String isActive;
    private static final String TAG = "PrayerAlarmReceiver";
    PrayerPreferences prayerPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {

        prayerPreferences = new PrayerPreferences(context);
        Bundle b = intent.getExtras();
        prayingName = intent.getStringExtra("prayName");


        Log.d(TAG, "AlarmReceiver" + isActive + prayingName);
        Intent intentService = new Intent(context, PrayerNotification.class);

        intentService.putExtra("prayName", b.getString("prayName"));


        if (prayingName.equals("0")) {
            PeriodicWorkRequest periodicWorkRequest =
                    new PeriodicWorkRequest.Builder(RegisterPrayer.class,
                            24, TimeUnit.HOURS).addTag("Register").build();
            WorkManager.getInstance(context)
                    .enqueueUniquePeriodicWork("Register", ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, periodicWorkRequest);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                context.startForegroundService(intentService);
            } else {
                context.startService(intentService);
            }


        }


    }


//          if (prayingName.equals("6") && prayerPreferences.getNotificationPrayer1()
//                  || prayingName.equals("6") && prayerPreferences.getNotificationPrayer2()
//                  || prayingName.equals("6") && prayerPreferences.getNotificationPrayer3()
//                  || prayingName.equals("6") && prayerPreferences.getNotificationPrayer4()
//                  || prayingName.equals("6") && prayerPreferences.getNotificationPrayer5()
//                  ) {
//        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.
//                Builder(RegisterParyer.class, 24, TimeUnit.HOURS)
//                .addTag("Res")
//                .build();
//        WorkManager.getInstance(context).
//                enqueueUniquePeriodicWork("Res",
//                        ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest);
//    } else {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            context.startForegroundService(intentService);
//
//        } else {
//            context.startService(intentService);
//        }
//
//
//    }

}
