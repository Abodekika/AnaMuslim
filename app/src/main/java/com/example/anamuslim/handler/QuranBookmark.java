package com.example.anamuslim.handler;

/**
 This part used from Holy quran App
 */
public class QuranBookmark {
    public int mAyahNumber;
    public int mSurahNumber;

    public QuranBookmark(int i, int i2) {
        this.mSurahNumber = i;
        this.mAyahNumber = i2;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof QuranBookmark)) {
            return false;
        }
        QuranBookmark quranBookmark = (QuranBookmark) obj;
        return quranBookmark.mAyahNumber == this.mAyahNumber && quranBookmark.mSurahNumber == this.mSurahNumber;
    }
}
