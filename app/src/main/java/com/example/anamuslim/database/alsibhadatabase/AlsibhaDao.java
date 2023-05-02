package com.example.anamuslim.database.alsibhadatabase;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.anamuslim.pojo.alsibha.Alsibha;


import java.util.List;

@Dao
public interface AlsibhaDao {


    @Insert
    public void insert(Alsibha alsibha);

    @Query("select * from Alsibha")
    public LiveData<List<Alsibha>> readAllData();

    @Query("select * from Alsibha")
    public List<Alsibha> readAllData2();

    @Query("update alsibha set max =:max ")
    public void updateMax(int max);

    @Query("update alsibha set current_7 =:current where id=:id ")
    public void updateCurrent_7(int current, int id);

    @Query("update alsibha set current_33 =:current where id=:id ")
    public void updateCurrent_33(int current, int id);

    @Query("update alsibha set current_100 =:current where id=:id ")
    public void updateCurrent_100(int current, int id);

    @Query("update alsibha set current_c =:current where id=:id ")
    public void updateCurrent_c(int current, int id);

    @Query("update alsibha set position =:position ")
    public void updatePosition(int position);

    @Query("update alsibha set current =:current where id=:id ")
    public void updateCurrent(int current , int id);


}
