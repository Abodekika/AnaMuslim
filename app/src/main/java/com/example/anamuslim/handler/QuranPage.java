package com.example.anamuslim.handler;

import android.util.SparseArray;

import java.util.ArrayList;
/**
This part used from Holy quran App
 */
public class QuranPage {
    public ArrayList<QuranAyah> mQuranAyahList;
    public int mQuranPageNumber;
    public ArrayList<QuranSurah> mQuranSurahList;
    public SparseArray<ArrayList<QuranAyah>> mQuranSurahToAyahsList;

    public void addQuranAyah(QuranAyah quranAyah) {
        if (this.mQuranAyahList == null) {
            this.mQuranAyahList = new ArrayList<>();
        }
        this.mQuranAyahList.add(quranAyah);
    }

    public void addQuranAyahList(ArrayList<QuranAyah> arrayList) {
        if (this.mQuranAyahList == null) {
            this.mQuranAyahList = new ArrayList<>();
        }
        this.mQuranAyahList.addAll(arrayList);
    }

    public void addQuranSurah(QuranSurah quranSurah) {
        if (this.mQuranSurahList == null) {
            this.mQuranSurahList = new ArrayList<>();
        }
        this.mQuranSurahList.add(quranSurah);
    }

    public void addQuranAyahToSurah(QuranSurah quranSurah, QuranAyah quranAyah) {
        if (this.mQuranSurahToAyahsList == null) {
            this.mQuranSurahToAyahsList = new SparseArray<>();
        }
        ArrayList<QuranAyah> arrayList = this.mQuranSurahToAyahsList.get(quranSurah.mQuranSurahNumber);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mQuranSurahToAyahsList.put(quranSurah.mQuranSurahNumber, arrayList);
        }
        arrayList.add(quranAyah);
    }
}
