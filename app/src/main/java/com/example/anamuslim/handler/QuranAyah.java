package com.example.anamuslim.handler;


public class QuranAyah {
    public int mAyahNumberInPage;
    public int mAyahNumberInSurah;
    public float mBottom;
    public float mLeft;
    public float mRight;
    public int mSurahNumber;
    public float mTop;
    public int mType;

    public QuranAyah(float f, float f2, float f3, float f4, int i, int i2) {
        this.mLeft = f;
        this.mTop = f2;
        this.mRight = f3;
        this.mBottom = f4;
        this.mType = i;
        this.mSurahNumber = i2;
    }

    public boolean isAbove(QuranAyah quranAyah) {
        return this.mTop < quranAyah.mTop && this.mBottom < quranAyah.mBottom;
    }

    public boolean isBelow(QuranAyah quranAyah) {
        return this.mTop > quranAyah.mTop && this.mBottom > quranAyah.mBottom;
    }

    public boolean isRightTo(QuranAyah quranAyah) {
        return this.mRight > quranAyah.mRight;
    }

    public boolean isLeftTo(QuranAyah quranAyah) {
        return this.mLeft < quranAyah.mLeft;
    }

    public boolean equals(Object obj) {
        if (obj instanceof QuranAyah) {
            QuranAyah quranAyah = (QuranAyah) obj;
            if (this.mLeft == quranAyah.mLeft && this.mTop == quranAyah.mTop && this.mRight == quranAyah.mRight && this.mBottom == quranAyah.mBottom && this.mType == quranAyah.mType) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    /* loaded from: classes.dex */


        public int compare(QuranAyah quranAyah, QuranAyah quranAyah2) {
            if (QuranHandler.isOnSameLine(quranAyah, quranAyah2)) {
                return -Float.compare(quranAyah.mLeft, quranAyah2.mLeft);
            }
            if (Float.compare(quranAyah.mTop, quranAyah2.mTop) >= 0 || Float.compare(quranAyah.mBottom, quranAyah2.mBottom) >= 0) {
                return (Float.compare(quranAyah.mTop, quranAyah2.mTop) <= 0 || Float.compare(quranAyah.mBottom, quranAyah2.mBottom) <= 0) ? 0 : 1;
            }
            return -1;
        }
    }

