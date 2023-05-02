package com.example.anamuslim.notification.prayernotification;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


import com.example.anamuslim.prayertimecalculation.Calculators;
import com.example.anamuslim.prayertimecalculation.PrayerTimeCalculator;
import com.example.anamuslim.prayertimecalculation.PrayerTimes;
import com.example.anamuslim.sharedpreferences.LocationPreferences;
import com.example.anamuslim.sharedpreferences.PrayerPreferences;
import com.example.anamuslim.utils.Converter;
import com.example.anamuslim.utils.PrayerAlarm;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class RegisterPrayer extends Worker {

    LocationPreferences preferences;
    Calendar calendar;
    String prayer1, prayer2, prayer3, prayer4, prayer5, prayer6, prayer7;
    String[] isActive = new String[7];

    private static final int PRAYER_SIG = 110;
    private static final String TAG = "RegisterPrayer";

    public RegisterPrayer(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);


    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    @NonNull
    @Override
    public Result doWork() {
        preferences = new LocationPreferences(getApplicationContext());
        calendar = Calendar.getInstance();

        PrayerPreferences prayerPreferences = new PrayerPreferences(getApplicationContext());
        prayer1 = prayerPreferences.getNotificationPrayer1();
        prayer2 = prayerPreferences.getNotificationPrayer2();
        prayer3 = prayerPreferences.getNotificationPrayer3();
        prayer4 = prayerPreferences.getNotificationPrayer4();
        prayer5 = prayerPreferences.getNotificationPrayer5();
        prayer6 = prayerPreferences.getNotificationPrayer6();
        prayer7 = prayerPreferences.getNotificationPrayer7();

        isActive[0] = prayer1;
        isActive[1] = prayer2;
        isActive[2] = prayer3;
        isActive[3] = prayer4;
        isActive[4] = prayer5;
        isActive[5] = prayer6;
        isActive[6] = prayer7;

        register_prayer_notification();


        return Result.success();
    }


    private void register_prayer_notification() {

        SimpleDateFormat nsdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] normaldate = nsdf.format(new Date().getTime()).split("-");
        double latitude = Double.parseDouble(preferences.getLatitude());
        double longitud = Double.parseDouble(preferences.getLongitude());
        String country = preferences.getCountryCode_KEY();
        int dst = calendar.getTimeZone().getDSTSavings();

        double[] prayers = new PrayerTimeCalculator(Integer.parseInt(Converter.convertNumberType(getApplicationContext(), normaldate[2].trim())), Integer.parseInt(Converter.convertNumberType(getApplicationContext(), normaldate[1].trim())), Integer.parseInt(Converter.convertNumberType(getApplicationContext(), normaldate[0].trim())), latitude, longitud, getTimeZone(), PrayerTimes.getDefaultMazhab(country), PrayerTimes.getDefaultWay(country), dst, getApplicationContext()).calculateDailyPrayers_withSunset();


        Calendar c = Calendar.getInstance();
        int hourNow = c.get(Calendar.HOUR_OF_DAY);
        int minsNow = c.get(Calendar.MINUTE);

        int counter = 0;


        for (double pray : prayers) {
            counter++;
            if (hourNow < Calculators.extractHour(pray)) {
                break;
            } else {
                if (hourNow == Calculators.extractHour(pray)) {
                    if (minsNow < Calculators.extractMinutes(pray)) {
                        break;
                    }
                }
            }
            Log.d(TAG, "prayers" + Calculators.extractHour(pray));


        }
        for (int i = (counter - 1); i < prayers.length; i++) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

                PrayerAlarm.setNotificationAlarm(getApplicationContext(), Calculators.extractHour(prayers[i]), Calculators.extractMinutes(prayers[i]), PRAYER_SIG + i, i + "", isActive[i]);
            }


            Log.d(TAG, "prayer" + i + "/ " + isActive[i]);

        }

    }


    public double getTimeZone() {


        Calendar mCalendar = new GregorianCalendar();
        TimeZone mTimeZone = mCalendar.getTimeZone();
        int mGMTOffset = mTimeZone.getRawOffset();

        return (double) TimeUnit.HOURS.convert(mGMTOffset, TimeUnit.MILLISECONDS);
    }


    @Override
    public void onStopped() {
        super.onStopped();


    }

    public void init() {


    }
}
