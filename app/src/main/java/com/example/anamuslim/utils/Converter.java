package com.example.anamuslim.utils;

import android.content.Context;

import androidx.room.TypeConverter;

import com.example.anamuslim.pojo.azkar.Azker;
import com.example.anamuslim.pojo.azkar.ContentRoom;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class Converter implements Serializable {

    @TypeConverter
    public static String fromContentToGson(Azker azker) {

        if (azker == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ContentRoom>>() {
        }.getType();
        String json = gson.toJson(azker, type);

        return json;


    }

    @TypeConverter
    public static Azker fromGsonToContent(String azkar) {

        if (azkar == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Azker>() {
        }.getType();
        Azker azkerList = gson.fromJson(azkar, type);
        return azkerList;


    }

    public static String convertNumberType(Context context, String number) {

        try {
            if (context.getResources().getConfiguration().locale.getDisplayLanguage().equals("العربية"))
                return number.replaceAll("0", "٠").replaceAll("1", "١")
                        .replaceAll("2", "٢").replaceAll("3", "٣")
                        .replaceAll("4", "٤").replaceAll("5", "٥")
                        .replaceAll("6", "٦").replaceAll("7", "٧")
                        .replaceAll("8", "٨").replaceAll("9", "٩");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }
}
