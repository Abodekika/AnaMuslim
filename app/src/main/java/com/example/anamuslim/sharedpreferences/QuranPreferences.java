package com.example.anamuslim.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class QuranPreferences {

    private static final String LAST_PAGE = "LAST_PAGE";
    private static final String FILE_NAME = "Quran_PREF";
    private final SharedPreferences preferences;

    public QuranPreferences(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public int getLast_page() {
        return preferences.getInt(LAST_PAGE, 0);
    }

    public void setLast_page(int  Longitude) {
        preferences.edit().putInt(LAST_PAGE, Longitude).apply();
    }

}
