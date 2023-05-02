package com.example.anamuslim.viewModel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Pair;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.anamuslim.MainActivity;
import com.example.anamuslim.R;
import com.example.anamuslim.handler.QuranAyah;
import com.example.anamuslim.handler.QuranAyahDetails;
import com.example.anamuslim.handler.QuranHandler;
import com.example.anamuslim.handler.TouchImageView;
import com.example.anamuslim.quran_manager.QuranPageManger;
import com.example.anamuslim.utils.Constants;
import com.example.anamuslim.utils.DownloadUtil;
import com.example.anamuslim.utils.Utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Objects;

public class QuranPageViewModel extends ViewModel {

    private static int sSelectionColor = Constants.AYAH_SELECTION_COLOR;
    private static int sCurrentZoomLevel = 0;
    public static SparseArray<View> sViews = new SparseArray<>();
    private static QuranAyahDetails sSelectedAyahDetails = null;
    private static int sSelectedSurah = -1;
    private static int sSelectedAyah = -1;

    public static Bitmap getQuranImage(int i) {


        return QuranPageManger.getQuranPages(i);
    }

    public void downloadQuranPage(Context context) {


        File root = Environment.getExternalStoragePublicDirectory("");


        File dir = new File(root + "/AnaMuslim/pages_hd");


        String fname = "images.zip";
        File file = new File(dir, fname);

        // Toast.makeText(context, "لا يوجد انترنت xxxx", Toast.LENGTH_SHORT).show();
        if (!file.exists()) {
            if (isConnected(context)) {
                //   final HTTP_ayat downloadTask = new HTTP_ayat(context);
                // downloadTask.execute(url);
                DownloadUtil.downloadProgress(context);
            } else {
                Toast.makeText(context, "لا يوجد انترنت ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isConnected(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            if (networkInfo.isConnected())
                return true;
            else
                return false;
        } else
            return false;

    }

    public void toggleFullscreen(int pageNumber) {
        int currentItem = (604 - pageNumber);
        for (int i = -1; i <= 1; i++) {
            View view = sViews.get(currentItem + i);
            if (view != null) {
                ((TouchImageView) view.findViewById(R.id.quran_image)).resetOnMeasureCalledFalg();
            }
        }

    }

    public void toggleZoomMode(Context context) {
        Utils.setZoomModeEnabled(context, !Utils.getZoomModeEnabled(context));
        //  this.mBackKeyPressed = true;
        //  this.mViewCleared = true;
        for (int i = 0; i < sViews.size(); i++) {
            ((BitmapDrawable) ((TouchImageView) sViews.valueAt(i).findViewById(R.id.quran_image)).getDrawable()).getBitmap().recycle();
        }
        sViews.clear();

        if (sSelectedAyahDetails != null) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(Constants.EXTRA_PAGE, sSelectedAyahDetails.mPageNumber);
            intent.putExtra(Constants.EXTRA_SURAH, sSelectedAyahDetails.mSurahNumber);
            intent.putExtra(Constants.EXTRA_AYAH, sSelectedAyahDetails.mAyahNumberInSurah);
            context.startActivity(intent);
        }
    }


    public static void loadQuranPage(Context fragmentActivity, TouchImageView touchImageView, int i) {
        int i2;
        Bitmap bitmap;
        Bitmap bitmap2;
        int i3;


        touchImageView.setZoomMode(Utils.getZoomModeEnabled(fragmentActivity));
//        StringBuilder sb = new StringBuilder();
//        sb.append(Constants.PAGES_TYPE_DIR[Utils.getSelectedPagesType(fragmentActivity)]);
//        int i4 = i + 1;
//        sb.append(i4);
//        sb.append(Constants.PAGES_EXTENSION);
        Bitmap decodeFile = getQuranImage(i + 1);


        View view2 = sViews.get(i);
        Paint paint = new Paint();
        Bitmap createBitmap = Bitmap.createBitmap(decodeFile.getWidth(), decodeFile.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(decodeFile, (Rect) null, new Rect(0, 0, decodeFile.getWidth(), decodeFile.getHeight()), (Paint) null);
        int i5 = 0;
        QuranAyahDetails quranAyahDetails = sSelectedAyahDetails;
        if (quranAyahDetails == null || quranAyahDetails.mPageNumber != i) {
            i2 = -1;
        } else {
            QuranAyah quranAyah3 = QuranHandler.mQuranAyahFinderList.get(new Pair(Integer.valueOf(sSelectedAyahDetails.mSurahNumber), Integer.valueOf(sSelectedAyahDetails.mAyahNumberInSurah)));
            QuranAyah quranAyah4 = QuranHandler.mQuranPageList.get(i).mQuranAyahList.get(quranAyah3.mAyahNumberInPage - 1);
            ArrayList arrayList2 = new ArrayList();
            i2 = -1;
            QuranHandler.returnSelectedAyahMarkers(fragmentActivity, quranAyah3, quranAyah4, (float) decodeFile.getWidth(), QuranHandler.isOnSameLine(quranAyah3, quranAyah4), arrayList2);
            paint.setColor(sSelectionColor);
            for (int i7 = 0; i7 < arrayList2.size(); i7++) {
                canvas.drawRect(((QuranAyah) arrayList2.get(i7)).mLeft, ((QuranAyah) arrayList2.get(i7)).mTop, ((QuranAyah) arrayList2.get(i7)).mRight, ((QuranAyah) arrayList2.get(i7)).mBottom, paint);
            }
            sSelectedAyah = -1;
            sSelectedSurah = -1;
            deleteAndCreateAutoBookmark(quranAyah3.mSurahNumber, quranAyah3.mAyahNumberInSurah);
        }
        if (sSelectedAyahDetails != null || sSelectedAyah == i2 || sSelectedSurah == i2 || QuranHandler.mQuranPageFinderList.get(new Pair(Integer.valueOf(sSelectedSurah), Integer.valueOf(sSelectedAyah))).intValue() != i) {
            bitmap2 = createBitmap;
            bitmap = decodeFile;
            i3 = 20;
        } else {
            QuranAyah quranAyah5 = QuranHandler.mQuranAyahFinderList.get(new Pair(Integer.valueOf(sSelectedSurah), Integer.valueOf(sSelectedAyah)));
            QuranAyah quranAyah6 = QuranHandler.mQuranPageList.get(i).mQuranAyahList.get(quranAyah5.mAyahNumberInPage - 1);
            ArrayList arrayList3 = new ArrayList();
            QuranHandler.returnSelectedAyahMarkers(fragmentActivity, quranAyah5, quranAyah6, (float) decodeFile.getWidth(), QuranHandler.isOnSameLine(quranAyah5, quranAyah6), arrayList3);
            paint.setColor(sSelectionColor);
            for (int i8 = 0; i8 < arrayList3.size(); i8++) {
                canvas.drawRect(((QuranAyah) arrayList3.get(i8)).mLeft, ((QuranAyah) arrayList3.get(i8)).mTop, ((QuranAyah) arrayList3.get(i8)).mRight, ((QuranAyah) arrayList3.get(i8)).mBottom, paint);
            }
            sSelectedAyah = i2;
            sSelectedSurah = i2;
            bitmap2 = createBitmap;
            i3 = 20;
            bitmap = decodeFile;
            sSelectedAyahDetails = new QuranAyahDetails(quranAyah5.mAyahNumberInSurah, quranAyah5.mSurahNumber, QuranHandler.mQuranSurahHash.get(quranAyah5).mQuranSurahNumberOfAyah, i, QuranHandler.mQuranSurahHash.get(quranAyah5).mQuranSurahName, QuranHandler.mQuranSurahHash.get(quranAyah5).mQuranSurahEnglishName, QuranHandler.mQuranSurahHash.get(quranAyah5).mQuranSurahPlaceOfDescend);
            deleteAndCreateAutoBookmark(quranAyah5.mSurahNumber, quranAyah5.mAyahNumberInSurah);
        }

        touchImageView.setImageBitmap(bitmap2);
        if (Utils.getNightModeEnabled(fragmentActivity)) {
            float[] fArr = new float[i3];
            // fill-array-data instruction
            fArr[0] = -1.0f;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 255.0f;
            fArr[5] = 0.0f;
            fArr[6] = -1.0f;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 255.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = -1.0f;
            fArr[13] = 0.0f;
            fArr[14] = 255.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
            touchImageView.setColorFilter(new ColorMatrixColorFilter(fArr));
        }
        bitmap.recycle();
    }

    public static boolean selectAyah(Context fragmentActivity, MotionEvent motionEvent, TouchImageView touchImageView, int i) {
        Matrix matrix = new Matrix();
        touchImageView.getImageMatrix().invert(matrix);
        float[] fArr = {motionEvent.getX(), motionEvent.getY()};
        matrix.mapPoints(fArr);
        QuranAyah quranAyah = new QuranAyah(fArr[0] - ((float) Constants.HALF_WIDTH_TYPE[Utils.getSelectedPagesType(fragmentActivity)]), fArr[1] - ((float) Constants.HALF_HEIGHT_TYPE[Utils.getSelectedPagesType(fragmentActivity)]), fArr[0] + ((float) Constants.HALF_WIDTH_TYPE[Utils.getSelectedPagesType(fragmentActivity)]), fArr[1] + ((float) Constants.HALF_HEIGHT_TYPE[Utils.getSelectedPagesType(fragmentActivity)]), 0, 0);
        Bitmap bitmap = ((BitmapDrawable) touchImageView.getDrawable()).getBitmap();
        ArrayList arrayList = new ArrayList();
        sSelectedAyahDetails = QuranHandler.selectAyah(fragmentActivity, quranAyah, i, (float) bitmap.getWidth(), arrayList);
        loadQuranPage(fragmentActivity, touchImageView, i);
        return arrayList.size() > 0;
    }

    public static void initialize(final Context fragmentActivity, final TouchImageView touchImageView, final int i) {
        loadQuranPage(fragmentActivity, touchImageView, i);
        touchImageView.setOnSingleTapListener(new TouchImageView.OnSingleTapListener() { // from class: com.nanosoft.holy.quran.ui.activities.QuranViewerActivity.8
            @Override // com.nanosoft.holy.quran.ui.controls.TouchImageView.OnSingleTapListener
            public void setOnSingleTapListener(MotionEvent motionEvent) {

                selectAyah(fragmentActivity, motionEvent, touchImageView, i);

                return;


            }
        });


    }

    public static void deleteAndCreateAutoBookmark(int i, int i2) {
        try {
            for (File file : Objects.requireNonNull(new File(Constants.APP_DIR).listFiles(new FilenameFilter() { // from class: com.nanosoft.holy.quran.ui.activities.QuranViewerActivity.10
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str) {
                    return str.endsWith(Constants.AUTO_BOOKMARK_EXTENSION);
                }
            }))) {
                file.delete();
            }
            new File(Constants.APP_DIR + i + Constants.BOOKMARKS_SEPARATOR + i2 + Constants.AUTO_BOOKMARK_EXTENSION).createNewFile();
        } catch (Exception unused) {
        }
    }
}