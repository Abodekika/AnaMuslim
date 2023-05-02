package com.example.anamuslim.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.example.anamuslim.R;
import com.example.anamuslim.sharedpreferences.LocationPreferences;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocationManger {

    private static final int REQUEST_CHECK_SETTINGS = 1000;
    private static LocationManger locationManger_instance;
    private Context context;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    LocationCallback locationCallback;
    private Location current_location;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = "LocationManger";
    ExecutorService executorService;
    LocationPreferences preferences;


    public static LocationManger getInstance(Context context) {
        if (locationManger_instance == null) {

            locationManger_instance = new LocationManger();

        }
        locationManger_instance.init(context);

        return locationManger_instance;

    }

    private void init(Context context) {
        this.context = context;
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        this.executorService = Executors.newSingleThreadExecutor();
        preferences = new LocationPreferences(context);


    }


    protected void createLocationRequest() {
        locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(1000)
                .setMaxUpdateDelayMillis(5000)
                .build();

    }


    public void buttonSwitchGPS_ON() {

        createLocationRequest();

        LocationSettingsRequest.Builder locationSettingsRequestBuilder = new LocationSettingsRequest.Builder();

        locationSettingsRequestBuilder.addLocationRequest(locationRequest);
        locationSettingsRequestBuilder.setAlwaysShow(true);

        SettingsClient settingsClient = LocationServices.getSettingsClient(context);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(locationSettingsRequestBuilder.build());

        task.addOnSuccessListener(locationSettingsResponse -> {


        });

        task.addOnFailureListener(e -> {


            if (e instanceof ResolvableApiException) {
                try {
                    ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                    resolvableApiException.startResolutionForResult((Activity) context, REQUEST_CHECK_SETTINGS);

                } catch (IntentSender.SendIntentException sendIntentException) {
                    sendIntentException.printStackTrace();
                }
            }
        });
    }


    public void getCurrentLocation(ProgressBar progressBar) {

        progressBar.setVisibility(View.VISIBLE);

        createLocationRequest();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        fusedLocationProviderClient
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(context)
                                .removeLocationUpdates(this);
                        if (locationResult.getLocations().size() > 0) {
                            int latestlocIndex = locationResult.getLocations().size() - 1;
                            double lati = locationResult.getLocations().get(latestlocIndex).getLatitude();
                            double longi = locationResult.getLocations().get(latestlocIndex).getLongitude();


                            android.location.Location location = new android.location.Location("providerNA");
                            location.setLongitude(longi);
                            location.setLatitude(lati);

                            executorService.execute(() -> getLocationInfo(location));


                            progressBar.setVisibility(View.GONE);

                        } else {

                            progressBar.setVisibility(View.GONE);


                        }
                    }
                }, Looper.getMainLooper());

    }


    private List<Address> getLocationInfo(Location location) {

        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
            String s = addresses.get(0).getLocality() + "-" + addresses.get(0).getAdminArea();

            preferences.setAddress(s);
            preferences.setCity(addresses.get(0).getAdminArea());
            preferences.setCountryCode_KEY(addresses.get(0).getCountryCode());
            preferences.setCountry(addresses.get(0).getCountryName());
            preferences.setLongitude(String.valueOf(location.getLongitude()));
            preferences.setLatitude(String.valueOf(location.getLatitude()));
            preferences.setLatitude(String.valueOf(location.getLatitude()));

        } catch (Exception ioException) {
            Log.d("Location", "Error in getting address for the location");
        }


        return addresses;

    }

    public void getCurrentLocationTextView(LottieAnimationView progressBar, TextView textView) {

        progressBar.setAnimation(R.raw.loading_progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.playAnimation();
        createLocationRequest();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        fusedLocationProviderClient
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(context)
                                .removeLocationUpdates(this);
                        if (locationResult.getLocations().size() > 0) {
                            int latestlocIndex = locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(latestlocIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestlocIndex).getLongitude();


                            android.location.Location location = new android.location.Location("providerNA");
                            location.setLongitude(longitude);
                            location.setLatitude(latitude);


                            List<Address> addresses = getLocationInfo(location);

                            // String s = (addresses.get(0).getCountryCode());


                            //SubAdminArea -> المركز
                            //AdminArea -> البلد
                            //getLocality -> المحافظة

                            String s = addresses.get(0).getLocality() + "-" + addresses.get(0).getAdminArea();
                            System.out.println(s);


                            preferences.setAddress(s);
                            preferences.setCountryCode_KEY(addresses.get(0).getCountryCode());


                            textView.setText(s);
                            progressBar.pauseAnimation();
                            progressBar.setVisibility(View.GONE);

                        } else {

                            progressBar.setVisibility(View.GONE);


                        }
                    }
                }, Looper.getMainLooper());

    }


}

