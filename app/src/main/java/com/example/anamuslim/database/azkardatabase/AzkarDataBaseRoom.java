package com.example.anamuslim.database.azkardatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import com.example.anamuslim.pojo.azkar.Azker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Azker.class}, version = 1)
@TypeConverters(Converter.class)

public abstract class AzkarDataBaseRoom extends RoomDatabase {

    private static AzkarDataBaseRoom instance = null;

    public abstract AzkarRoomDao azkarRoomDao();

    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AzkarDataBaseRoom getInstance(Context context) {

        if (instance == null) {
            synchronized (AzkarDataBaseRoom.class) {
                if (instance == null) {
                    try {
                        String DATABASE_NAME = "quran.db";
                        instance = Room.databaseBuilder(context.getApplicationContext(),
                                        AzkarDataBaseRoom.class, DATABASE_NAME)
                                .allowMainThreadQueries()

                                .build();
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
        }
        return instance;
    }



}
