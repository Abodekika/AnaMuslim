package com.example.anamuslim.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class LocationPreferences {

    private static final String FILE_NAME = "LOCATION_PREF";
    private static final String CITY_KEY = "CITY_PREF";
    private static final String Longitude_KEY = "Longitude_PREF";
    private static final String Latitude_KEY = "Latitude_PREF";
    private static final String COUNTRY_KEY = "COUNTRY_PREF";
    private static final String FIRST_OPEN_KEY = "FIRST_OPEN_PREF";
    private static final String Prayer_Timing_KEY = "Prayer_Timing_KEY";
    private static final String METHOD_KEY = "METHOD_PREF";
    private static final String CountryCode_KEY = "CountryCode_PREF";
    private static final String ADDRESS_KEY = "ADDRESS_PREF";

    private final SharedPreferences preferences;

    public String getLongitude() {
        return preferences.getString(Longitude_KEY, "31.274241");
    }

    public String getLatitude() {
        return preferences.getString(Latitude_KEY, "29.998577");
    }

    public void setLongitude(String Longitude) {
        preferences.edit().putString(Longitude_KEY, Longitude).apply();
    }

    public String getCountryCode_KEY() {
        return preferences.getString(CountryCode_KEY, "EG");
    }

    public void setCountryCode_KEY(String countryCode) {
        preferences.edit().putString(CountryCode_KEY, countryCode).apply();
    }

    public void setLatitude(String Latitude) {
        preferences.edit().putString(Latitude_KEY, Latitude).apply();
    }


    public LocationPreferences(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getCity() {
        return preferences.getString(CITY_KEY, "cairo");
    }

    public void setCity(String city) {
        preferences.edit().putString(CITY_KEY, city).apply();
    }

    public void setAddress(String address) {
        preferences.edit().putString(ADDRESS_KEY, address).apply();
    }

    public String getAddress() {
        return preferences.getString(ADDRESS_KEY, "A");
    }

    public String getCountry() {
        return preferences.getString(COUNTRY_KEY, "EG");
    }

    public void setCountry(String country) {
        preferences.edit().putString(COUNTRY_KEY, country).apply();
    }

    public int getMethod() {
        return preferences.getInt(METHOD_KEY, 5);
    }

    public void setMethod(int method) {
        preferences.edit().putInt(METHOD_KEY, method).apply();
    }

    public boolean getFIRST_OPEN() {
        return preferences.getBoolean(FIRST_OPEN_KEY, true);
    }

    public void setFIRST_OPEN(boolean openKey) {
        preferences.edit().putBoolean(FIRST_OPEN_KEY, openKey).apply();
    }
}
