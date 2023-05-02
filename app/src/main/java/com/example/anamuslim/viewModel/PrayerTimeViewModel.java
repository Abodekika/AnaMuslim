package com.example.anamuslim.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.anamuslim.prayertimecalculation.PrayerTimes;
import com.example.anamuslim.sharedpreferences.LocationPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class PrayerTimeViewModel extends ViewModel {
    LocationPreferences preferences;
    private final MutableLiveData<String> mText;
    private final MutableLiveData<Date[]> prayerTime;

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf_today = new SimpleDateFormat("EE");
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf_date = new SimpleDateFormat("dd MMMM yyyy");

    public PrayerTimeViewModel() {
        mText = new MutableLiveData<>();
        prayerTime = new MutableLiveData<>();

        mText.setValue("This is gallery fragment");


    }


    public LiveData<String> getText() {
        return mText;
    }

    public void setf(Context context) {
        preferences = new LocationPreferences(context);
        setPrayerTime();

    }

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = (calendar.get(Calendar.MONTH)) + 1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int dst = calendar.getTimeZone().getDSTSavings();
    int timeZone = calendar.getTimeZone().getRawOffset() / (1000 * 60 * 60);

    public String today = "اليوم / " + sdf_today.format(calendar.getTime());
    public String date = sdf_date.format(calendar.getTime());

    public void setPrayerTime() {

        Date[] prayers = new PrayerTimes(day, month, year, getLatitude(), getLongitude(), timeZone, (dst > 0), PrayerTimes.getDefaultMazhab("EG"), PrayerTimes.getDefaultWay("EG")).get();

        prayerTime.setValue(prayers);

    }



    public String getCountry() {
        System.out.println(preferences.getCountryCode_KEY());
        return preferences.getCountryCode_KEY();

    }

    double getLatitude() {
        return Double.parseDouble(preferences.getLatitude());

    }

    double getLongitude() {
        return Double.parseDouble(preferences.getLongitude());

    }

    public MutableLiveData<Date[]> getPrayerTime() {
        return prayerTime;
    }

    public double getTimeZone() {


        Calendar mCalendar = new GregorianCalendar();
        TimeZone mTimeZone = mCalendar.getTimeZone();
        int mGMTOffset = mTimeZone.getRawOffset();
        Double timeZone = (double) TimeUnit.HOURS.convert(mGMTOffset, TimeUnit.MILLISECONDS);

        return timeZone;
    }

    public PrayerTimes getPrayerForPreviousDay() {


        return new PrayerTimes(day - 1, month, year, getLatitude(), getLongitude(),timeZone, (dst > 0), PrayerTimes.getDefaultMazhab("EG"), PrayerTimes.getDefaultWay("EG"));

    }

    public PrayerTimes getPrayerForNextDay() {


        return new PrayerTimes(day + 1, month, year, getLatitude(), getLongitude(), timeZone, (dst > 0), PrayerTimes.getDefaultMazhab("EG"), PrayerTimes.getDefaultWay("EG"));
    }

}