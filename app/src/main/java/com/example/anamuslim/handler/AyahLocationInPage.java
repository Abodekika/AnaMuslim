package com.example.anamuslim.handler;


public class AyahLocationInPage {
    int mAyahNumberInSurah;
    float mAyahX;
    float mAyahY;
    int mPageType;

    public AyahLocationInPage(int i, float f, float f2, int i2) {
        this.mPageType = i;
        this.mAyahX = f;
        this.mAyahY = f2;
        this.mAyahNumberInSurah = i2;
    }
}
