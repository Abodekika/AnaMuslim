package com.example.anamuslim.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;


import com.example.anamuslim.handler.QuranAyah;
import com.example.anamuslim.handler.QuranHandler;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes.dex */
public class Utils {
    private static final String ARABIC_FONT_ENABLED_PREFERENCE_KEY = "com.nanosoft.holy.quran.ARABIC_FONT_ENABLED_PREFERENCE_KEY";
    private static final String LANGUAGE_CHANGED_PREFERENCE_KEY = "com.nanosoft.holy.quran.LANGUAGE_CHANGED_PREFERENCE_KEY";
    private static final String LANGUAGE_ENABLED_PREFERENCE_KEY = "com.nanosoft.holy.quran.LANGUAGE_ENABLED_PREFERENCE_KEY";
    private static final String LANGUAGE_PREFERENCE_KEY = "com.nanosoft.holy.quran.LANGUAGE_PREFERENCE_KEY";
    private static final String NIGHT_MODE_ENABLED_PREFERENCE_KEY = "com.nanosoft.holy.quran.NIGHT_MODE_ENABLED_PREFERENCE_KEY";
    private static final String SELECTED_PAGES_TYPE_PREFERENCE_KEY = "selected_pages_type_preference_key";
    private static final String SELECTED_READER_PREFERENCE_KEY = "selected_reader_preference_key";
    private static final String SELECTED_TAFSIR_PREFERENCE_KEY = "selected_tafsir_preference_key";
    private static final String ZOOM_MODE_ENABLED_PREFERENCE_KEY = "com.nanosoft.holy.quran.ZOOM_MODE_ENABLED_PREFERENCE_KEY";
    private static InputStream sInputstream;

    public static String getRandomDomain() {
        return Constants.QURAN_DOMAIN_URLS[new Random().nextInt((Constants.QURAN_DOMAIN_URLS.length - 1) + 0 + 1) + 0];
    }





    public static boolean checkSurahDownloaded(int i, int i2, String str) {
        String format = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i));
        for (int i3 = 1; i3 <= i2; i3++) {
            String format2 = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i3));
            if (!new File(Constants.AUDIO_DIR + str + "/" + format + format2 + Constants.AUDIO_EXTENSION).exists()) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkMinAyahDownloadedInSurah(int i, int i2, String str) {
        String format = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i));
        for (int i3 = 1; i3 <= i2; i3++) {
            String format2 = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i3));
            if (new File(Constants.AUDIO_DIR + str + "/" + format + format2 + Constants.AUDIO_EXTENSION).exists()) {
                return true;
            }
        }
        return false;
    }

    public static int checkAyahsDownloadedInSurah(int i, int i2, String str) {
        String format = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i));
        int i3 = 0;
        for (int i4 = 1; i4 <= i2; i4++) {
            String format2 = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i4));
            if (new File(Constants.AUDIO_DIR + str + "/" + format + format2 + Constants.AUDIO_EXTENSION).exists()) {
                i3++;
            }
        }
        return i3;
    }

    public static boolean checkPageDownloaded(int i, String str) {
        int size = QuranHandler.mQuranPageList.get(i).mQuranAyahList.size();
        for (int i2 = 0; i2 < size; i2++) {
            QuranAyah quranAyah = QuranHandler.mQuranPageList.get(i).mQuranAyahList.get(i2);
            String format = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(quranAyah.mSurahNumber));
            String format2 = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(quranAyah.mAyahNumberInSurah));
            if (!new File(Constants.AUDIO_DIR + str + "/" + format + format2 + Constants.AUDIO_EXTENSION).exists()) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkAyahDownloaded(int i, int i2, String str) {
        String format = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i));
        String format2 = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i2));
        return new File(Constants.AUDIO_DIR + str + "/" + format + format2 + Constants.AUDIO_EXTENSION).exists();
    }

    public static boolean checkContinousPageDownloaded(int i, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.AUDIO_DIR);
        sb.append(str);
        sb.append("/");
        sb.append(i);
        sb.append(Constants.AUDIO_EXTENSION);
        return new File(sb.toString()).exists();
    }

    public static boolean checkContinuousSurahDownloaded(int i, int i2, String str) {
        while (i <= i2) {
            if (!checkContinousPageDownloaded(i, str)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static void deletPage(int i, String str) {
        new File(Constants.AUDIO_DIR + str + "/" + i + Constants.AUDIO_EXTENSION).delete();
    }

    public static void deleteSurah(int i, int i2, String str) {
        String format = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i));
        for (int i3 = 1; i3 <= i2; i3++) {
            String format2 = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i3));
            new File(Constants.AUDIO_DIR + str + "/" + format + format2 + Constants.AUDIO_EXTENSION).delete();
        }
    }

    public static void deleteTafsirFile(String str) {
        new File(Constants.FILES_DIR + str).delete();
    }

    public static void deleteFileOrFolder(File file) throws IOException {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                deleteFileOrFolder(file2);
            }
        }
        if (!file.delete()) {
            throw new FileNotFoundException("Failed to delete file: " + file);
        }
    }

    public static long getDirectorySize(File file) {
        long j;
        File[] listFiles = file.listFiles();
        long j2 = 0;
        if (listFiles == null) {
            return 0;
        }
        for (File file2 : listFiles) {
            try {
                if (file2.isFile()) {
                    j = file2.length();
                } else {
                    j = getDirectorySize(file2);
                }
                j2 += j;
            } catch (Exception unused) {
            }
        }
        return j2;
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x0199 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0194 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x018f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0188 A[Catch: Exception -> 0x018d, TRY_LEAVE, TryCatch #12 {Exception -> 0x018d, blocks: (B:78:0x0184, B:80:0x0188), top: B:119:0x0184 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */

    /* JADX WARN: Removed duplicated region for block: B:123:0x017b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0176 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0185 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0180 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean fetchContent(String r21, String r22) {
        /*
            Method dump skipped, instructions count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nanosoft.holy.quran.utils.Utils.fetchContent(java.lang.String, java.lang.String):boolean");
    }


    public static void closeCurrentConnections() {
        InputStream inputStream = sInputstream;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static boolean isSdCardAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static void createFolders() {
        if (!new File(Constants.PAGES_DIR).exists()) {
            new File(Constants.PAGES_DIR).mkdirs();
        }
        if (!new File(Constants.PAGES_HD_DIR).exists()) {
            new File(Constants.PAGES_HD_DIR).mkdirs();
        }
        if (!new File(Constants.BOOKMARK_DIR).exists()) {
            new File(Constants.BOOKMARK_DIR).mkdirs();
        }
        if (!new File(Constants.AUDIO_DIR).exists()) {
            new File(Constants.AUDIO_DIR).mkdirs();
        }
        if (!new File(Constants.FILES_DIR).exists()) {
            new File(Constants.FILES_DIR).mkdirs();
        }
    }




    public static void unzip(String str) throws IOException {
        try {
            File file = new File(str);
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
            try {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    FileOutputStream fileOutputStream = new FileOutputStream(file.getParentFile() + "/" + nextEntry.getName());
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.close();
                }
                file.delete();
            } catch (Exception unused) {
            } catch (Throwable th) {
                zipInputStream.close();
                throw th;
            }
            zipInputStream.close();
        } catch (Exception e) {
            Log.e(Constants.TAG, "Unzip exception " + e.getMessage());
        }
    }

    public static void unzip(InputStream inputStream, OutputStream outputStream) throws IOException {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(inputStream));
            try {
                if (zipInputStream.getNextEntry() != null) {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        outputStream.write(bArr, 0, read);
                    }
                    outputStream.close();
                }
            } catch (Exception unused) {
            }
            zipInputStream.close();
        } catch (Exception e) {
            Log.e(Constants.TAG, "Unzip exception " + e.getMessage());
        }
    }

    public static float pixelsToSp(Context context, float f) {
        return f / context.getResources().getDisplayMetrics().scaledDensity;
    }





    public static void setSelectedReader(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(SELECTED_READER_PREFERENCE_KEY, str);
        edit.commit();
    }

    public static String getSelectedReader(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(SELECTED_READER_PREFERENCE_KEY, "-1");
    }

    public static void setNightModeEnabled(Context context, boolean z) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(NIGHT_MODE_ENABLED_PREFERENCE_KEY, z);
        edit.commit();
    }

    public static boolean getNightModeEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(NIGHT_MODE_ENABLED_PREFERENCE_KEY, false);
    }

    public static void setZoomModeEnabled(Context context, boolean z) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(ZOOM_MODE_ENABLED_PREFERENCE_KEY, z);
        edit.commit();
    }

    public static boolean getZoomModeEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(ZOOM_MODE_ENABLED_PREFERENCE_KEY, false);
    }

    public static void setArabicFontEnabled(Context context, boolean z) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(ARABIC_FONT_ENABLED_PREFERENCE_KEY, z);
        edit.commit();
    }

    public static boolean getArabicFontEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(ARABIC_FONT_ENABLED_PREFERENCE_KEY, true);
    }

    public static void setLanguageEnabled(Context context, boolean z) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(LANGUAGE_ENABLED_PREFERENCE_KEY, z);
        edit.commit();
    }

    public static boolean getLanguageEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(LANGUAGE_ENABLED_PREFERENCE_KEY, false);
    }

    public static void setSelectedTafsir(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(SELECTED_TAFSIR_PREFERENCE_KEY, str);
        edit.commit();
    }

    public static String getSelectedTafsir(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(SELECTED_TAFSIR_PREFERENCE_KEY, "4");
    }

    public static void setLanguage(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(LANGUAGE_PREFERENCE_KEY, str);
        edit.commit();
    }

    public static String getLanguage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(LANGUAGE_PREFERENCE_KEY, null);
    }

    public static void setLanguageChanged(Context context, boolean z) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(LANGUAGE_CHANGED_PREFERENCE_KEY, z);
        edit.commit();
    }

    public static boolean getLanguageChanged(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(LANGUAGE_CHANGED_PREFERENCE_KEY, false);
    }

    public static void setSelectedPagesType(Context context, int i) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putInt(SELECTED_PAGES_TYPE_PREFERENCE_KEY, i);
        edit.commit();
    }

    public static int getSelectedPagesType(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(SELECTED_PAGES_TYPE_PREFERENCE_KEY, 0);
    }
}
