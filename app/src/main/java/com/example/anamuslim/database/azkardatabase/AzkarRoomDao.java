package com.example.anamuslim.database.azkardatabase;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.anamuslim.pojo.azkar.Azker;
import com.example.anamuslim.pojo.azkar.FavItem;

import java.util.List;


@Dao
public interface AzkarRoomDao {

    @Insert
    public void insert(Azker azkerRoom);

    @Query(value = "select * from Azkar_table where id=:id ")
    public List<Azker> readAllData(String id);

    @Query(value = "update Azkar_table set bookmark='0' where id=:id ")
    public void deleteFromD(String id);

    @Query(value = "select * from Azkar_table where bookmark='1' ")
    public List<FavItem> readAllFav();

}
