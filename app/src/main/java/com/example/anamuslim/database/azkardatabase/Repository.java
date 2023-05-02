package com.example.anamuslim.database.azkardatabase;

import android.content.Context;

import com.example.anamuslim.pojo.azkar.Azker;
import com.example.anamuslim.pojo.azkar.ContentRoom;
import com.example.anamuslim.pojo.azkar.FavItem;


import java.util.List;

public class Repository {

    AzkarRoomDao azkarRoomDao;
    private static final String TAG = "Repository";

    public Repository(Context context) {

        AzkarDataBaseRoom db = AzkarDataBaseRoom.getInstance(context);

        if (db != null) {
            azkarRoomDao = db.azkarRoomDao();
        }
    }


    public void insertIntoData(Azker Azker) {
        AzkarDataBaseRoom.databaseWriteExecutor.execute(() -> {
            azkarRoomDao.insert(Azker);
        });
    }


    public void insertEmpty(List<Azker> azker_list) {


        for (int i = 0; i < azker_list.size(); i++) {

            String id = azker_list.get(i).getId();
            String title = azker_list.get(i).getTitle();
            String count = azker_list.get(i).getCount();
            String bookmark = azker_list.get(i).getBookmark();
            List<ContentRoom> content = azker_list.get(i).getContent();


            azker_list.get(i).setId(id);
            azker_list.get(i).setTitle(title);
            azker_list.get(i).setCount(count);
            if (azker_list.get(i).getBookmark().equals("1")) {


                azker_list.get(i).setBookmark("1");

            } else {
                azker_list.get(i).setBookmark("0");
            }
            azker_list.get(i).setContent(content);

//            contentValues.put("id", i);
//            contentValues.put("title", azker_list.get(i).title);
//            contentValues.put("count", azker_list.get(i).count);
//
//
//            //   contentValues.put("title",azker_list.get(i).content.get(0).ID);
//
//            if (azker_list.get(i).getBookmark().equals("1")) {
//
//
//                contentValues.put("bookmark", "1");
//
//            } else {
//                contentValues.put("bookmark", "0");
//            }
//            contentValues.put("content", gson.toJson(azker_list.get(i).content).getBytes());
            azkarRoomDao.insert(azker_list.get(i));

        }
    }

    public void insertIntoDataBase(List<Azker> azker_list) {

        AzkarDataBaseRoom.databaseWriteExecutor.execute(() -> {

            for (int i = 0; i < azker_list.size(); i++) {
                String id = azker_list.get(i).getId();
                String title = azker_list.get(i).getTitle();
                String count = azker_list.get(i).getCount();
                String bookmark = azker_list.get(i).getBookmark();
                List<ContentRoom> content = azker_list.get(i).getContent();

                if (azker_list.get(i).getBookmark().equals("1")) {

                    azker_list.get(i).setBookmark("1");

                } else {
                    azker_list.get(i).setBookmark("0");
                }
                Azker Azker = new Azker(id, title, count, bookmark, content);
                azkarRoomDao.insert(Azker);
            }

        });

    }


    public void insertAzkar(List<Azker> azker_list) {

        AzkarDataBaseRoom.databaseWriteExecutor.execute(() -> {

            for (int i = 0; i < azker_list.size(); i++) {
                String id = azker_list.get(i).getId();
                String title = azker_list.get(i).getTitle();
                String count = azker_list.get(i).getCount();
                String bookmark = azker_list.get(i).getBookmark();


                List<ContentRoom> content = azker_list.get(i).getContent();

                if (azker_list.get(i).getBookmark().equals("1")) {


                    azker_list.get(i).setBookmark("1");

                } else {
                    azker_list.get(i).setBookmark("0");
                }
                Azker Azker = new Azker(id, title, count, bookmark, content);
                azkarRoomDao.insert(Azker);
            }

        });

    }


    public void readAllData(String id, Valuelis valuelis) {

        List<Azker> list = azkarRoomDao.readAllData(id);
        valuelis.onlist(list);


    }

    public List<FavItem> readAllFav() {

        List<FavItem> list = azkarRoomDao.readAllFav();


        return list;
    }





    public void deleteFromD(String id) {
        AzkarDataBaseRoom.databaseWriteExecutor.execute(() -> {
            azkarRoomDao.deleteFromD(id);
        });
    }

/*
    public void insertEmptys(List<Azker> azker_list) {


        for (int i = 0; i < azker_list.size(); i++) {


            String id = azker_list.get(i).getId();
            String title = azker_list.get(i).getTitle();
            String count = azker_list.get(i).getCount();
            String bookmark = azker_list.get(i).getBookmark();
            List<Content> content = azker_list.get(i).getContent();


//            contentValues.put("id", i);
//            contentValues.put("title", azker_list.get(i).title);
//            contentValues.put("count", azker_list.get(i).count);


            //   contentValues.put("title",azker_list.get(i).content.get(0).ID);

            if (azker_list.get(i).getBookmark().equals("1")) {


                contentValues.put("bookmark", "1");

            } else {
                contentValues.put("bookmark", "0");
            }
            contentValues.put("content", gson.toJson(azker_list.get(i).content).getBytes());

            Azker azker = new Azker(id, title, count, bookmark, content);
            db.insert("azkar", null, contentValues);
        }
    }


 */
}
