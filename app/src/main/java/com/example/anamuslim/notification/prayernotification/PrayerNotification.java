package com.example.anamuslim.notification.prayernotification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.anamuslim.MainActivity;

import com.example.anamuslim.R;
import com.example.anamuslim.prayertimecalculation.calendar.HGDate;
import com.example.anamuslim.sharedpreferences.PrayerPreferences;


public class PrayerNotification extends Service {

    private String prayingName, prayerType;
    MediaPlayer mediaPlayer;
    String isActive;
    public static final String CHANNEL_ID = "Ahmed.Prayer_CHANNEL";
    public static final String CHANNEL_NAme = "Ahmed.Prayer_Notification_CHANNEL";

    private static final String TAG = "PrayerNotification";

    @Override
    public void onCreate() {
        super.onCreate();

        createMedia();
        MyMediaPlayer.getInstanceMediaPlayer();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        prayingName = intent.getStringExtra("prayName");


        Log.d(TAG, "onStartCommand:" + isActive + prayingName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            showNotification();
        }
//01117092005
        onComplete();

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 200, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        switch (prayingName) {
            case "0":
                prayingName = this.getString(R.string.fajr_prayer);
                //   prayerType = PrayerImageActivity.MOSQUE_NIGHT;
                //   MindtrackLog.add(prayingName);
                break;
            case "1":
                prayingName = this.getString(R.string.sunrize_prayer);
                //  prayerType = PrayerImageActivity.MOSQUE_DAY;
                // MindtrackLog.add(prayingName);
                break;
            case "2":
                HGDate hgDate = new HGDate();
                prayingName = hgDate.weekDay() != 5 ? this.getString(R.string.zuhr_prayer) : this.getString(R.string.jomma_prayer);

                //  prayerType = PrayerImageActivity.MOSQUE_DAY;
                //  MindtrackLog.add(prayingName);
                break;
            case "3":
                prayingName = this.getString(R.string.asr_prayer);
                //  prayerType = PrayerImageActivity.MOSQUE_DAY;
                //  MindtrackLog.add(prayingName);
                break;
            case "4":
                prayingName = this.getString(R.string.magreb_prayer);
                // prayerType = PrayerImageActivity.MOSQUE_NIGHT;
                //  MindtrackLog.add(prayingName);
                break;
            case "5":
                prayingName = this.getString(R.string.asha_prayer);
                // prayerType = PrayerImageActivity.MOSQUE_NIGHT;
                // MindtrackLog.add(prayingName);
                break;
            case "6":
                prayingName = this.getString(R.string.mid_night);
                //MindtrackLog.add(prayingName);
                break;

        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        Notification builder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(prayingName).setContentText("Please! Wake Up").setContentIntent(pendingIntent).setSound(null).setCategory(NotificationCompat.CATEGORY_ALARM).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).setPriority(NotificationCompat.PRIORITY_MAX).setFullScreenIntent(pendingIntent, true).build();
///
        NotificationChannel channel;
        channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAme, NotificationManager.IMPORTANCE_HIGH);

        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);

        }
//
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//        notificationManagerCompat.notify(123, builder);

        startForeground(1, builder);
        play();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
        stopSelf();

    }


    private void createMedia() {


        MyMediaPlayer.instanceMediaPlayer = MediaPlayer.create(this, R.raw.azannnn);
        MyMediaPlayer.instanceMediaPlayer.setLooping(false);
    }


    private void play() {

        MyMediaPlayer.instanceMediaPlayer.start();

    }


    private void stop() {

        MyMediaPlayer.instanceMediaPlayer.stop();
        MyMediaPlayer.instanceMediaPlayer.release();
    }


    private void onComplete() {


        MyMediaPlayer.instanceMediaPlayer.setOnCompletionListener(mp -> {

            stopSelf();


        });
//        Log.d(TAG, "onCompletion: خلص خلاص خلاص ");
//        mediaPlayer.reset();


    }


}

