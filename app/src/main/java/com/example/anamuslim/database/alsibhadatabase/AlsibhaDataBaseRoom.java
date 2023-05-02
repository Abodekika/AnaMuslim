package com.example.anamuslim.database.alsibhadatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.anamuslim.pojo.alsibha.Alsibha;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Alsibha.class}, version = 1)
public abstract class AlsibhaDataBaseRoom extends RoomDatabase {

    private static AlsibhaDataBaseRoom instance = null;

    public abstract AlsibhaDao alsibhaDao();

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AlsibhaDataBaseRoom getInstance(Context context) {

        if (instance == null) {
            synchronized (AlsibhaDataBaseRoom.class) {
                if (instance == null) {
                    try {
                        String DATABASE_NAME = "alsibha.db";
                        instance = Room.databaseBuilder(context.getApplicationContext(),
                                        AlsibhaDataBaseRoom.class, DATABASE_NAME)
                                .createFromAsset("alsibha/alsibha.db")
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
