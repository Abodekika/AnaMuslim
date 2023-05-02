package com.example.anamuslim.handler;

/**
 This part used from Holy quran App
 */
public class QuranReader {
    public boolean mAvailable;
    public String mFolderName;
    public boolean mIsBasmalaEnabled;
    public boolean mIsContinuous;
    public String mName;
    public String mNameEnglish;
    public String mUrl;

    public QuranReader(String str, String str2, String str3, String str4, boolean z, boolean z2, boolean z3) {
        this.mName = str;
        this.mUrl = str3;
        this.mFolderName = str4;
        this.mIsContinuous = z;
        this.mNameEnglish = str2;
        this.mIsBasmalaEnabled = z2;
        this.mAvailable = z3;
    }
}
