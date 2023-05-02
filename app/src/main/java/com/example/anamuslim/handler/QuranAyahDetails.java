package com.example.anamuslim.handler;


public class QuranAyahDetails {
    public int mAyahNumberInSurah;
    public int mPageNumber;
    public String mSurahDescend;
    public String mSurahName;
    public String mSurahNameEnglish;
    public int mSurahNumber;
    public int mTotalAyahsInSurah;

    public QuranAyahDetails(int i, int i2, int i3, int i4, String str, String str2, String str3) {
        this.mAyahNumberInSurah = i;
        this.mSurahNumber = i2;
        this.mTotalAyahsInSurah = i3;
        this.mPageNumber = i4;
        this.mSurahName = str;
        this.mSurahNameEnglish = str2;
        this.mSurahDescend = str3;
    }
}
