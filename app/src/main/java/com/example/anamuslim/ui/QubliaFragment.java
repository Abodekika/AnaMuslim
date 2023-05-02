package com.example.anamuslim.ui;

import static com.example.anamuslim.R.drawable.quran_photo;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anamuslim.R;
import com.example.anamuslim.databinding.FragmentQubliaBinding;
import com.example.anamuslim.sharedpreferences.LocationPreferences;
import com.example.anamuslim.viewModel.QubliaViewModel;

public class QubliaFragment extends Fragment {

    private QubliaViewModel mViewModel;
    private FragmentQubliaBinding binding;
    private ImageView imageDial, arrowViewQiblat, qublia_image_background;
    private float currentAzimuth;
    TextView info_value, qublia_degree, qublia_note;
    Toolbar toolbar;
    LocationPreferences preferences;

    private static final String TAG = "QubliaFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = FragmentQubliaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        imageDial = binding.qubliaImageMain;
        arrowViewQiblat = binding.qubliaImageSecond;
        info_value = binding.infoValue;
        qublia_note = binding.qubliaNote;
        toolbar = binding.qubliaToolbar;
        qublia_degree = binding.qubliaDegree;

        qublia_image_background = binding.qubliaImageBackground;
        preferences = new LocationPreferences(requireContext());
        setupCompass();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setTitle("القبلة");

        qublia_degree.setText("اتجاه القبلة  من الشمال: " + (int) fetch_GPS() + " ");


    }


    private void setupCompass() {
        fetch_GPS();
        Compass compass = new Compass(getContext());
        compass = compass;
        compass.start();
        compass.setListener(new Compass.CompassListener() { // from class: com.example.myapplication.MainActivity$$ExternalSyntheticLambda0
            @Override // com.example.myapplication.Compass.CompassListener
            public final void onNewAzimuth(float f) {
                adjustGambarDial(f);
                adjustArrowQiblat(f);
            }
        });
    }

    public void adjustGambarDial(float azimuth) {
        Animation an = new RotateAnimation(-this.currentAzimuth, -azimuth, 1, 0.5f, 1, 0.5f);
        this.currentAzimuth = azimuth;
        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);
        this.imageDial.startAnimation(an);
        int s = (int) azimuth;

        int s1 = (int) fetch_GPS();
        int v1 = s - 2;
        int v2 = s + 2;


        info_value.setText("" + s);


        if (s1 <= v1 || s1 >= v2) {
            qublia_image_background.setImageResource(R.drawable.circle_shape_white);

        } else {
            qublia_image_background.setImageResource(R.drawable.circle_shape);
        }
    }

    public void adjustArrowQiblat(float azimuth) {
        float QiblaDegree = fetch_GPS();
        Animation an = new RotateAnimation((-this.currentAzimuth) + QiblaDegree, -azimuth, 1, 0.5f, 1, 0.5f);
        this.currentAzimuth = azimuth;
        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);
        this.arrowViewQiblat.startAnimation(an);
        if (QiblaDegree > 0.0f) {
            this.arrowViewQiblat.setVisibility(View.VISIBLE);
            return;
        }
        this.arrowViewQiblat.setVisibility(View.INVISIBLE);
        this.arrowViewQiblat.setVisibility(View.GONE);
    }


    public float fetch_GPS() {
        double latitude2 = Math.toRadians(21.422507d);
        double latitude1 = Math.toRadians(Double.parseDouble(preferences.getLatitude()));
        double longDiff = Math.toRadians(39.826209d - Double.parseDouble(preferences.getLongitude()));
        float aFloat = (float) ((Math.toDegrees(Math.atan2(Math.sin(longDiff) * Math.cos(latitude2), (Math.cos(latitude1) * Math.sin(latitude2)) - ((Math.sin(latitude1) * Math.cos(latitude2)) * Math.cos(longDiff)))) + 360.0d) % 360.0d);

        arrowViewQiblat.setVisibility(View.VISIBLE);
        return aFloat;

    }


    /*
    private void setupCompass() {
        fetch_GPS();
        Compass compass = new Compass(requireContext());
        // this.compass = compass;
        compass.start();
        compass.setListener(new Compass.CompassListener() {
            @Override
            public void onNewAzimuth(float f) {
              //  adjustArrowQublia(f);
                adjustGambarDial(f);
            }
        });
    }

    public void adjustGambarDial(float azimuth) {
        Animation an = new RotateAnimation(-this.currentAzimuth, -azimuth, 1, 0.5f, 1, 0.5f);
        this.currentAzimuth = azimuth;
        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);
        qublia_image_main.startAnimation(an);

        int s = (int) azimuth;

        int s1 = (int) fetch_GPS();
        int v1 = s - 10;
        int v2 = s + 10;

        info_value.setText("" + s + " " + v1);

//        Log.d(TAG, "adjustGambarDial: " + s + " " + s1+" "+ v1 + " " + v2);
        if (s1 <= v1 || s1 >=   v2) {
          qublia_image_main.setImageResource(R.drawable.qublia_background_white);
            //  Toast.makeText(getContext(), "Aaaaaaa", Toast.LENGTH_SHORT).show();
        } else {
            qublia_image_main.setImageResource(R.drawable.qublia_background_y);
        }
    }

    public void adjustArrowQublia(float azimuth) {
        float QiblaDegree = fetch_GPS();
        Animation an = new RotateAnimation((-this.currentAzimuth) + QiblaDegree, -azimuth, 1, 0.5f, 1, 0.5f);
        this.currentAzimuth = azimuth;
        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);
        qublia_image_second.startAnimation(an);
        if (QiblaDegree > 0.0f) {
            this.qublia_image_second.setVisibility(View.VISIBLE);
            return;
        }
        this.qublia_image_second.setVisibility(View.VISIBLE);
        this.qublia_image_second.setVisibility(View.GONE);

    }

    public float fetch_GPS() {
        double latitude2 = Math.toRadians(21.422507d);
        double latitude1 = Math.toRadians(Double.parseDouble(preferences.getLatitude()));
        double longDiff = Math.toRadians(39.826209d - Double.parseDouble(preferences.getLongitude()));
        float aFloat = (float) ((Math.toDegrees(Math.atan2(Math.sin(longDiff) * Math.cos(latitude2), (Math.cos(latitude1) * Math.sin(latitude2)) - ((Math.sin(latitude1) * Math.cos(latitude2)) * Math.cos(longDiff)))) + 360.0d) % 360.0d);

        qublia_image_second.setVisibility(View.VISIBLE);
        return aFloat;

    }

//    public void SaveFloat(String key, Float ff) {
//        SharedPreferences.Editor edit = this.prefs.edit();
//        edit.putFloat(key, ff.floatValue());
//        edit.apply();
//    }
//
//    public Float GetFloat(String key) {
//        return Float.valueOf(this.prefs.getFloat(key, 0.0f));
//    }


     */
}