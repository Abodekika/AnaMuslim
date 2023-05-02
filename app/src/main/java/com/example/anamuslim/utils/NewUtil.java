package com.example.anamuslim.utils;

import android.content.Context;

import com.example.anamuslim.pojo.quran.NewAyaLocationInPage;
import com.example.anamuslim.pojo.quran.QuranPart;
import com.example.anamuslim.pojo.quran.QuranSurah;

import com.example.anamuslim.pojo.quran.Reader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NewUtil {

    /**
     * Read Aya Location json file and convert it into list
     *
     * @param context
     * @return
     */

    public static String read_quran_surah(Context context) {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = context.getAssets().open("new_quran_surah.json");
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

    public static List<QuranSurah> get_quran_surah(Context context) {


        Gson gson = new Gson();

        String file = read_quran_surah(context);

        Type ak = new TypeToken<ArrayList<QuranSurah>>() {
        }.getType();
        List<QuranSurah> dddd = gson.fromJson(file, ak);


        return dddd;

    }


    /**
     * Read Aya Location json file and convert it into list
     *
     * @param context
     * @return
     */

    public static String readAyaLocation(Context context) {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = context.getAssets().open("new_ayah_loc_in_pages.json");
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

    public static List<NewAyaLocationInPage> getlistlocation(Context context) {


        Gson gson = new Gson();

        String file = readAyaLocation(context);

        Type ak = new TypeToken<ArrayList<NewAyaLocationInPage>>() {
        }.getType();
        List<NewAyaLocationInPage> dddd = gson.fromJson(file, ak);


        return dddd;

    }


    /**
     * Read quran Part json file and convert it into list
     *
     * @param context
     * @return
     */

    public static String readQuranPart(Context context) {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = context.getAssets().open("quran_part.json");
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

    public static List<QuranPart> get_quran_part(Context context) {


        Gson gson = new Gson();

        String file = readQuranPart(context);

        Type ak = new TypeToken<ArrayList<QuranPart>>() {
        }.getType();
        List<QuranPart> dddd = gson.fromJson(file, ak);


        return dddd;

    }

    /**
     * Read quran reader json file and convert it into list
     *
     * @param context
     * @return
     */


    public static String readReader(Context context) {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = context.getAssets().open("readers.json");
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

    public static List<Reader> get_quran_reader(Context context) {


        Gson gson = new Gson();

        String file = readReader(context);

        Type ak = new TypeToken<ArrayList<Reader>>() {
        }.getType();
        List<Reader> dddd = gson.fromJson(file, ak);


        return dddd;

    }

    /**
     * Read quran reader only for spinner  json file and convert it into list
     *
     * @param context
     * @return
     */


    public static String[] get_quran_reader_only_for_spinner(Context context) {


        List<Reader> dddd = get_quran_reader(context);
        String[] arr = new String[dddd.size()];
        for (int i = 0; i < dddd.size(); i++) {
            arr[i] = dddd.get(i).getReader();
        }

        return arr;

    }

}
