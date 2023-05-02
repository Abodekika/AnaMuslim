package com.example.anamuslim.ui;

import android.Manifest;
import android.content.pm.PackageManager;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anamuslim.databinding.ActivityLocationBinding;
import com.example.anamuslim.databinding.ActivityMainBinding;
import com.example.anamuslim.location.LocationManger;


import androidx.appcompat.app.AppCompatActivity;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;




public class LocationActivity extends AppCompatActivity {


    private ActivityLocationBinding binding;
    LocationManger locationManger;
    TextView textView;
    Button button;
    ProgressBar progressBar;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        locationManger = LocationManger.getInstance(this);
        textView = binding.textView5;
        button = binding.button;
        progressBar = binding.progressBar;


        button.setOnClickListener(v -> checkLocationPermission());


    }


    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LocationActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);

            Toast.makeText(this, "رفض الاذان", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "تم منح الاذان", Toast.LENGTH_SHORT).show();
            locationManger.buttonSwitchGPS_ON();
            locationManger.getCurrentLocation(progressBar);


        }
    }



}