package com.example.anamuslim.database.azkardatabase;

import androidx.room.TypeConverter;

import com.example.anamuslim.pojo.azkar.ContentRoom;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class Converter implements Serializable {

    @TypeConverter
    public String fromContentToGson(List<ContentRoom> content) {

        if (content == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ContentRoom>>() {
        }.getType();
        String json = gson.toJson(content, type);

        return json;


    }

    @TypeConverter
    public List<ContentRoom> fromGsonToContent(String content) {

        if (content == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ContentRoom>>() {
        }.getType();
        List<ContentRoom> productCategoriesList = gson.fromJson(content, type);
        return productCategoriesList;


    }
}
