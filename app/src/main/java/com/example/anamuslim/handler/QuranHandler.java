package com.example.anamuslim.handler;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;


import com.example.anamuslim.pojo.quran.NewAyaLocationInPage;
import com.example.anamuslim.pojo.quran.QuranSurah;
import com.example.anamuslim.utils.NewUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
This part used from Holy quran App
 */
public class QuranHandler {
    public static ArrayList<QuranPage> mQuranPageList;
    public static HashMap<Pair<Integer, Integer>, Integer> mQuranPageFinderList;
    public static HashMap<Pair<Integer, Integer>, QuranAyah> mQuranAyahFinderList;
    public static HashMap<QuranAyah, com.example.anamuslim.handler.QuranSurah> mQuranSurahHash;
    public static ArrayList<com.example.anamuslim.handler.QuranSurah> mQuranSurahList;
    private static final String TAG = "QuranHandlerNew";

    public static SparseArray<ArrayList<AyahLocationInPage>> loadAyahLocationInPages(Context context) {
        List<NewAyaLocationInPage> list = NewUtil.getlistlocation(context);
        SparseArray<ArrayList<AyahLocationInPage>> ayahLocationInPageList = new SparseArray<>();

        for (int j = 0; j < list.size(); j++) {

            int page = Integer.parseInt(list.get(j).getPage_number());
            ArrayList<AyahLocationInPage> ayahLocList = ayahLocationInPageList.get(page);

            if (ayahLocList == null) {
                ayahLocList = new ArrayList<>();
                ayahLocationInPageList.put(page, ayahLocList);
            }
            NewAyaLocationInPage m = list.get(j);

            AyahLocationInPage ayahLocationInPage = new AyahLocationInPage(Integer.parseInt(m.getMarker_type()),
                    ((642 * Float.parseFloat(m.getX()))) / 100.0f,
                    ((917 * Float.parseFloat(m.getY()))) / 100.0f, Integer.parseInt(m.getNumber_in_surah())

            );
            ayahLocList.add(ayahLocationInPage);

        }
        return ayahLocationInPageList;
    }


    public static void initializeSurahAndPageLists(Context context, int iy,
                                                    SparseArray<ArrayList<AyahLocationInPage>>
                                                            ayahLocationInPageList) throws JSONException {
        QuranPage quranPage;
        com.example.anamuslim.handler.QuranSurah quranSurah = new com.example.anamuslim.handler.QuranSurah();
        QuranSurah c = NewUtil.get_quran_surah(context).get(iy);

        quranSurah.mQuranSurahNumber = Integer.parseInt(c.surah_number);
        quranSurah.mQuranSurahName = c.surah_name;
        quranSurah.mQuranSurahEnglishName = c.surah_name_en;
        quranSurah.mQuranSurahPlaceOfDescend = c.place_decent;
        quranSurah.mQuranSurahNumberOfAyah = Integer.parseInt(c.number_ayahs_in_surah);
        quranSurah.mQuranSurahDisplayName = String.valueOf((char) Integer.parseInt(c.display_name, 16));



        int surahNumberInPage = Integer.parseInt(NewUtil.get_quran_surah(context).get(iy).surah_number_in_page);
        int surahCounter = 0;
        for (int i = Integer.parseInt(c.surah_start_page); i <= Integer.parseInt(c.surah_end_page); i++) {
            quranSurah.addQuranPage(i);
            if (mQuranPageList.size() <= i) {
                quranPage = new QuranPage();
                quranPage.mQuranPageNumber = i;
            } else {
                quranPage = mQuranPageList.get(i);
            }
            quranPage.addQuranSurah(quranSurah);

            ArrayList<QuranAyah> quranAyahList = new ArrayList<>();
            ArrayList<AyahLocationInPage> ayahLocList = ayahLocationInPageList.get(i);
            for (int j = 0; j < ayahLocList.size(); j++) {
                AyahLocationInPage ayahLoc = ayahLocList.get(j);
                if (ayahLoc.mPageType == 1) {
                    surahCounter++;
                }
                if (surahCounter >= surahNumberInPage) {
                    if (surahCounter > surahNumberInPage) {
                        break;
                    }
                    QuranAyah quranAyah = new QuranAyah(
                            ayahLoc.mAyahX - ((float) 20),
                            ayahLoc.mAyahY - ((float) 20),
                            ayahLoc.mAyahX + ((float) 20),
                            ayahLoc.mAyahY + ((float) 20),
                            ayahLoc.mPageType, quranSurah.mQuranSurahNumber);


                    quranAyah.mAyahNumberInSurah = ayahLoc.mAyahNumberInSurah;
                    quranAyah.mAyahNumberInPage = j;
                    mQuranPageFinderList.put(new Pair<>(quranSurah.mQuranSurahNumber, quranAyah.mAyahNumberInSurah), quranPage.mQuranPageNumber);
                    mQuranAyahFinderList.put(new Pair<>(quranSurah.mQuranSurahNumber, quranAyah.mAyahNumberInSurah), quranAyah);
                    quranAyahList.add(quranAyah);
                    mQuranSurahHash.put(quranAyah, quranSurah);
                    quranPage.addQuranAyahToSurah(quranSurah, quranAyah);
                }
            }
            quranSurah.addQuranAyahList(i, quranAyahList);
            quranPage.addQuranAyahList(quranAyahList);
            if (!mQuranPageList.contains(quranPage)) {
                mQuranPageList.add(quranPage);
            }
        }
        mQuranSurahList.add(quranSurah);
    }


    public static void load(Context context) {

        SparseArray<ArrayList<AyahLocationInPage>> ayahLocationInPageList = loadAyahLocationInPages(context);
        try {
            mQuranSurahList = new ArrayList<>();
            mQuranPageList = new ArrayList<>();
            mQuranSurahHash = new HashMap<>();
            mQuranPageFinderList = new HashMap<>();
            mQuranAyahFinderList = new HashMap<>();
            List<QuranSurah> list = NewUtil.get_quran_surah(context);
            for (int j = 0; j < list.size(); j++) {
                initializeSurahAndPageLists(context, j, ayahLocationInPageList);
            }
            ayahLocationInPageList.clear();


        } catch (Exception e) {
            Log.e("test", "load failed: " + e.getMessage());
        }
    }

    public static QuranAyahDetails selectAyah(Context context, QuranAyah quranAyah, int i, float f, ArrayList<QuranAyah> arrayList) {
        int size = mQuranPageList.get(i).mQuranAyahList.size();
        for (int i2 = 0; i2 < size; i2++) {
            QuranAyah quranAyah2 = mQuranPageList.get(i).mQuranAyahList.get(i2);
            if (quranAyah.isAbove(quranAyah2) && quranAyah2.mType == 1 && !isOnSameLine(quranAyah, quranAyah2)) {
                break;
            }
            if ((!quranAyah.isBelow(quranAyah2) || isOnSameLine(quranAyah, quranAyah2)) && (!quranAyah.isLeftTo(quranAyah2) || !isOnSameLine(quranAyah, quranAyah2))) {
                int i3 = i2 - 1;
                QuranAyah quranAyah3 = i3 >= 0 ? mQuranPageList.get(i).mQuranAyahList.get(i3) : null;
                if (quranAyah3 == null) {
                    continue;
                } else if (isOnSameLine(quranAyah, quranAyah2) && isOnSameLine(quranAyah, quranAyah3)) {
                    returnSelectedAyahMarkers(context, quranAyah2, quranAyah3, f, true, arrayList);
                    return new QuranAyahDetails(mQuranSurahHash.get(quranAyah2).mQuranPageToAyahList.get(i).get(mQuranSurahHash.get(quranAyah2).mQuranPageToAyahList.get(i).indexOf(quranAyah2)).mAyahNumberInSurah, mQuranSurahHash.get(quranAyah2).mQuranSurahNumber, mQuranSurahHash.get(quranAyah2).mQuranSurahNumberOfAyah, i, mQuranSurahHash.get(quranAyah2).mQuranSurahName, mQuranSurahHash.get(quranAyah2).mQuranSurahEnglishName, mQuranSurahHash.get(quranAyah2).mQuranSurahPlaceOfDescend);
                } else if (isOnSameLine(quranAyah3, quranAyah2)) {
                    break;
                } else if (quranAyah3.mTop < quranAyah2.mTop && quranAyah3.mBottom < quranAyah2.mBottom) {
                    returnSelectedAyahMarkers(context, quranAyah2, quranAyah3, f, false, arrayList);
                    return new QuranAyahDetails(mQuranSurahHash.get(quranAyah2).mQuranPageToAyahList.get(i).get(mQuranSurahHash.get(quranAyah2).mQuranPageToAyahList.get(i).indexOf(quranAyah2)).mAyahNumberInSurah, mQuranSurahHash.get(quranAyah2).mQuranSurahNumber, mQuranSurahHash.get(quranAyah2).mQuranSurahNumberOfAyah, i, mQuranSurahHash.get(quranAyah2).mQuranSurahName, mQuranSurahHash.get(quranAyah2).mQuranSurahEnglishName, mQuranSurahHash.get(quranAyah2).mQuranSurahPlaceOfDescend);
                }
            }
        }
        return null;
    }


    public static void returnSelectedAyahMarkers(
            Context context, QuranAyah ayah,
            QuranAyah prevAyah,
            float imageWidth,
            boolean isSameLine,
            ArrayList<QuranAyah> ayahRects) {

        if (isSameLine) {
            ayahRects.add(new QuranAyah(ayah.mLeft, ayah.mTop, prevAyah.mLeft, ayah.mBottom, 0, 0));
        } else {
            if ((imageWidth - ((float) 50) - ayah.mLeft >= ((float) 20))) {
                ayahRects.add(new QuranAyah(ayah.mLeft, ayah.mTop, imageWidth - ((float) 50), ayah.mBottom, 0, 0));
            }
            if (ayah.mTop - prevAyah.mBottom >= ((float) 20)) {
                ayahRects.add(new QuranAyah((float) 50, prevAyah.mBottom, imageWidth - ((float) 50), ayah.mTop, 0, 0));
            }
            if (prevAyah.mLeft - ((float) 50) >= ((float) 20)) {
                ayahRects.add(new QuranAyah((float) 50, prevAyah.mTop, prevAyah.mLeft, prevAyah.mBottom, 0, 0));
            }
        }
    }

    public static boolean isOnSameLine(QuranAyah p1, QuranAyah p2) {
        if (Float.compare(p1.mTop, p2.mTop) >= 0 && Float.compare(p1.mTop, p2.mBottom - ((float) 20)) < 0) {
            return true;
        }
        if (Float.compare(p2.mTop, p1.mTop) < 0 || Float.compare(p2.mTop, p1.mBottom - ((float) 20)) >= 0) {
            return false;
        }
        return true;
    }
}
