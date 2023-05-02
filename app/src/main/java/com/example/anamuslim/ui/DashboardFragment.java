package com.example.anamuslim.ui;

import static com.example.anamuslim.R.*;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.example.anamuslim.R;
import com.example.anamuslim.databinding.FragmentDashboardBinding;
import com.example.anamuslim.location.LocationManger;
import com.example.anamuslim.sharedpreferences.LocationPreferences;
import com.example.anamuslim.viewModel.PrayerTimeViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DashboardFragment extends Fragment {

    Button btn_prayer_fragment;
    private FragmentDashboardBinding binding;
    LinearLayout dashboard_quran_card, dashboard_azkar_card, dashboard_qublia_card, dashboard_alsibha_card;
    public static Dialog dialog;
    LocationManger locationManger;
    ImageView dashboard_location_icon;
    ProgressBar dashboard_progressbar_icon;
    LottieAnimationView dashboard_progressbar_animation;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    LocationPreferences locationPreferences;
    TextView dashboard_current_prayer, dashboard_next_prayer_time, dashboard_location_textView;
    PrayerTimeViewModel prayerTimeViewModel;
    Date fajrDate, sunriseDate, duhrDate, asrDate, maghrebDate, ishaDate, midNightDate;
    String nextPray = "";
    String currentPray = "";
    Date nextDate, lastDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        prayerTimeViewModel =
                new ViewModelProvider(requireActivity()).get(PrayerTimeViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        btn_prayer_fragment = binding.btnPrayerFragment;
        dashboard_quran_card = binding.dashboardQuranCard;
        dashboard_azkar_card = binding.dashboardAzkarCard;
        dashboard_qublia_card = binding.dashboardQubliaCard;
        dashboard_location_icon = binding.dashboardLocationIcon;
        // dashboard_progressbar_icon = binding.dashboardProgressbarIcon;
        dashboard_progressbar_animation = binding.dashboardProgressbarAnimation;
        dashboard_current_prayer = binding.dashboardNextPrayer;
        dashboard_next_prayer_time = binding.dashboardNextPrayerTime;
        dashboard_location_textView = binding.dashboardLocationTextView;
        locationManger = LocationManger.getInstance(getContext());
        prayerTimeViewModel.setf(getContext());
        dashboard_alsibha_card = binding.dashboardAlsibhaCard;
        locationPreferences = new LocationPreferences(requireContext());

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_prayer_fragment.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(id.action_dashboard_fragment_to_prayer_time_fragment);

        });


        dashboard_quran_card.setOnClickListener(view1 -> {

            Navigation.findNavController(view).navigate(id.action_dashboard_fragment_to_quran_fragment);

        });

        dashboard_azkar_card.setOnClickListener(view12 ->
                Navigation.findNavController(view12).navigate(id.action_dashboard_fragment_to_azkar_fragment));


        dashboard_qublia_card.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(id.action_dashboard_fragment_to_qubliaFragment);

        });

        dashboard_alsibha_card.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(id.action_dashboard_fragment_to_alsibhaFragment);

        });


        dashboard_location_icon.setOnClickListener(v -> {
            checkLocationPermission();
        });

        dashboard_location_textView.setText(locationPreferences.getAddress());

        prayerTimeViewModel.getPrayerTime().observe(requireActivity(), this::updateViews);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);


        } else {

            locationManger.buttonSwitchGPS_ON();

            locationManger.getCurrentLocationTextView(dashboard_progressbar_animation, dashboard_location_textView);

        }
    }

    private void updateViews(Date[] dates) {

        Calendar mid = Calendar.getInstance();
        mid.set(Calendar.HOUR_OF_DAY, 0);
        mid.set(Calendar.MINUTE, 0);
        mid.set(Calendar.SECOND, 0);
        midNightDate = mid.getTime();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a", Locale.getDefault());


        fajrDate = dates[0];


        sunriseDate = dates[1];
        //sunrise.setText(format.format(sunriseDate));

        duhrDate = dates[2];


        asrDate = dates[3];


        maghrebDate = dates[4];


        ishaDate = dates[5];


        checkActiveView();

    }

    @SuppressLint("SetTextI18n")
    private void checkActiveView() {


        SimpleDateFormat format = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        if (fajrDate == null || sunriseDate == null || duhrDate == null || asrDate == null || maghrebDate == null || ishaDate == null)
            return;

        Date current = Calendar.getInstance().getTime();


        if (current.after(fajrDate) && current.before(sunriseDate)) {
            nextPray = getString(R.string.sun_rise);
            lastDate = fajrDate;
            nextDate = sunriseDate;

        }
        if (current.after(sunriseDate) && current.before(duhrDate)) {

            nextPray = getString(R.string.zuhr);
            nextPray = getString(R.string.sun_rise);
            lastDate = sunriseDate;
            nextDate = duhrDate;
        } else if (current.after(duhrDate) && current.before(asrDate)) {

            nextPray = getString(R.string.asr);
            currentPray = getString(R.string.zuhr);
            lastDate = duhrDate;
            nextDate = asrDate;
        } else if (current.after(asrDate) && current.before(maghrebDate)) {

            nextPray = getString(R.string.magrib);
            currentPray = getString(R.string.asr);
            lastDate = asrDate;
            nextDate = maghrebDate;
        } else if (current.after(maghrebDate) && current.before(ishaDate)) {

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

            nextPray = getString(R.string.fajr);
            currentPray = getString(R.string.isha);

        }

        dashboard_current_prayer.setText("صلاة" + " " + nextPray);
        dashboard_next_prayer_time.setText(format.format(nextDate));

        // prayerPreferences.setNextPray("صلاة" + " " + nextPray);
        // prayerPreferences.setNextPrayTime(format.format(nextDate));

//        Log.i("DATE_TAg" ,"last : "+format.format(lastDate));
//        Log.i("DATE_TAg" ,"current : "+format.format(current));
//        Log.i("DATE_TAg" ,"end : "+format.format(nextDate));


    }


}