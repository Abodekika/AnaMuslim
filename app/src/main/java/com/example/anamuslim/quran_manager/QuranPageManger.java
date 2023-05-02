package com.example.anamuslim.quran_manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;

public class QuranPageManger {

    public static Bitmap getQuranPages(int i) {


        File root = Environment.getExternalStoragePublicDirectory("");


        File dir = new File(root + "/AnaMuslim");

        String fname = "/pages_hd";
        File file = new File(dir, fname);


        String filename = file + "/" + i + ".img";
        Bitmap bitmap;


        // bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), Uri.parse(filename));
        bitmap = BitmapFactory.decodeFile(filename);


        //  String filename = file+"/"+i + ".png";


        return bitmap;

    }
}
