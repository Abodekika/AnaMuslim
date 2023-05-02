package com.example.anamuslim.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.anamuslim.database.alsibhadatabase.AlsibhaDao;
import com.example.anamuslim.database.alsibhadatabase.AlsibhaDataBaseRoom;
import com.example.anamuslim.pojo.alsibha.Alsibha;


import java.util.List;

public class AlsibhaRepository {

    AlsibhaDao alsibhaDao;
    private static final String TAG = "Repository";

    public AlsibhaRepository(Context context) {

        AlsibhaDataBaseRoom db = AlsibhaDataBaseRoom.getInstance(context);

        if (db != null) {
            alsibhaDao = db.alsibhaDao();
        }
    }

/*
    public void insert() {
        AlsibhaDataBaseRoom.databaseWriteExecutor.execute(() -> {
            Alsibha alsibha = new Alsibha(0, "الْلَّهُ أَكْبَرُ", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha1 = new Alsibha(1, "الْحَمْدُ للّهِ", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha2 = new Alsibha(2, "سُبْحَانَ اللَّهِ", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha3 = new Alsibha(3, "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ ، سُبْحَانَ اللَّهِ الْعَظِيمِ", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha4 = new Alsibha(4, "سُبْحَانَ اللهِ العَظِيمِ وَبِحَمْدِهِ", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha5 = new Alsibha(5, "لَا إلَه إلّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلُّ شَيْءِ قَدِيرِ.", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha6 = new Alsibha(6, "لا حَوْلَ وَلا قُوَّةَ إِلا بِاللَّهِ ", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha7 = new Alsibha(7, "الْلَّهُم صَلِّ وَسَلِم وَبَارِك عَلَى سَيِّدِنَا مُحَمَّد", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha8 = new Alsibha(8, "أستغفر الله", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha9 = new Alsibha(9, "سُبْحَانَ الْلَّهِ، وَالْحَمْدُ لِلَّهِ، وَلَا إِلَهَ إِلَّا الْلَّهُ، وَالْلَّهُ أَكْبَرُ", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha10 = new Alsibha(10, "لَا إِلَهَ إِلَّا اللَّه", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha11 = new Alsibha(11, "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ كَمَا صَلَّيْتَ عَلَى إِبْرَاهِيمَ , وَعَلَى آلِ إِبْرَاهِيمَ إِنَّكَ حَمِيدٌ مَجِيدٌ , اللَّهُمَّ بَارِكْ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ كَمَا بَارَكْتَ عَلَى إِبْرَاهِيمَ وَعَلَى آلِ إِبْرَاهِيمَ إِنَّكَ حَمِيدٌ مَجِيدٌ.", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha12 = new Alsibha(12, "لَا إلَه إلّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلُّ شَيْءِ قَدِيرِ.", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha13 = new Alsibha(13, "لا حَوْلَ وَلا قُوَّةَ إِلا بِاللَّهِ", 0, 0, 0, 0, 0, 0, 0, 0);
            Alsibha alsibha14 = new Alsibha(14, "أستغفر الله", 0, 0, 0, 0, 0, 0, 0, 0);

            alsibhaDao.insert(alsibha);
            alsibhaDao.insert(alsibha1);
            alsibhaDao.insert(alsibha2);
            alsibhaDao.insert(alsibha3);
            alsibhaDao.insert(alsibha4);
            alsibhaDao.insert(alsibha5);
            alsibhaDao.insert(alsibha6);
            alsibhaDao.insert(alsibha7);
            alsibhaDao.insert(alsibha8);
            alsibhaDao.insert(alsibha9);
            alsibhaDao.insert(alsibha10);
            alsibhaDao.insert(alsibha11);
            alsibhaDao.insert(alsibha12);
            alsibhaDao.insert(alsibha13);
            alsibhaDao.insert(alsibha14);
        });
    }


 */
    public LiveData<List<Alsibha>> readAllData() {

        LiveData<List<Alsibha>> list = alsibhaDao.readAllData();
        return list;
    }

    public List<Alsibha> readAllData2() {

        List<Alsibha> list = alsibhaDao.readAllData2();
        return list;
    }

    public void updateMax(int max) {
        AlsibhaDataBaseRoom.databaseWriteExecutor.execute(() -> {
            alsibhaDao.updateMax(max);
        });
    }

    public void updateCurrent_7(int current, int id) {

        alsibhaDao.updateCurrent_7(current, id);

    }

    public void updateCurrent_33(int current, int id) {

        alsibhaDao.updateCurrent_33(current, id);

    }

    public void updateCurrent_100(int current, int id) {

        alsibhaDao.updateCurrent_100(current, id);

    }

    public void updateCurrent_c(int current, int id) {

        alsibhaDao.updateCurrent_c(current, id);

    }

    public void updatePosition(int position) {

        alsibhaDao.updatePosition(position);

    }

    public void updateCurrent(int current, int id) {

        alsibhaDao.updateCurrent(current, id);

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
