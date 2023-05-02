package com.example.anamuslim.handler;

import android.util.SparseArray;

import java.util.ArrayList;

/**
 This part used from Holy quran App
 */
public class QuranSurah {
    public boolean mIsQuranSurahAudioDownloaded;
    public ArrayList<Integer> mQuranPageList;
    public SparseArray<ArrayList<QuranAyah>> mQuranPageToAyahList;
    public String mQuranSurahDisplayName;
    public String mQuranSurahEnglishName;
    public String mQuranSurahName;
    public int mQuranSurahNumber;
    public int mQuranSurahNumberOfAyah;
    public String mQuranSurahPlaceOfDescend;

    public void addQuranAyahList(int i, ArrayList<QuranAyah> arrayList) {
        if (this.mQuranPageToAyahList == null) {
            this.mQuranPageToAyahList = new SparseArray<>();
        }
        this.mQuranPageToAyahList.put(i, arrayList);
    }

    public void addQuranPage(int i) {
        if (this.mQuranPageList == null) {
            this.mQuranPageList = new ArrayList<>();
        }
        this.mQuranPageList.add(Integer.valueOf(i));
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof QuranSurah) && this.mQuranSurahNumber == ((QuranSurah) obj).mQuranSurahNumber;
    }
}
