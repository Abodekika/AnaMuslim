package com.example.anamuslim.viewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.anamuslim.pojo.azkar.Azker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class AllAzkarViewModel extends ViewModel {
    Gson gson = new Gson();


    public ArrayList<Azker> azkarList(Context context) {
        String file = readAzkar(context);
        Type ak = new TypeToken<ArrayList<Azker>>() {
        }.getType();

        return gson.fromJson(file, ak);
    }

    public String readAzkar(Context context) {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = context.getAssets().open("Azkar/azkar.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }

}