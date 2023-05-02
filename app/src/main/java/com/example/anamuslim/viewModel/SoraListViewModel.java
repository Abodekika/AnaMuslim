package com.example.anamuslim.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SoraListViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SoraListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


    public String read_quran(Context context) {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = context.getAssets().open("QuranDataBase/quran_part.json");
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