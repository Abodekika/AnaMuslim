package com.example.anamuslim.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PrayerPreferences {

    private final SharedPreferences preferences;
    private static final String FILE_NAME = "PRAYER_NOTIFICATION_PREF1";
    private static final String NotificationPrayer1 = "Notification_Prayer1_PREF";
    private static final String NotificationPrayer2 = "Notification_Prayer2_PREF";
    private static final String NotificationPrayer3 = "Notification_Prayer3_PREF";
    private static final String NotificationPrayer4 = "Notification_Prayer4_PREF";
    private static final String NotificationPrayer5 = "Notification_Prayer5_PREF";
    private static final String NotificationPrayer6 = "Notification_Prayer6_PREF";
    private static final String NotificationPrayer7 = "Notification_Prayer7_PREF";


    public PrayerPreferences(Context context) {

        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }


    //prayer 1

    public void setNotificationPrayer1(String value) {
        preferences.edit().putString(NotificationPrayer1, value).apply();
    }

    public String getNotificationPrayer1() {
        return preferences.getString(NotificationPrayer1, "1");
    }

    //prayer 2
    public void setNotificationPrayer2(String value) {
        preferences.edit().putString(NotificationPrayer2, value).apply();
    }


    public String getNotificationPrayer2() {
        return preferences.getString(NotificationPrayer2, "2");
    }

    //prayer 3
    public void setNotificationPrayer3(String value) {
        preferences.edit().putString(NotificationPrayer3, value).apply();
    }


    public String getNotificationPrayer3() {
        return preferences.getString(NotificationPrayer3, "3");
    }

    //prayer 4
    public void setNotificationPrayer4 (String value) {
        preferences.edit().putString(NotificationPrayer4, value).apply();
    }


    public String getNotificationPrayer4() {
        return preferences.getString(NotificationPrayer4, "4");
    }

    //prayer 5
    public void setNotificationPrayer5(String value) {
        preferences.edit().putString(NotificationPrayer5, value).apply();
    }


    public String getNotificationPrayer5() {
        return preferences.getString(NotificationPrayer5, "5");
    }

    //prayer 6
    public void setNotificationPrayer6(String value) {
        preferences.edit().putString(NotificationPrayer6, value).apply();
    }


    public String getNotificationPrayer6() {
        return preferences.getString(NotificationPrayer6, "6");
    }

    //prayer 7
    public void setNotificationPrayer7(String value) {
        preferences.edit().putString(NotificationPrayer7, value).apply();
    }


    public String getNotificationPrayer7() {
        return preferences.getString(NotificationPrayer7, "1");
    }
}
