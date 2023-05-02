package com.example.anamuslim.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import com.example.anamuslim.R;
import com.example.anamuslim.databinding.FragmentPrayerTimeBinding;
import com.example.anamuslim.sharedpreferences.PrayerPreferences;
import com.example.anamuslim.viewModel.PrayerTimeViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import java.util.Timer;
import java.util.TimerTask;


public class PrayerTimeFragment extends Fragment {

    private FragmentPrayerTimeBinding binding;


    PrayerTimeViewModel prayerTimeViewModel;

    TextView Fajr, FajrTime, sunRise, sunRiseTime, Dhuhr, DhuhrTime, Asr, AsrTime, tv_nextPrayer, Maghrib, MaghribTime, Isha, IshaTime, tv_date, tv_rest_time, tv_day, current_prayer_name;

    Date fajrDate, sunriseDate, duhrDate, asrDate, maghrebDate, ishaDate, midNightDate;
    ImageView notification_image_prayer1, notification_image_prayer2, notification_image_prayer3, notification_image_prayer4, notification_image_prayer5, notification_image_prayer6;

    private LinearLayout pray1, pray2, pray3, pray4, pray5, pray6;
    PrayerPreferences prayerPreferences;
    String nextPray = "";
    String currentPray = "";
    Date nextDate, lastDate;
    Calendar endCal = Calendar.getInstance(), startCal = Calendar.getInstance(), currCal = Calendar.getInstance();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        prayerTimeViewModel = new ViewModelProvider(requireActivity()).get(PrayerTimeViewModel.class);

        binding = FragmentPrayerTimeBinding.inflate(inflater, container, false);
        prayerPreferences = new PrayerPreferences(requireContext());
        bind();
        prayerTimeViewModel.getPrayerTime().observe(getViewLifecycleOwner(), this::updateViews);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_day.setText(prayerTimeViewModel.today);
        tv_date.setText(prayerTimeViewModel.date);
        setNotificationImage();
        checkNotification();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void bind() {
        Fajr = binding.Fajr;
        FajrTime = binding.FajrTime;

        sunRise = binding.sunRise;
        sunRiseTime = binding.sunRiseTime;


        Dhuhr = binding.dhuhr;
        DhuhrTime = binding.dhuhrTime;

        Asr = binding.Asr;
        AsrTime = binding.AsrTime;

        Maghrib = binding.maghrib;
        MaghribTime = binding.maghribTime;

        Isha = binding.Isha;
        IshaTime = binding.IshaTime;

        tv_date = binding.tvDate;
        tv_day = binding.prayerDayTextview;

        tv_nextPrayer = binding.tvNextPrayer;

        tv_rest_time = binding.tvRestTime;
        current_prayer_name = binding.currentPrayerName;

        pray1 = binding.pray1;
        pray2 = binding.pray2;
        pray3 = binding.pray3;
        pray4 = binding.pray4;
        pray5 = binding.pray5;
        pray6 = binding.pray6;

        notification_image_prayer1 = binding.notificationImagePrayer1;

        notification_image_prayer3 = binding.notificationImagePrayer3;
        notification_image_prayer4 = binding.notificationImagePrayer4;
        notification_image_prayer5 = binding.notificationImagePrayer5;
        notification_image_prayer6 = binding.notificationImagePrayer6;

        prayerTimeViewModel.setf(getContext());
        //  pray6 = (RelativeLayout) rootView.findViewById(R.id.p6);


        //   countdown = binding.countdown;
        //  prayerName = binding.prayerName;


    }


    private void updateViews(Date[] dates) {

        Calendar mid = Calendar.getInstance();
        mid.set(Calendar.HOUR_OF_DAY, 0);
        mid.set(Calendar.MINUTE, 0);
        mid.set(Calendar.SECOND, 0);
        midNightDate = mid.getTime();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a", Locale.getDefault());


        fajrDate = dates[0];
        FajrTime.setText(format.format(fajrDate));

        sunriseDate = dates[1];
        sunRiseTime.setText(format.format(sunriseDate));

        duhrDate = dates[2];
        DhuhrTime.setText(format.format(duhrDate));

        asrDate = dates[3];
        AsrTime.setText(format.format(asrDate));

        maghrebDate = dates[4];
        MaghribTime.setText(format.format(maghrebDate));

        ishaDate = dates[5];
        IshaTime.setText(format.format(ishaDate));


        checkActiveView();

    }

    private void checkActiveView() {
        new SimpleDateFormat("hh:mm a", Locale.getDefault());
        if (fajrDate == null || sunriseDate == null || duhrDate == null || asrDate == null || maghrebDate == null || ishaDate == null)
            return;
        removeActiveViews();
        Date current = Calendar.getInstance().getTime();


        if (current.after(fajrDate) && current.before(sunriseDate)) {
            pray2.setBackgroundColor(Color.argb(255, 73, 138, 127));
            nextPray = getString(R.string.sun_rise);
            lastDate = fajrDate;
            nextDate = sunriseDate;

        }
        if (current.after(sunriseDate) && current.before(duhrDate)) {
            pray3.setBackgroundColor(Color.argb(255, 73, 138, 127));
            nextPray = getString(R.string.zuhr);
            currentPray = getString(R.string.sun_rise);
            lastDate = sunriseDate;
            nextDate = duhrDate;
        } else if (current.after(duhrDate) && current.before(asrDate)) {
            pray4.setBackgroundColor(Color.argb(255, 220, 224, 247));
            nextPray = getString(R.string.asr);
            currentPray = getString(R.string.zuhr);
            lastDate = duhrDate;
            nextDate = asrDate;
        } else if (current.after(asrDate) && current.before(maghrebDate)) {
            pray5.setBackgroundColor(Color.argb(255, 73, 138, 127));
            nextPray = getString(R.string.magrib);
            currentPray = getString(R.string.asr);
            lastDate = asrDate;
            nextDate = maghrebDate;
        } else if (current.after(maghrebDate) && current.before(ishaDate)) {
            pray6.setBackgroundColor(Color.argb(255, 73, 138, 127));
            nextPray = getString(R.string.isha);
            currentPray = getString(R.string.magrib);
            lastDate = maghrebDate;
            nextDate = ishaDate;
        } else {

            if (current.after(midNightDate) && current.before(fajrDate)) {
                lastDate = prayerTimeViewModel.getPrayerForPreviousDay().get()[5];
                nextDate = fajrDate;
            } else {

                lastDate = ishaDate;
                nextDate = prayerTimeViewModel.getPrayerForNextDay().get()[0];
            }
            pray1.setBackgroundColor(Color.argb(255, 73, 138, 127));
            nextPray = getString(R.string.fajr);
            currentPray = getString(R.string.isha);

        }

        tv_nextPrayer.setText(nextPray);
        current_prayer_name.setText(currentPray);

//        Log.i("DATE_TAg" ,"last : "+format.format(lastDate));
//        Log.i("DATE_TAg" ,"current : "+format.format(current));
//        Log.i("DATE_TAg" ,"end : "+format.format(nextDate));


        updateTimer(current);

    }


    private void removeActiveViews() {
        pray1.setBackgroundColor(Color.TRANSPARENT);
        pray2.setBackgroundColor(Color.TRANSPARENT);
        pray3.setBackgroundColor(Color.TRANSPARENT);
        pray4.setBackgroundColor(Color.TRANSPARENT);
        pray5.setBackgroundColor(Color.TRANSPARENT);
        pray6.setBackgroundColor(Color.TRANSPARENT);
    }

    private void updateTimer(Date current) {
        endCal.setTime(nextDate);
        startCal.setTime(lastDate);
        currCal.setTime(current);


        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                final long timeRemaining = endCal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();

                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        if (timeRemaining <= 0) {
                            checkActiveView();
                            return;
                        }
                        tv_rest_time.setText(getDate(timeRemaining));
                    });
                }
            }
        }, 0, 1000);
    }

    @SuppressLint("DefaultLocale")
    private static String getDate(Long l) {

        long totalSeconds = l / 1000;
        long currentSeconds = totalSeconds % 60;

        long totalMinutes = totalSeconds / 60;

        long currentMinute = totalMinutes % 60;

        long totalHours = totalMinutes / 60;


        long currentHour = totalHours % 24;

        String s = currentHour + ":" + currentMinute + ":" + currentSeconds;
        //  return String.format("%d ساعة و %d دقيقة ", currentHour, currentMinute);

        return s;
    }

    private void checkNotification() {
        notification_image_prayer1.setOnClickListener(v -> {
            if (prayerPreferences.getNotificationPrayer7().equals("0")) {

                prayerPreferences.setNotificationPrayer7("1");


                notification_image_prayer1.setImageResource(R.drawable.ic_notifications_24);
                Toast.makeText(getContext(), "تمت تشغيل تنبية الفجر", Toast.LENGTH_SHORT).show();


            } else {

                prayerPreferences.setNotificationPrayer7("0");

                notification_image_prayer1.setImageResource(R.drawable.ic_notifications_off_24);

                Toast.makeText(getContext(), "تم ايقاف  تنبية الفجر", Toast.LENGTH_SHORT).show();
            }

        });


        notification_image_prayer3.setOnClickListener(v -> {
            if (prayerPreferences.getNotificationPrayer3().equals("0")) {

                prayerPreferences.setNotificationPrayer3("1");


                notification_image_prayer3.setImageResource(R.drawable.ic_notifications_24);
                Toast.makeText(getContext(), "تمت تشغيل تنبيةالظهر ", Toast.LENGTH_SHORT).show();


            } else {

                prayerPreferences.setNotificationPrayer3("0");

                notification_image_prayer3.setImageResource(R.drawable.ic_notifications_off_24);

                Toast.makeText(getContext(), "تم ايقاف  تنبيةالظهر", Toast.LENGTH_SHORT).show();
            }

        });

        notification_image_prayer4.setOnClickListener(v -> {
            if (prayerPreferences.getNotificationPrayer4().equals("0")) {

                prayerPreferences.setNotificationPrayer4("1");


                notification_image_prayer4.setImageResource(R.drawable.ic_notifications_24);
                Toast.makeText(getContext(), "تمت تشغيل تنبيةالعصر", Toast.LENGTH_SHORT).show();


            } else {

                prayerPreferences.setNotificationPrayer4("0");

                notification_image_prayer4.setImageResource(R.drawable.ic_notifications_off_24);

                Toast.makeText(getContext(), "تم ايقاف تنبيةالعصر", Toast.LENGTH_SHORT).show();
            }

        });

        notification_image_prayer5.setOnClickListener(v -> {
            if (prayerPreferences.getNotificationPrayer5().equals("0")) {

                prayerPreferences.setNotificationPrayer5("1");


                notification_image_prayer5.setImageResource(R.drawable.ic_notifications_24);
                Toast.makeText(getContext(), "تمت تشغيل تنبيةالمغرب", Toast.LENGTH_SHORT).show();


            } else {

                prayerPreferences.setNotificationPrayer5("0");

                notification_image_prayer5.setImageResource(R.drawable.ic_notifications_off_24);

                Toast.makeText(getContext(), "تم ايقاف  تنبيةالمغرب", Toast.LENGTH_SHORT).show();
            }

        });
        notification_image_prayer6.setOnClickListener(v -> {
            if (prayerPreferences.getNotificationPrayer6().equals("0")) {

                prayerPreferences.setNotificationPrayer6("1");


                notification_image_prayer6.setImageResource(R.drawable.ic_notifications_24);
                Toast.makeText(getContext(), "تمت تشغيل تنبيةالعشاء", Toast.LENGTH_SHORT).show();


            } else {

                prayerPreferences.setNotificationPrayer6("0");

                notification_image_prayer6.setImageResource(R.drawable.ic_notifications_off_24);

                Toast.makeText(getContext(), "تم ايقاف تنبيةالعشاء", Toast.LENGTH_SHORT).show();
            }

        });


    }

    private void setNotificationImage() {
        if (prayerPreferences.getNotificationPrayer7().equals("0")

        ) {

            notification_image_prayer1.setImageResource(R.drawable.ic_notifications_off_24);

        } else {
            notification_image_prayer1.setImageResource(R.drawable.ic_notifications_24);

        }


        if (prayerPreferences.getNotificationPrayer3().equals("0")) {

            notification_image_prayer3.setImageResource(R.drawable.ic_notifications_off_24);

        } else {
            notification_image_prayer3.setImageResource(R.drawable.ic_notifications_24);

        }

        if (prayerPreferences.getNotificationPrayer4().equals("0")

        ) {

            notification_image_prayer4.setImageResource(R.drawable.ic_notifications_off_24);

        } else {
            notification_image_prayer4.setImageResource(R.drawable.ic_notifications_24);

        }

        if (prayerPreferences.getNotificationPrayer5().equals("0")

        ) {

            notification_image_prayer5.setImageResource(R.drawable.ic_notifications_off_24);

        } else {
            notification_image_prayer5.setImageResource(R.drawable.ic_notifications_24);

        }

        if (prayerPreferences.getNotificationPrayer6().equals("0")

        ) {

            notification_image_prayer6.setImageResource(R.drawable.ic_notifications_off_24);

        } else {
            notification_image_prayer6.setImageResource(R.drawable.ic_notifications_24);

        }


    }
}